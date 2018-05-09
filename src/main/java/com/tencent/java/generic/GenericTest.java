package com.tencent.java.generic;

import java.util.ArrayList;
import java.util.List;
/**
 * java泛型测试
 * * 测试类型擦出，并用反编译确认类型擦出：javap -c -s GenericTest
 * * 泛型的继承
 * @author andy
 *
 */
public class GenericTest {
	
	public static void main(String[] args) {
		if(3*0.1 == 0.3) {
			System.out.println(true);
		} else {
			System.out.println(false);
		}
		
		typeErasure();
	}

	/**
	 * 测试类型擦除
	 */
	private static void typeErasure() {
		List<Long> test1 = new ArrayList<>();
		List<String> test2 = new ArrayList<>();
		System.out.println(test1.getClass() == test2.getClass());
	}

}
