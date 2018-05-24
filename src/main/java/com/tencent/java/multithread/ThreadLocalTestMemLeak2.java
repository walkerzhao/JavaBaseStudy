package com.tencent.java.multithread;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 多线程模拟,一个threadlocal的对象,里面有list的数组,是否有remove操作，对于内存的影响，是否有内存泄露
 * Created by andy on 2018/5/23.
 */
public class ThreadLocalTestMemLeak2 {
    static ThreadLocal<UdbLog> udblog;

    public static void main(String args[]){
        System.out.println("hello,world");

        //初始化
        ThreadLocal<UdbLog> udblog = new ThreadLocal() {
            @Override
            public Object initialValue(){
                List<Long> list = new ArrayList<>();
                list.add(1L);
                list.add(2L);
                UdbLog udbLog = new UdbLog(list);
                return udbLog;
            }
        };

        ThreadLocalTestMemLeak2.udblog = udblog;
        System.out.println(ThreadLocalTestMemLeak2.udblog.get());


        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
        int index=1;
        Runnable thread1 = new AddThreadLocalListThread1();   //线程中，不做remove操作
        Runnable thread2 = new AddThreadLocalListThread2();   //线程中，执行remove操作
        for(int i=1; i<100000; i++) {   //线程执行10次 add操作
            if(i %2 == 0) {
                fixedThreadPool.execute(thread1);
            } else {
                fixedThreadPool.execute(thread2);
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class AddThreadLocalListThread1 implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println("thread name:"+Thread.currentThread().getName());
                UdbLog log = udblog.get();
                log.list.add(1L);
                System.out.println("list size:" + log.list.size());
                Thread.sleep(2000);

                //某个必现的情况，导致抛出空指针
                if(true) {
                    throw new NullPointerException("意外必现空指针");
                }
                udblog.remove();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                System.out.println("exception:" + e);
            }
        }
    }

    static class AddThreadLocalListThread2 implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println("thread name:"+Thread.currentThread().getName());
                UdbLog log = udblog.get();
                log.list.add(1L);
                System.out.println("list size:" + log.list.size());
                Thread.sleep(2000);

                //某个必现的情况，导致抛出空指针
                if(true) {
                    throw new NullPointerException("意外必现空指针");
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                System.out.println("exception:" + e);
            } finally {
                udblog.remove();
            }
        }
    }


    public static void displayAvailableMemory() {
        DecimalFormat df = new DecimalFormat("0.00") ;

        //显示JVM总内存
        long totalMem = Runtime.getRuntime().totalMemory();
        //空闲内存
        long freeMem = Runtime.getRuntime().freeMemory();
        //显示JVM尝试使用的最大内存
        long maxMem = Runtime.getRuntime().maxMemory();
        System.out.println("total:"+ df.format(totalMem/1000_000F) + " MB"
                + " free:" + df.format(freeMem/1000_000F) + " MB"
                + " max:" + df.format(maxMem/1000_000F) + " MB");



        System.out.println();
    }
}

class UdbLog {
    List<Long> list;

    @Override
    public String toString() {
        return "UdbLog{" +
                "list=" + list +
                '}';
    }

    public UdbLog(List<Long> list) {
        this.list = list;
    }
}
