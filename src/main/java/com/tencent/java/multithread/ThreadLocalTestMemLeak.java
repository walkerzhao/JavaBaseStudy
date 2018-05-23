package com.tencent.java.multithread;

import java.text.DecimalFormat;

/**
 * TODO:多线程模拟,一个threadlocal的对象,里面有list的数组,线程不断向list中丢东西,而不执行remove操作,会不会导致内存泄漏.
 * Created by andy on 2018/5/23.
 */
public class ThreadLocalTestMemLeak {

    public static final int _10MB = 1024  * 1024 * 10;

    public static ThreadLocal<Object> threadLocal = new ThreadLocal(){
        @Override
        public Object initialValue(){
            return null;
        }
    };

    public static void main(String args[]){
        System.out.println();
//        displayAvailableMemory();
        for(;;){
            new Thread(){
                @Override
                public void run() {
                    byte[] data = new byte[_10MB];
                    threadLocal.set(data);
                }
            }.start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            displayAvailableMemory();
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
