package com.tencent.java.collections;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.reflection.invoker.GetFieldInvoker;

public class HashMapTest {
	public static void main(String[] args) {
		
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
