package com.tencent.java.jvm;

/**
 * 查看java进程里哪个线程占用cpu最多
 * @author andy
 *
 */
public class ThreadCpuTest {
	public static void main(String[] args) {
		ThreadForCpuTest thread1 = new ThreadForCpuTest(10_0000, 30);
		ThreadForCpuTest thread2 = new ThreadForCpuTest(5_0000, 60);
		new Thread(thread1).start();
		new Thread(thread2).start();
	}

}


class ThreadForCpuTest implements Runnable {
	int total;
	int sleepTime;
	

	public ThreadForCpuTest(int total, int sleepTime) {
		super();
		this.total = total;
		this.sleepTime = sleepTime;
	}


	@Override
	public void run() {
		for(int i =0; i< 10000; i++) {
			System.out.println("i:"+i);
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
