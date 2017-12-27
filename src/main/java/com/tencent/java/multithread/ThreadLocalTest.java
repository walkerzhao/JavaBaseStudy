package com.tencent.java.multithread;

import java.util.ArrayList;
import java.util.List;

/**
 * 参考：http://uule.iteye.com/blog/870464
 * @author andy
 *
 */
public class ThreadLocalTest {
	public static void main(String[] args) {
		
		SeqNum seqNum = new SeqNum();
		List<Integer> num = new ArrayList<>();
		
		ThreadForThreadLocal thread1 = new ThreadForThreadLocal(seqNum, num);
		ThreadForThreadLocal thread2 = new ThreadForThreadLocal(seqNum, num);
		ThreadForThreadLocal thread3 = new ThreadForThreadLocal(seqNum, num);
		new Thread(thread1).start();
		new Thread(thread2).start();
		new Thread(thread3).start();
		
	}
	
	
}

class SeqNum {
	ThreadLocal<Integer> num = new ThreadLocal<Integer>() {
		public Integer initialValue(){  
            return 0;  
        }  
	};
	
	public Integer getNext() {
		num.set(num.get()+1); 
		return num.get();
	}
}

class ThreadForThreadLocal implements Runnable {
	
	SeqNum seqNum;
	
	List<Integer> num;
	
	

	public ThreadForThreadLocal(com.tencent.java.multithread.SeqNum seqNum, List<Integer> num) {
		super();
		this.seqNum = seqNum;
		this.num = num;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i =0; i < 3; i++) {
			num.add(i);
			System.out.println("thread:{}"+Thread.currentThread().getName()+ " i:"+i +" SeqNum:" + seqNum.getNext()
			+ " num:" + num);
		}
		
	}
	
}
