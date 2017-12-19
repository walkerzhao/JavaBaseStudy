package com.tencent.java.log4j2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerTest  {
	
	public static void main(String[] args) {
		Test test = new Test();
		test.getPath();
//		loggerTest.logger.debug("hello,world");
	}
}

class Test extends Father{

	final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public String getPath() {
		String path = "test";
		logger.debug("test:{}", path);
		return path;
	}
}


abstract class Father {
	final Logger logger = LoggerFactory.getLogger(getClass());
	public abstract String getPath();
}
