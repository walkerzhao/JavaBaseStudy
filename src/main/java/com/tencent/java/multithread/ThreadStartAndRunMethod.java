package com.tencent.java.multithread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 创建线程和启动线程
 * @author andy
 *
 */
public class ThreadStartAndRunMethod {
	static Logger logger = LoggerFactory.getLogger(ThreadStartAndRunMethod.class);
	public static void main(String[] args) {

		Thread threadImplents = new Thread(new ThreadImplents1());
		threadImplents.start();
		
		
		threadImplents.run();
	
	}

}


class ThreadImplents1 implements Runnable {
	Logger logger = LoggerFactory.getLogger(getClass());
	public void run() {
		for(int i = 0; i<5; i++) {
			logger.info("thread:{} i:{}", Thread.currentThread().getName(), i);
		}
		
	}	
}

