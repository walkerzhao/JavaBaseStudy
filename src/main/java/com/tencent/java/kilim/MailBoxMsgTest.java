package com.tencent.java.kilim;

import kilim.Mailbox;
import kilim.Pausable;
import kilim.Task;

public class MailBoxMsgTest {
	
	public static void main(String[] args) throws InterruptedException {
		test1(3000_0000);
		
		test2(3000_0000);

	}

	private static void test2(long num) throws InterruptedException {
		long start = System.currentTimeMillis();
		Mailbox<Integer> mailbox = new Mailbox<>();
		for(int i=0; i< num; i++) {
			ProducerTask2 producerTask2 = new ProducerTask2(mailbox, i);
			producerTask2.start();
		}
		
		
		Thread.sleep(10_000);
		long end = System.currentTimeMillis();
		System.out.println(mailbox.size() +" cost:{}"+ (end-start));
		
	}

	private static void test1(long num) throws InterruptedException {
		long start = System.currentTimeMillis();
		Mailbox<Integer> mailbox = new Mailbox<>();
		for(int i=0; i< num; i++) {
			ProducerTask producerTask = new ProducerTask(mailbox, i);
			producerTask.start();
		}
		
		
		Thread.sleep(10_000);
		long end = System.currentTimeMillis();
		System.out.println(mailbox.size()+" cost:{}"+ (end-start));
		
	}

}


/**
 * 生产消息到mailbox
 * @author andy
 *
 */
class ProducerTask extends Task {
	
	public ProducerTask(Mailbox<Integer> mailbox, int i) {
		super();
		this.mailbox = mailbox;
	}


	int i;
	Mailbox<Integer> mailbox;
	
	
	
	public void execute() throws Pausable{
		
		mailbox.putb(i,500);
	}
	
}

class ProducerTask2 extends Task {
	
	public ProducerTask2(Mailbox<Integer> mailbox, int i) {
		super();
		this.mailbox = mailbox;
	}


	int i;
	Mailbox<Integer> mailbox;
	
	
	
	public void execute() throws Pausable{
		
		mailbox.putb(i);
	}
	
}


/**
 * 从mailbox中取出消息消费
 * @author andy
 *
 */
class ConsumerTask extends Task {
	public ConsumerTask(Mailbox<Integer> mailbox) {
		super();
		this.mailbox = mailbox;
	}

	Mailbox<Integer> mailbox;
	
	public void execute() throws Pausable{
		
		
	}
	
}