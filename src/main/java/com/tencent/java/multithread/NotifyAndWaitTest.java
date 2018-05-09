package com.tencent.java.multithread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * http://www.importnew.com/16453.html 测试notify/notifyall/wait
 * 在执行wait, notify时，必须获得该对象的锁: 因为没有锁，多线程情况下，可能会发生竞态条件； 通过获得锁，来保证同步。jvm通过这种方式，来消除隐藏的竞态条件。
 * 用来解决生产者消费者问题；公用队列数据的同步问题，比如队列满的时候，生产者会等待消费者的通知；队列空的时候，消费者会等待生产者的通知。
 * 这个是多线程的一个通信问题。 wait、notify是某个Object的方法，在生产者消费者问题中，这个对象就是公用的缓冲区队列。
 * 
 * wait应该放在循环中，而不是if中，因为多个线程去wait等待 monitor，某个线程得到对象的锁之后，去执行一个操作，测试会更改 条件； 
 * 如果不是循环判断，另外一个线程获得对象锁的时候，条件其实已经不满足了，去执行下面的操作，会抛出一些异常之类的，或者是有一些错误的notify通知（代码问题）。
 * 
 * @author ewanzhao
 * 
 */
public class NotifyAndWaitTest {

	public static void main(String[] args) throws InterruptedException {
		
//		testWaitAndLock();
		
//		testWaitAndLockOtherObject();
		
		testWaitAndLockSelf();

		System.out.println("How to use wait and notify method in Java");
		System.out.println("Solving Producer Consumper Problem");
		Queue<Integer> buffer = new LinkedList<>();
		int maxSize = 10;
		Thread producer = new Producer(buffer, maxSize, "PRODUCER");
		Thread consumer = new Consumer(buffer, maxSize, "CONSUMER");
		producer.start();
		consumer.start();

	}

	private static void testWaitAndLockSelf() throws InterruptedException {
		Integer a = 1;
		Object data;
		synchronized (a) {
			System.out.println("object a wait.");
			a.wait();			
		}
		
	}

	private static void testWaitAndLockOtherObject() throws InterruptedException {
		Integer a = 1;
		Integer b = 2;
		synchronized (b) {
			a.wait();
		}
				
	}

	/**
	 * 使用wait/notify之前，必须先获得对象的锁
	 * @throws InterruptedException
	 */
	private static void testWaitAndLock() throws InterruptedException {
		Integer a = 1;
//		a.wait();	
		a.notify();
	}

}

class Producer extends Thread {
	private Queue<Integer> queue;
	private int maxSize;

	public Producer(Queue<Integer> queue, int maxSize, String name) {
		super(name);
		this.queue = queue;
		this.maxSize = maxSize;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (queue) {
				while (queue.size() == maxSize) {
					try {
						System.out.println("Queue is full, "
								+ "Producer thread waiting for "
								+ "consumer to take something from queue");
						queue.wait();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				Random random = new Random();
				int i = random.nextInt();
				System.out.println("Producing value : " + i);
				queue.add(i);
				queue.notifyAll();
			}
		}
	}
}

class Consumer extends Thread {
	private Queue<Integer> queue;
	private int maxSize;

	public Consumer(Queue<Integer> queue, int maxSize, String name) {
		super(name);
		this.queue = queue;
		this.maxSize = maxSize;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (queue) {
				while (queue.isEmpty()) {
					System.out.println("Queue is empty,"
							+ "Consumer thread is waiting"
							+ " for producer thread to put something in queue");
					try {
						queue.wait();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				System.out.println("Consuming value : " + queue.remove());
				queue.notifyAll();
			}
		}
	}
}