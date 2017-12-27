package com.tencent.java.jvm;

/**
 * java的性能分析工具测试
 * @author andy
 *
 */
public class MemToolTest {
	public static void main(String[] args) throws InterruptedException {
		int i = 0;
		while(true) {
			System.out.println("hello,world"+(i++));
			Thread.sleep(1000);
		}
	}

}
