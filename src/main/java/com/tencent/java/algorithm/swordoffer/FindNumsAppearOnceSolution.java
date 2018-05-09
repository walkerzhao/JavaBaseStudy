package com.tencent.java.algorithm.swordoffer;

/**
 * 数组中，只有2个元素只出现一次，其他元素都是出现两次
 * 找出这两个数
 * 
 * 思路--一个数的情况，是通过异或操作就可以实现
 * 两个数，异或的结果，是两个不同数的结果
 * 通过对数组划分，划分成两个子数组，两个不同的数分在不同的数组里，这样通过上面的方法即可。
 * 划分数组 是通过异或的结果，取一个位数为1的位，然后通过这个位 来划分数组
 * 
 * https://blog.csdn.net/ouyangyanlan/article/details/72668012
 * @author ewanzhao
 *
 */
public class FindNumsAppearOnceSolution {
	
	public static void main(String[] args) {
		System.out.println("hello,world");
		int [] array = {1,1,2,2,3,6};
		int[] num1 = {};
		int[] num2 = {};
		findNumsAppearOnce(array, num1, num2);
	}
	
	public static void findNumsAppearOnce(int [] array,int num1[] , int num2[]) {
		//进行异或操作
		int ret = calYiHuoRet(array);
		System.out.println(ret);
		
		//找出两个数不同的位数
		int index = getIndex(ret);
		System.out.println(index);
		
		int data1 = 0;
		int data2 = 0;
		for(int i=0; i<array.length; i++) {
			if( isBit1(array[i], index) ) {
				data1 = data1 ^ array[i]; 
			} else {
				data2 = data2 ^ array[i];
			}
		}
		System.out.println(data1 + ":" + data2);
	}

	private static boolean isBit1(int data, int index) {

		data = data >> index;
		boolean ret = ((data&1) == 1);
		
		return ret;
	}

	private static int getIndex(int ret) {
		int index = 0;
		while(true) {
			if((ret&1) == 0) {  //地位为0，做位移操作
				ret = ret >> 1;
			} else {
				break;
			}
			index++;
		}
		return index;
	}

	private static int calYiHuoRet(int[] array) {
		int ret = 0;
 		for(int i=0; i<array.length; i++) {
			ret ^= array[i];
		}
		return ret;
	}

}
