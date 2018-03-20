package com.tencent.java.algorithm;

import io.netty.util.internal.ThreadLocalRandom;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

/**
 * 
 * 基于延迟队列的方式, 实现令牌桶限流算法, 该队列方式poll性能 2000w/s 可能不止. 
 * 该通用实现类能支撑 的所有总限速为 2000w/s 
 * 如果想精确控制, 改写成一个类一个thread去take就没误差了
 * @author jimmyzhu
 *
 */
public class GenericSpeedLimit {
	
	private static final Logger log = LoggerFactory.getLogger(GenericSpeedLimit.class);
	
	private static final ExecutorService executorSingleService = Executors.newSingleThreadExecutor();
	private static final  List<DelayQueue<DelayItem>> limitDelayQueueList = new CopyOnWriteArrayList<DelayQueue<DelayItem>>();

	private  DelayQueue<DelayItem> limitDelayQueue = new DelayQueue<DelayItem>();
	
	@Inject
	public GenericSpeedLimit(){
		limitDelayQueueList.add(this.limitDelayQueue);
	}
	
	/**
	 * 限速上报及检查, 单位秒如 qpsLimit =  1000/s
	 * @param qpsLimit
	 * @return 被限速 返回 true
	 */
	public boolean checkLimited(int qpsLimit){
		
		if(qpsLimit>0 && limitDelayQueue.size() >= qpsLimit){
			//log.error(" reach limit valve qps=" +qpsLimit);
			return true;
		}
		limitDelayQueue.put(new DelayItem(System.currentTimeMillis()+1000));
		return false;
	}
	
	public boolean checkLimited(int qpsLimit , int count){
		if(qpsLimit>0 && (limitDelayQueue.size()+count) >= qpsLimit){
			//log.error(" reach limit valve qps=" +qpsLimit);
			return true;
		}
		long currentTimeMillis = System.currentTimeMillis();
		for(int i =0; i< count ; i++){
			limitDelayQueue.put(new DelayItem(currentTimeMillis+1000));
		}
		
		return false;
	}
	
	public int getCurSpeed(){
		return limitDelayQueue.size();
	}
	
	
	
	static{
		executorSingleService.execute(new Runnable() {
					
			@Override
			public void run() {
				int inValidCount = 0; 
				while(true){
					try {
						boolean effectCycle = false;
						Iterator<DelayQueue<DelayItem>> iterator = limitDelayQueueList.iterator();
						while(iterator.hasNext()){
							DelayQueue<DelayItem> next = iterator.next();
							while(next.poll() != null){
								effectCycle = true;
							}
						}
						
						if(effectCycle == false){
							if(inValidCount++ >=2){//无效遍历 2次则开始递增 sleep 
								//System.out.println("无效遍历,开始睡眠 "+inValidCount +"ms 当前总队列数" + limitDelayQueueList.size());
								Thread.sleep(inValidCount>50?50:inValidCount); //最差情况有 50ms误差控制 ,一般情况几ms误差.
							}
						}else{
							inValidCount = 0; //有效遍历
						}
					} catch (Exception e) {
						log.error("GenericSpeedLimit error",e);
					}
				}
			}
		});
	}
	
	static class DelayItem implements Delayed{
		long time;
		
		DelayItem(long time){
			this.time= time;
		}
		
		@Override
		public int compareTo(Delayed o) {
			  if(this.time < ((DelayItem)o).time) return -1;  
	            else if(this.time > ((DelayItem)o).time)return 1;  
	            else return 0;
		}
		
		@Override
		public long getDelay(TimeUnit unit) {
			return unit.convert(time - System.currentTimeMillis(), unit.MILLISECONDS);
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		DelayQueue<DelayItem> limitDelayQueue = new DelayQueue<DelayItem>();
		for (int i = 0; i < 1000000; i++) {
			limitDelayQueue.put(new DelayItem(System.currentTimeMillis()+1000));
		}
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			limitDelayQueue.poll();
		}
		long end = System.currentTimeMillis();
		System.out.println("100w 消耗时间 "+ (end-start));
		
		int threadCount = 10;
		final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
		final StringBuffer result = new StringBuffer();
		final GenericSpeedLimit genericSpeedLimit = new GenericSpeedLimit();
		for (int i = 0; i < threadCount; i++) {
			new Thread(){
				@Override
				public void run() {
					
					 
					 long start = System.currentTimeMillis();
					 int throughCount = 0;
					//构造每秒最多 100/s  1000个需要 跑10秒
					 int totalCount = 1000;
					 for (int j = 0; j < totalCount; j++) {
						 int nextInt = ThreadLocalRandom.current().nextInt(20);
						 try { 
							Thread.sleep(nextInt); 
						} catch (InterruptedException e) {
						}
						 boolean checkLimit = genericSpeedLimit.checkLimited(50);//限速每秒50
						 if(!checkLimit){
							// System.out.println(" qps limit !");
							 throughCount++;
						 }
					 }
					 long end = System.currentTimeMillis();
					 result.append(this.getName()+ " cost " + (end -start) + " totalCount="+totalCount+ " throughputCount="+throughCount+"\n");
					 
					 countDownLatch.countDown();
				} 
			}.start();;
		}
		
		countDownLatch.await();
		System.out.println( result);
		System.exit(0);
	}
	
}
