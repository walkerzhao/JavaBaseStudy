package com.tencent.java.kilim;

import kilim.Mailbox;

/**
 * * 需要验证几个点：* putb，当mailbox满的时候，消息是否会丢；
 * * 复现轩总的问题
 * * jstack 查看线程执行状况，定位问题
 * @author ewanzhao
 *
 */
public class XuanzongKilimBugFix {
	public static void main(String[] args) throws InterruptedException {
		int initailSize = 5;
//		int maxSize = 3000_0000;
		int maxSize = 5;
		Mailbox<Integer> mailbox = new Mailbox<>(initailSize, maxSize); 
		Producer producer = new Producer(mailbox, maxSize); 
		new Thread(producer).start();
		
		Thread.sleep(1000);
		
		Consumer consumer = new Consumer(mailbox, maxSize);
		new Thread(consumer).start();
		
	}
	
}

/**
 * 往mailbox中放消息
 * @author ewanzhao
 *
 */
class Producer implements Runnable {
	public Producer(Mailbox<Integer> mailbox, int maxSize) {
		super();
		this.mailbox = mailbox;
		this.maxSize = maxSize;
	}


	Mailbox<Integer> mailbox;
	int maxSize;
	

	@Override
	public void run() {
		testMailBoxPutbMsg();		
	}
	
	private  void testMailBoxPutbMsg() {
		System.out.println("put msg into mailbox");
		
		long start = System.currentTimeMillis();
		for(int i=0; i< (maxSize+1);i++) {
			
			mailbox.putb(i);
			System.out.println("put msg suc:"+i +" size:"+mailbox.size());
//			if(mailbox.size()==maxSize) {
//				long end = System.currentTimeMillis();
//				System.out.println("pub msg suc:"+i +" mailbox size:" + mailbox.size() );
//				System.out.println("cost:" + (end-start));				
//			}
			
//			System.out.print("mailbox data: ");
//			for(int j=0; j< ((Object[])mailbox.msgs).length; j++) {
//				System.out.print(((Object[])mailbox.msgs)[j] +" ");
//			}
//			System.out.println(" mailbox.size:"+mailbox.size());
			
		}
		
	}
	
}

class Consumer implements Runnable {
	
	public Consumer(Mailbox<Integer> mailbox, int maxSize) {
		super();
		this.mailbox = mailbox;
		this.maxSize = maxSize;
	}

	Mailbox<Integer> mailbox;
	int maxSize;

	@Override
	public void run() {
		System.out.println("consume msg from mailbox");
		int cnt = 0;
		while(true) {
			Integer integer = mailbox.getb();
			System.out.println("get msg:"+integer);
			
			try {
				Thread.sleep(10);    //隔1s消费一个--for 生产者足够时间put数据
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cnt++;
			if(cnt == maxSize+1) {
				System.out.println("maxSize."+maxSize);
				break;
			}
		}
		// TODO Auto-generated method stub
		
	}
	
}



