package com.tencent.java.kilim.intro;

import kilim.*;

/**
 * 参考文章：kilim简介：https://www.ibm.com/developerworks/cn/java/j-javadev2-7.html
 * @author andy
 *
 */
public class CalculationCooperation {
	public static void main(String[] args) {
		Mailbox<Calculation> sharedMailbox = new Mailbox<Calculation>();

		Task deferred = new DeferredDivision(sharedMailbox);
		Task calculator = new Calculator(sharedMailbox);

		deferred.start();
		calculator.start();

	}
}
