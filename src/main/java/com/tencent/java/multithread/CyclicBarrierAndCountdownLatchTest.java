package com.tencent.java.multithread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

import org.apache.logging.log4j.core.lookup.MainMapLookup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * cyclicbarrier表示一个栅栏，所有线程都必须等待栅栏放开才能继续执行
 * countdownlatch表示一个线程的计数器，通常主线程等待工作线程执行完毕，才执行操作；
 * @author andy
 *
 */
public class CyclicBarrierAndCountdownLatchTest {
	static Logger logger = LoggerFactory.getLogger(CyclicBarrierAndCountdownLatchTest.class);
	public static void main(String[] args) {
		
		CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
		CountDownLatch countDownLatch = new CountDownLatch(3);
		CycleBarrierThread cycleBarrierThread1 = new CycleBarrierThread(cyclicBarrier,countDownLatch);
		new Thread(cycleBarrierThread1).start();
		
		CycleBarrierThread cycleBarrierThread2 = new CycleBarrierThread(cyclicBarrier,countDownLatch);
		new Thread(cycleBarrierThread2).start();
		
		CycleBarrierThread cycleBarrierThread3 = new CycleBarrierThread(cyclicBarrier,countDownLatch);
		new Thread(cycleBarrierThread3).start();

		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//公布跑步结果
		logger.info("result-- thread1 cost:{} ms thread2 cost:{}ms thread3 cost:{}ms ",
				cycleBarrierThread1.getResult(), cycleBarrierThread2.getResult(), cycleBarrierThread3.getResult());
		
	}

}

/**
 * 运动员起跑，等待所有人准备好之后才开始跑
 * @author andy
 *
 */
class CycleBarrierThread implements Runnable {
	
	static Logger logger = LoggerFactory.getLogger(CycleBarrierThread.class);
	private CyclicBarrier cyclicBarrier;
	private CountDownLatch countDownLatch;

	private Long result;   //跑步计时
	public Long getResult() {
		return result;
	}



	public CycleBarrierThread(CyclicBarrier cyclicBarrier, CountDownLatch countDownLatch) {
		super();
		this.cyclicBarrier = cyclicBarrier;
		this.countDownLatch = countDownLatch;
	}



	@Override
	public void run() {
		long start = System.currentTimeMillis();
		logger.info("thread:{} i'm ready", Thread.currentThread().getName());
		try {
			cyclicBarrier.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//do other thing
		logger.info("thread:{} running", Thread.currentThread().getName());
		long end = System.currentTimeMillis();
		long cost = end -start;
		result = cost;
		
		countDownLatch.countDown();
		
		
	}
	
}
