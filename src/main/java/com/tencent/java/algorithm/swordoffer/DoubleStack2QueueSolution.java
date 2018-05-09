package com.tencent.java.algorithm.swordoffer;

import java.util.Stack;

/**
 * 双栈实现一个队列
 * 栈 先进后出
 * 队列 先进先出
 * 一个栈用来存储数据--push
 * 另外一个栈用来 pop操作，当没有数据的时候，从stack1中pop数据到栈2，从栈2中取数据即可
 */
public class DoubleStack2QueueSolution {

    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);

    }

    public int pop() {

        return 0;
    }
}
