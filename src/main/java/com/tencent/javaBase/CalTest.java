package com.tencent.javaBase;

public class CalTest {
	public static void main(String[] args) {
		if(!test1() && test2()) {
			System.out.println("hello,world");
		}
	}

	private static boolean test2() {
		System.out.println("test2");
		return false;
	}

	private static boolean test1() {
		System.out.println("test1");
		return false;
	}
}
