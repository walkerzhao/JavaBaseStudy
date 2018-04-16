package com.tencent.java.designpattern;

import java.util.HashSet;

/**
 * 单例模式:http://wuchong.me/blog/2014/08/28/how-to-correctly-write-singleton-pattern/
 * @author ewanzhao
 *
 */
public class SingletonPattern {
	
	public SingletonPattern() {
		super();
	}

	private static SingletonPattern singletonPattern;
	
	private volatile static SingletonPattern singletonPatternWithVolatile;
	
	private static final SingletonPattern singletonPattern2 = new SingletonPattern();
	
	/**
	 * 懒汉式，线程不安全 ; 
	 * 懒加载，调用的时候，才创建对象
	 * 多线程 同时get 对象的时候，会create 多个对象
	 * @return
	 */
	public static SingletonPattern getInstance() {
		if(singletonPattern == null) {
			singletonPattern = new SingletonPattern();
		}
		return singletonPattern;
	}
	
	/**
	 * 线程安全
	 * 同步
	 * 不是很高效，只有第一次去创建对象的时候才需要同步
	 * @return
	 */
	public static synchronized SingletonPattern getInstanceThreadSafe() {
		if(singletonPattern == null) {
			singletonPattern = new SingletonPattern();
		}
		return singletonPattern;
	}
	
	/**
	 * 这种同步效率不高，每次拿对象的时候，都会调同步代码块
	 * 大部分情况下，都是创建好的对象，所以有了下面的double check
	 * @return
	 */
	public static  SingletonPattern getInstanceWithSync() {
		synchronized(SingletonPattern.class) {
			if(singletonPattern == null) {
				singletonPattern = new SingletonPattern();
			}
		}
		return singletonPattern;
	}
	
	
	
	
	/**
	 * double check 获取对象
	 * 但是singletonPattern 存在指令重排.
	 * new操作的3个步骤：1.分配内存，2.调用构造函数，3.赋值（将对象指向分配的内存空间，执行完这个操作就是非null了）
	 * cpu会进行指令重排序(1/3/2). 同时有其它线程判断singletonPattern 为非null ，就会返回一个没有调用构造函数的对象
	 * @return
	 */
	public static  SingletonPattern getInstanceDoubleCheck() {
		if(singletonPattern == null) {   //first check
			synchronized (SingletonPattern.class) {
				if(singletonPattern == null) {
					singletonPattern = new SingletonPattern();
				}
			}
			
		}
		return singletonPattern;
	}
	
	/**
	 * volatile  保证有序性
	 * @return
	 */
	public static  SingletonPattern getInstanceDoubleCheckWithVolatile() {
//		HashSet<E>
		if(singletonPatternWithVolatile == null) {   //first check
			synchronized (SingletonPattern.class) {
				if(singletonPatternWithVolatile == null) {
					singletonPatternWithVolatile = new SingletonPattern();
				}
			}
			
		}
		return singletonPatternWithVolatile;
	}
	
	/**
	 * 饿汉式，加载类的时候，就初始化；
	 * 不够灵活，比如实例创建是通过配置文件或者参数 来传递参数
	 * @return
	 */
	public static SingletonPattern getInstance2() {
		return singletonPattern2;
	}
	
	/**
	 * 静态内部类的方式
	 * @author ewanzhao
	 *
	 */
	private static class SingletonHolder {  
        private static final SingletonPattern INSTANCE = new SingletonPattern();  
    }  
	
	/**
	 * 静态内部类的方式
	 * 由于 SingletonHolder 是私有的，除了 getInstance() 之外没有办法访问它，因此它是懒汉式的；同时读取实例的时候不会进行同步，没有性能缺陷；也不依赖 JDK 版本
	 * @return
	 */
	public static final SingletonPattern getInstanceWithInnerClass() {  
        return SingletonHolder.INSTANCE; 
    } 
	
	
	
	
	
	
	
	

}
