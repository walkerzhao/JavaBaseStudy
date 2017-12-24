package com.tencent.java.multithread;

public class ExceptionInThreadTest {
	public static void main(String[] args) {
		exceptionTest();
		
		System.out.println("hello,world");
	}

	private static void exceptionTest() {
		throw new RuntimeException("test");
		
	}

}
