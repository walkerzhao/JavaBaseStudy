package com.tencent.java.multithread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程中的几个特性需要注意：原子性、可见性、有序性
 * 几种测试情况
 * volatile不带volatile：8700
 * 带volatile：7577
 * 
 * 结论：volatile可以保证可见性（线程中对volatile变量进行写操作，会立即刷新到主内存，正常不会理解刷新）、有序性（禁止cpu指令重排序）;
 * 但是不能保证原子性，可以通过 synchronized或lock 进行加锁；或者使用AtomicInteger（cas操作） 来保证原子性。
 * 
 * volatile的使用场景：https://blog.csdn.net/vking_wang/article/details/9982709   状态标记、单例模式的double check等；
 * @author andy
 *
 */
public class VolatileTest {
	public  int incr = 0;
	
	public volatile int incrWithVolatile =0;
	
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
		System.out.println(volatileTest.incrWithSync);    //保证操作同步
		System.out.println(volatileTest.incrWithLock);		//使用锁
		System.out.println(volatileTest.incrAtomicInteger);	//AtomicInteger  原子类型操作
	}

}
