package com.tencent.java.collections;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 判断Integer 相等符号--in the range -128 to 127 是缓存的，指向同一个地方，其他的是new出来的
 * {@link Integer valueOf()}
 * http://blog.csdn.net/xiaojiesu/article/details/50215237
 * @author ewanzhao
 *
 */
public class IntegerEqual {
	
	public static void main(String[] args) {
		testIntegerEqual(123,123);
		AtomicInteger
		
		testIntegerEqual(12345,12345);
	}

	private static void testIntegerEqual(Integer a, Integer b) {

		if(a == b) {
			System.out.println(true);
		} else {
			System.out.println(false);
		}
	}

}
