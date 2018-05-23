package com.tencent.java.multithread;

/**
 * http://www.cnblogs.com/dolphin0520/p/3920407.html
 * 1. thread local 是在各线程中复制一份,存在当前线程的一个threadlocas的ThreadLocalMap变量中;
 * 2. 在各个线程中操作threadlocal变量,互不影响;
 *3. get之前必须先set或者实现initialValue()方法,否则会报空指针.
 * Created by andy on 2018/5/23.
 */
public class ThreadlocalTest2 {

    ThreadLocal<Long> longLocal = new ThreadLocal<Long>() {
        protected Long initialValue() {
            return Thread.currentThread().getId();
        };
    };
    ThreadLocal<String> stringLocal = new ThreadLocal<String>() {
        protected String initialValue() {
            return Thread.currentThread().getName();
        };
    };


    public void set() {
        longLocal.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName());
    }

    public long getLong() {
        return longLocal.get();
    }

    public String getString() {
        return stringLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
        final ThreadlocalTest2 test = new ThreadlocalTest2();


//        test.set();
        System.out.println(test.getLong());
        System.out.println(test.getString());


        Thread thread1 = new Thread(){
            public void run() {
                test.set();
                System.out.println(test.getLong());
                System.out.println(test.getString());
            };
        };
        thread1.start();
        thread1.join();

        System.out.println(test.getLong());
        System.out.println(test.getString());
    }
}
