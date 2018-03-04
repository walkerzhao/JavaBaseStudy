package com.tencent.java.collections;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.ibatis.reflection.invoker.GetFieldInvoker;

/**
 * 参考hashmap在并发场景下的死循环问题：https://coolshell.cn/articles/9606.html
 * 当 多个线程在进行rehash的时候，会同时操作一个共同的存储数据，涉及到链表数据迁移的时候，会造成环形链表的结构，然后get数据，遍历到这个环形链表的时候，就会出现死循环。
 * @author andy
 *
 */
public class HashMapTest {
	public static void main(String[] args) {
			
		simpleHashMapTest();
		
		concurrentHashMapTest();
	}

	/**
	 * hashMap的死循环并发测试
	 */
	private static void concurrentHashMapTest() {
		//两个生产者往hashmap中put数据
		HashMap<Integer, Object> map = new HashMap<>();
		ProducerPutData producerPutData1 = new ProducerPutData(map);
		ProducerPutData producerPutData2 = new ProducerPutData(map);
		new Thread(producerPutData1).start();
		new Thread(producerPutData2).start();
		
		
		//主线程get 数据，get可能会出现死循环
		for(int i=0; i<1_0000; i++) {
//			if(map.containsKey(i)) {
				Object data = map.get(i);
				System.out.println("i:" + i + " data:" + data);
//			}
		}
		
	}
	
	static class ProducerPutData implements Runnable{
		private HashMap<Integer, Object> map;

		public ProducerPutData(HashMap<Integer, Object> map) {
			super();
			this.map = map;
		}

		@Override
		public void run() {
			Random random = new Random();
			while(true) {
				map.put(random.nextInt()% (1024 * 1024 * 100), null);
			}

		}
		
		
	}

	private static void simpleHashMapTest() {
		String str = " test";
		int int1 = 1;
		System.out.println(str.hashCode());
		System.out.println(int1);
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("语文", 1);
		map.put("数学", 2);
		map.put("英语", 3);
		map.put("历史", 4);
		map.put("政治", 5);
		map.put("地理", 6);
		map.put("生物", 7);
		map.put("化学", 8);
		for(Map.Entry<String, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}

}
