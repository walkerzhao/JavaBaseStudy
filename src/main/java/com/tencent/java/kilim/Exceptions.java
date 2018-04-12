package com.tencent.java.kilim;

import kilim.Mailbox;
import kilim.Pausable;
import kilim.Task;

/**
 * 测试栈值恢复 && bug修复
 * https://github.com/kilim/kilim/issues/49
 * @author ewanzhao
 *
 */
public class Exceptions extends Task {
	
	public static void main(String args[]) {
		new Exceptions().start();
	}
	
	private String fWithOutMailBox() throws Pausable {
		throw new RuntimeException(); // no pausable method
	}

	private String fWithMailBox() throws Pausable {
		new Mailbox<String>().get(1000); // has pausable method
		throw new RuntimeException();
	}

	public void execute() throws Pausable {
		test1();
		
		test2();
	}
	
	
	private void test2() throws Pausable {
		int tryTime = 1;
		while(tryTime > 0) {
			try {
				fWithMailBox();
				tryTime = 1;
			} catch(Exception e) {
				
			}
			
			System.out.println("tryTime:"+tryTime);
			Task.sleep(1000);
		}
		System.out.println("tryTime after:"+ tryTime);
		
	}

	public void test1() throws Pausable{
		String foo = "data";
		try {
			foo = fWithOutMailBox();
		} catch (Throwable t) {
			System.out.println("test foo=" + foo);
		}
		String foo2 = "data";
		try {
			foo2 = fWithMailBox();
		} catch (Throwable t) {
			System.out.println("testfoo2=" + foo2);
		}
	}

	
}
