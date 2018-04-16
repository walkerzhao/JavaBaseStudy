package com.tencent.java.algorithm.swordoffer;

/**
 * 替换字符串中的空格信息
 * https://blog.csdn.net/jsqfengbao/article/details/47044473
 */
public class ReplaceSpaceSolution {
    public static void main(String[] args) {
        StringBuffer str = new StringBuffer("hello world");
        String newStr = replaceSpace(str);
        System.out.println(newStr);
    }


    /**
     * 先遍历出长度
     * 替代后的长度，从字符串的末尾开始拷贝遇到空格，做替换即可。
     * 不能申请新的buffer
     * @param str
     * @return
     */
    public static String replaceSpace(StringBuffer str) {
        if(str == null || str.length()==0) {
            return str.toString();
        }
        int length = str.length();
        int spaceNum = 0;
        for(int i=0; i< length; i++) {
            if(str.charAt(i) == ' ') {
                spaceNum++;
            }
        }

        int newLength = length + spaceNum*2;
        //给字符串扩容
        str.setLength(newLength);

        int oldIndex = length-1;
        int newIndex = newLength -1;

        while(true) {
            if(str.charAt(oldIndex) == ' ') {
                str.setCharAt(newIndex, '0');
                str.setCharAt(newIndex-1, '2');
                str.setCharAt(newIndex-2, '%');
                newIndex -= 3;
                oldIndex--;
            } else {
                str.setCharAt(newIndex, str.charAt(oldIndex));
                newIndex--;
                oldIndex--;
            }

            if(oldIndex == -1) {
                break;
            }
        }
        return str.toString();

    }
}
