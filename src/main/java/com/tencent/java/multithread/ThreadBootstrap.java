package com.tencent.java.multithread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.logging.log4j.core.lookup.MainMapLookup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 创建线程和启动线程
 * @author andy
 *
 */
public class ThreadBootstrap {
	static Logger logger = LoggerFactory.getLogger(ThreadBootstrap.class);
	public static void main(String[] args) {
		ThreadExtends threadExtends = new ThreadExtends();
		threadExtends.start();
		

		Thread threadImplents = new Thread(new ThreadImplents());
		threadImplents.start();
		
		CallImplents callImplents = new CallImplents();
		FutureTask<Integer> futureTask = new FutureTask<Integer>(callImplents);
		Thread task = new Thread(futureTask);
		task.start();
		
		try {
			try {
				logger.info("task value:{}",futureTask.get(1000, TimeUnit.MILLISECONDS));
			} catch (TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}


class ThreadExtends extends Thread {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		for(int i = 0; i<5; i++) {
			logger.info("thread:{} i:{}", Thread.currentThread().getName(), i);
		}
	}
}

class ThreadImplents implements Runnable {
	Logger logger = LoggerFactory.getLogger(getClass());
	public void run() {
		for(int i = 0; i<5; i++) {
			logger.info("thread:{} i:{}", Thread.currentThread().getName(), i);
		}
		
	}	
}

class CallImplents implements Callable<Integer> {
	Logger logger = LoggerFactory.getLogger(getClass());
	public Integer call() throws Exception {
		int i=0;
		for(i =0; i<5; i++) {
			logger.info("thread:{} i:{}", Thread.currentThread().getName(), i);
		}
		return i;
	}
	
}
