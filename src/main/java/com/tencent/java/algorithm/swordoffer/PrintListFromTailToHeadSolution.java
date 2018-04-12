package com.tencent.java.algorithm.swordoffer;

import java.util.ArrayList;
import java.util.Collections;


class ListNode {
     int val;
     ListNode next = null;

     ListNode(int val) {
         this.val = val;
     }
 }



public class PrintListFromTailToHeadSolution {

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> result = new ArrayList<Integer>();

        ListNode node = listNode;
        while(node != null) {
            result.add(node.val);
            node = node.next;
        }
        Collections.reverse(result);
        return result;

    }
}
