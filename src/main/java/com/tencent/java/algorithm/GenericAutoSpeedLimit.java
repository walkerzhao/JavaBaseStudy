package com.tencent.java.algorithm;

import io.netty.util.internal.ThreadLocalRandom;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Singleton;


/**
 * 智能自动限流保护器,多实例
 * @author jimmyzhu
 *
 */

public class GenericAutoSpeedLimit {
	 private final static Logger logger = LoggerFactory.getLogger(GenericAutoSpeedLimit.class);
	
	private  AtomicInteger threshold = new AtomicInteger(0);
	private  AtomicInteger succCount = new AtomicInteger(1);
	private  AtomicInteger errorCount = new AtomicInteger(0);
	private  AtomicInteger incrPercent = new AtomicInteger(1);
	
	private  long curTime = System.currentTimeMillis();
	private  AtomicLong lastTime = new AtomicLong(curTime);
	
	private  AtomicLong lastPrint = new AtomicLong(curTime);
	
	private  GenericSpeedLimit speedLimit = new GenericSpeedLimit(); 
	
	public  void addSucc(){
		succCount.incrementAndGet();
	}
	//为什么不直接统计total,多线程并发,total和succ可能数量不一致误计算成功率<100%
	public  void addFail(){
		errorCount.incrementAndGet();
	}
	
	public  void addSucc(int count){
		for (int i = 0; i < count; i++) {
			succCount.incrementAndGet();
		}
	}
	public  void addFail(int count){
		for (int i = 0; i < count; i++) {
			errorCount.incrementAndGet();
		}
	}
	
	/**
	 * 返回true 被限速
	 * @return
	 */
	public  boolean checkLimited(){
		checkReset();
		if(speedLimit.checkLimited(threshold.get())){
			return true;
		}
		return false;
	}
	
	/**
	 *  返回true 被限速
	 * @param count
	 * @return
	 */
	public  boolean checkLimited(int count){
		checkReset();
		if(speedLimit.checkLimited(threshold.get(),count)){
			return true;
		}
		return false;
	}
	
	
	
	private void checkReset() {
		//周期检测与重置
		if(checkPeriod(lastTime,1000)){
			float error = errorCount.get();
			float succ = succCount.get();
			float succRate = succ / (succ+error);
			if( succRate < 0.97){//成功率降降低 ,缩小门限 至成功请求数,97为测试后的 值,太小容易浮动太大达不到效果..99错误又比97多
				int shrink = (int) (succ*succRate);
				threshold.set(shrink<2?2:shrink);
				incrPercent.set(1);
				//有失败, 1秒一次打印.
				logger.error("succ="+succ+ " error="+error+ " succRate="+succRate+" < 97 reduce threshold to " +threshold.get());
				//System.out.println("succ="+succ+ " error="+error+ " succRate="+succRate+" < 97 reduce threshold to " +threshold.get());
			}else{
				//成功率>97% 继续扩大门限
				int thresval = threshold.get();
				int incrPerc = incrPercent.get();
				int extendVal = (int) (thresval* (1 + (incrPerc / 100f)));
				threshold.set(extendVal>1000000? 1000000: extendVal);
				incrPercent.set(incrPerc*2 >= 128 ? 200:incrPerc*2 );//最大2倍增长,%1 %2 %4 %8 16%...
				//正常成功率 1分钟打印一次.
				if(checkPeriod(lastTime,60*1000)){
					logger.info("succ="+succ+ " error="+error+ " succRate="+succRate+" incr threshold to " +threshold.get() +" incrPerc="+incrPerc+"%"  );
				}
				//System.out.println("succ="+succ+ " error="+error+ " succRate="+succRate+" incr threshold to " +threshold.get() +" incrPerc="+incrPerc+"%" );
			}
			errorCount.set(0);
			succCount.set(1);
		}
	}
	
	
	public String getCurInfo(){
		return "cur_speed="+getCurSpeed()+"/s"+" succ="+getCurSuccRate() + " theshold="+getCurTheshold();
	}
	public int getCurSpeed(){
		return speedLimit.getCurSpeed();
	}
	
	public float getCurSuccRate(){
		float error = errorCount.get();
		float succ = succCount.get();
		float succRate = succ / (succ+error);
		return succRate;
	}
	
	public float getCurTheshold(){
		 return threshold.get();
	}
	
	private static boolean checkPeriod(AtomicLong lastPrint, long period){
		long cur = System.currentTimeMillis();
		long last = lastPrint.get();
		if( cur - last > period && lastPrint.compareAndSet(last, cur)){
			return true;
		}
		return false;
	}
	
public static void main(String[] args) throws InterruptedException {
		
		int threadCount = 60;
		final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
		final StringBuffer result = new StringBuffer();
		
		final GenericSpeedLimit genericSpeedLimit = new GenericSpeedLimit();
		final GenericAutoSpeedLimit redisSppedlimit = new GenericAutoSpeedLimit();
		for (int i = 0; i < threadCount; i++) {
			new Thread(){
				@Override
				public void run() {
					long curTime = System.currentTimeMillis();
					AtomicLong lastTime = new AtomicLong(curTime);
					
					
					 //1000 会执行5秒.
					 boolean errMode = true;
					 boolean printStartFail = false;
					//构造每秒最多 100/s ,20线程产生2000/s  
					 int totalCount = 100*60;
					 for (int j = 0; j < totalCount; j++) {
						 int nextInt = ThreadLocalRandom.current().nextInt(20);
						 try { 
							Thread.sleep(nextInt);  
						} catch (InterruptedException e) {
						}
						 boolean checkLimit = redisSppedlimit.checkLimited();
						 if(!checkLimit){
							// System.out.println(" qps limit !");
							 redisSppedlimit.addSucc();
							 if(System.currentTimeMillis() -curTime  <= 20*1000 ){//10秒内
								 if(genericSpeedLimit.checkLimited(1500)){//被调服务性能1500/s
									 redisSppedlimit.addFail();
								 }
							 }else{
								 if( printStartFail == false ){
									 System.out.println("start fail mode!!!");
									 printStartFail = true;
								 }
									 
								 if(checkPeriod(lastTime,40000)){//20-30秒 强制失败模式
									 errMode = false;
									 System.out.println("end fail mode!!!!");
								 }
								 if(errMode){
									 int rr = ThreadLocalRandom.current().nextInt(10)+1;
									 int error = ThreadLocalRandom.current().nextInt(rr);
									 if(error >= 5){ //随机失败
										 redisSppedlimit.addFail();
									 }
								 }
							 }
							 
						 }else{
							// System.out.println("limit");
						 }
					 }
					 countDownLatch.countDown();
				} 
			}.start();;
		}
		
		countDownLatch.await();
		System.out.println( result);
		System.exit(0);
	}
	
}
