package com.tencent.java.multithread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程中的几个特性需要注意：原子性、可见性、有序性
 * 几种测试情况
 * volatile不带volatile：8700
 * 带volatile：7577
 * @author andy
 *
 */
public class VolatileTest {
	public  int incr = 0;
	
	public int incrWithVolatile =0;
	
	public int incrWithSync = 0;
	
	public int incrWithLock = 0;
	Lock lock = new ReentrantLock();
	
	public AtomicInteger incrAtomicInteger = new AtomicInteger(0);
	
	public void increase() {
		incr++;
	}
	
	public void increaseWithVolatile() {
		incrWithVolatile++;
	}
	
	public synchronized void increaseWithSync() {
		incrWithSync++;
	}
	
	public void increaseWithLock() {
		lock.lock();
		incrWithLock++;
		lock.unlock();
	}
	
	public void increaseWithAtomicInteger() {
		incrAtomicInteger.getAndIncrement();
	}
	
	public static void main(String[] args) {
		final VolatileTest volatileTest = new VolatileTest();
		for(int i=0; i< 10; i++) {
			new Thread() {
				public void run() {
					for(int j=0; j< 1000; j++) {
						volatileTest.increase();
						volatileTest.increaseWithVolatile();
						volatileTest.increaseWithSync();
						volatileTest.increaseWithLock();
						volatileTest.increaseWithAtomicInteger();
					}
				}
				
			}.start();
		}
		
		while(Thread.activeCount() > 1) {    //等待所有线程执行完毕
			Thread.yield();
		}
		
		System.out.println(volatileTest.incr);
		System.out.println(volatileTest.incrWithVolatile);
		System.out.println(volatileTest.incrWithSync);
		System.out.println(volatileTest.incrWithLock);
		System.out.println(volatileTest.incrAtomicInteger);
	}

}
