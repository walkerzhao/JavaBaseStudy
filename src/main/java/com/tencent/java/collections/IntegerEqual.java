package com.tencent.java.collections;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * �ж�Integer ��ȷ���--in the range -128 to 127 �ǻ���ģ�ָ��ͬһ���ط�����������new������
 * {@link Integer valueOf()}
 * http://blog.csdn.net/xiaojiesu/article/details/50215237
 * @author ewanzhao
 *
 */
public class IntegerEqual {
	
	public static void main(String[] args) {
		testIntegerEqual(123,123);
		
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
