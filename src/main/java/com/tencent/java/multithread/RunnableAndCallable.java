package com.tencent.java.multithread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import org.apache.logging.log4j.core.lookup.MainMapLookup;

/**
 * Runnable和Callable的区别
 * @author andy
 *
 */
public class RunnableAndCallable {
	public static void main(String[] args) {
		
		RunImple runImple = new RunImple();
		Thread thread1 = new Thread(runImple);
		long time1 = System.currentTimeMillis();
		thread1.start();
		long time2 = System.currentTimeMillis();
		System.out.println(time2-time1);
		
		CallImpl callImpl = new CallImpl();
		FutureTask<Long> task = new FutureTask<>(callImpl);
		Thread thread2 = new Thread(task);;
		thread2.start();
		try {
			System.out.println(task.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long time3 = System.currentTimeMillis();
		System.out.println(time3-time2);
	}

}

class RunImple implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("thread run."+Thread.currentThread().getName());
		
	}
	
}


class CallImpl implements Callable<Long> {

	@Override
	public Long call() throws Exception {
		Thread.sleep(1000);
		System.out.println("thread run"+Thread.currentThread().getName());
		return 1L;
	}
	
}
