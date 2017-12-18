package com.tencent.java.multithread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 参考文章：http://www.importnew.com/9668.html
 * 死锁的demo
 * 原因：多个线程共享多个资源，占有资源，然后去同步获取对方线程占有的资源，形成了一个依赖于资源的循环。
 * 怎么检测死锁：查看线程堆栈;查看java运行的进程，使用jstack pid > stack.txt，打印当前的java堆栈，对于死锁，堆栈里会帮你分析出来。
 * 避免方式：避免嵌套封锁；使用tryLock去获取数据，设置等待时间上限；使用同样的顺序去占有资源。
 * @author andy
 *
 */
public class DeadLockDemo {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	public static void main(String[] args) {
//		deadLockDemo();
		
		deadLockSolution1();
		
	}
	private static void deadLockDemo() {
		Object object1 = new Object();
		Object object2 = new Object();
		
		Thread syncThread1 = new Thread(new SyncThead(object1, object2));
		Thread syncThread2 = new Thread(new SyncThead(object2, object1));
		syncThread1.start();
		syncThread2.start();
		
	}
	
	private static void deadLockSolution1() {
		Object object1 = new Object();
		Object object2 = new Object();
		
		Thread thread1 = new Thread(new TheadSolution1(object1, object2));
		Thread thread2 = new Thread(new TheadSolution1(object2, object1));
		thread1.start();
		thread2.start();
		
	}
	
	
	

}

/**
 * 占有锁的同时去获取另外一个锁
 * @author andy
 *
 */
class SyncThead implements Runnable {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	public SyncThead(Object object1, Object object2) {
		super();
		this.object1 = object1;
		this.object2 = object2;
	}


	Object object1;
	Object object2;
	

	public void run() {
		String threadName = Thread.currentThread().getName();
		logger.debug("thread:{} run", threadName);
		
		//占有对象1
		logger.debug("try to lock on object1:{}",object1);
		synchronized (object1) {
			logger.debug("lock on object1:{}",object1);
			//等待100ms
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//去占有对象2
			logger.info("try to lock on object2:{}", object2);
			synchronized (object2) {
				logger.info("lock on object2:{}", object2);
				//等待100ms
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			logger.info("release on object2");
		}
		
		logger.info("release on object1");
		
		logger.info("thread:{} finish", threadName);
		
	}
	
}

/**
 * 占有另外一个锁的时候，释放自身的锁
 * @author andy
 *
 */
class TheadSolution1 implements Runnable {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	public TheadSolution1(Object object1, Object object2) {
		super();
		this.object1 = object1;
		this.object2 = object2;
	}


	Object object1;
	Object object2;
	

	public void run() {
		String threadName = Thread.currentThread().getName();
		logger.debug("thread:{} run", threadName);
		
		//占有对象1
		logger.debug("try to lock on object1:{}",object1);
		synchronized (object1) {
			logger.debug("lock on object1:{}",object1);
			//等待100ms
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}						
		}
		
		//去占有对象2
		logger.info("try to lock on object2:{}", object2);
		synchronized (object2) {
			logger.info("lock on object2:{}", object2);
			//等待100ms
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		logger.info("release on object2");
		
		logger.info("release on object1");
		
		logger.info("thread:{} finish", threadName);
		
	}
	
}
