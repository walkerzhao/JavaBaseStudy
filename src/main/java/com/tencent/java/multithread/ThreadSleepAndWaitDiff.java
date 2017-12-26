package com.tencent.java.multithread;

/**
 * sleep和wait的区别
 * @author andy
 *
 */
public class ThreadSleepAndWaitDiff {
	public static void main(String[] args) throws InterruptedException {
		ThreadB threadb = new ThreadB();
		Thread thread = new Thread(threadb);
		thread.start();
		
		Thread.sleep(300);
		synchronized (threadb) {
			System.out.println("get threadb monitor..");
			try {
				Thread.sleep(400);
				
				threadb.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("back.");
		}
	}

}


class ThreadB implements Runnable {

	@Override
	public void run() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		synchronized (this) {
			System.out.println("thread:"+Thread.currentThread().getName() + " running");
			for(int i =0; i<100; i++) {
				System.out.println("i:"+i);
			}
			notify();
		}
		
	}
	
}
