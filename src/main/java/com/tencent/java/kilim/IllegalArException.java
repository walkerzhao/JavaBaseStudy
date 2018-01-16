package com.tencent.java.kilim;

import kilim.Mailbox;
import kilim.Pausable;
import kilim.Task;

public class IllegalArException extends Task {
	public static void main(String[] args) {
		new IllegalArException().start();
	}

	public void execute() throws Pausable {
		int i = 2;
		try {
			new Mailbox<String>().get(1000);// RPC
			throw new DolphinRunException();
		} catch (DolphinRunException ex) {
			System.out.println("DolphinRunException:" + i);
		} catch (Exception ex) {
			System.out.println("Exception:" + i);
		}
	}

}

class DolphinRunException extends RuntimeException {
	
}
