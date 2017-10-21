package com.tencent.java.log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * log4j2.xml的配置文件学习
 * @author andy
 * 
 * http://www.cnblogs.com/hafiz/p/6170702.html

 */
public class Log4j2Test {
	private static Logger logger = LogManager.getLogger(Log4j2Test.class);
	public static void main(String[] args) {
		System.out.println("hello,world");
		logger.debug("hello,world--debug log");
		logger.info("hello,world--info log");
		logger.warn("hello,world--warn log");
		logger.error("hello,world--error log");
	}

}
