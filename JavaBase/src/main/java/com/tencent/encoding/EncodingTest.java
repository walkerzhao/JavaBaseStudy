package com.tencent.encoding;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class EncodingTest {
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		stringTest("hello,world");
		System.out.println("###############################");
		
		stringTest("腾讯");
		System.out.println("###############################");
	}

	private static void stringTest(String str) throws UnsupportedEncodingException {
		System.out.println("str:"+str);
		String strEncoding = Charset.defaultCharset().name();
		System.out.println("default encoding:"+ strEncoding);    //修改文件编码，可以调整这里的默认编码
		str2bytes(str);
		str2bytes(str, "GB2312");
		str2bytes(str, "UTF-8");
		str2bytes(str, "UTF-16");
		str2bytes(str, "Unicode");	
	}
	
	
	private static void str2bytes(String str, String encoding) throws UnsupportedEncodingException {
		byte[] byteStrGBK = str.getBytes(encoding);
		System.out.println("encoding:" + encoding + " byte size:" +byteStrGBK.length);
		printByte(byteStrGBK);		
	}
	
	private static void str2bytes(String str) throws UnsupportedEncodingException {
		byte[] byteStrGBK = str.getBytes();
		System.out.println(" byte size:" +byteStrGBK.length);
		printByte(byteStrGBK);		
	}

	public static void printByte(byte[] bytestr) {
		for(int i =0; i < bytestr.length; i++) {
			System.out.print(String.format("%02x", bytestr[i]) +" ");
			
		}
		System.out.println();
	}

}
