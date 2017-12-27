package com.tencent.java.jvm;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

/**
 * jvm启动参数调整
 * a.txt大文件，shell批量写入：➜  
 * file git:(master) ✗ for i in {1..1000000}
for> do
for> echo "line$i" >> a.txt
for> done

参数设置：http://blog.csdn.net/chenleixing/article/details/43230527
-Xms20m -Xmx20m -Xmn1m

1. jps找到进程号
2. jmap -heap pid 查看进程的内存分配，打印不出来，可能是权限的问题
3. 查看gc频率：jstat -gcutil 11024 1000 10
 * 
 * @author andy
 *
 */
public class JvmArgu {
	public static void main(String[] args) throws IOException, InterruptedException {
		testLineIterator();
	}

	private static void testLineIterator() throws IOException, InterruptedException {
		long start = System.currentTimeMillis();
		File file = new File("file/" + "a.txt");
		LineIterator lineIter = FileUtils.lineIterator(file);
		while (lineIter.hasNext()) {
			System.out.println(lineIter.nextLine());
			Thread.sleep(20);
		}
		lineIter.close();
		long end = System.currentTimeMillis();
		System.out.println("cost:"+(end-start));
		
	}

}
