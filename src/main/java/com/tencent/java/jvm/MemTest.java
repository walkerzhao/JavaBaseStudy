package com.tencent.java.jvm;

import java.util.Date;

/**
 * java内存分布测试
 * debug下，看看变量的消亡
 * @author andy
 *
 */
public class MemTest {
	
	public static void main(String[] args) {
		int a = 10;   //a--基本类型存在栈上
		Date date = new Date(); 	//date--引用类型(类似指针)，存在栈上,date指向的对象存在堆上
		System.out.println(date);
		
		foo(a);
		System.out.println("hello,world");
	}
	
	/**
	 * 调用栈
	 * @param i
	 */
	static void foo(int i) {
		int temp = i;
		System.out.println(temp);
	}

}
