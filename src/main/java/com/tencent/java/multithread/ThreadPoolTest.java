package com.tencent.java.multithread;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 线程池学习:https://www.jianshu.com/p/87bff5cc8d8c
 * Executor 
 * @author ewanzhao
 *
 */
public class ThreadPoolTest {
	
	//创建线程池的4种方式 除了newScheduledThreadPool的内部实现特殊一点之外，其它几个线程池都是基于ThreadPoolExecutor类实现的
	static Executor executor1 = Executors.newFixedThreadPool(10);    //创建一个包含10个线程的线程池
	static Executor executor2 = Executors.newCachedThreadPool();		//会自动释放线程资源
	static Executor executor3 = Executors.newSingleThreadExecutor();	//单个线程，可以保证提交的任务，按照顺序执行完
	static Executor executor4 = Executors.newScheduledThreadPool(10);    //定时任务，可以用来定期的同步数据
	
	public static void main(String[] args) {
		testRunThread();   //使用线程池去启动一个线程
		
		
	}

	/**
	 * 使用线程池来创建线程
	 * Executors.newFixedThreadPool(10)初始化一个包含10个线程的线程池executor；
	 * 通过executor.execute方法提交20个任务，每个任务打印当前的线程名；
	 * 负责执行任务的线程的生命周期都由Executor框架进行管理；
	 */
	private static void testRunThread() {
		for(int i=0; i < 20; i++) {
			executor1.execute(new TestTask());
		}
		
	}
	
	
	static class TestTask implements Runnable {

		@Override
		public void run() {
			System.out.println("hello,world. Thread Name:" + Thread.currentThread().getName() + " thread state:" );
			System.out.println();
			
		}
		
	}

}
