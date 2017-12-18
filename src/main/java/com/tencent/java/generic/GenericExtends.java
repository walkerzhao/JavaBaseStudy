package com.tencent.java.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试泛型的继承
 * http://blog.csdn.net/ShierJun/article/details/51253870
 * @author andy
 *
 */
public class GenericExtends {
	public static void main(String[] args) {
		ArrayList<Long> data = new ArrayList<>();
		data.add(416548283L);
		Son7 son = new Son7(data);
		List<Long> after = son.test(data);
		System.out.println(after);
	}
}

class Father<T> {
	T data;

	public Father(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Father [data=" + data + "]";
	}

}

class Son1<T> extends Father<T> {// 最正常的继承，子类的泛型参数和父类的参数是一致的

	public Son1(T data) {
		super(data);
	}

	@Override
	public String toString() {
		return "Son1 [data=" + data + "]";
	}

}

// 父类指定了类型，子类又增加了，这时子类的只是新增加的泛型参数，跟父类没有关系
class Son5<T> extends Father<Integer> {
	T otherData;

	public Son5(Integer data, T otherData) {
		super(data);
		this.otherData = otherData;
	}

	@Override
	public String toString() {
		return "Son5 [otherData=" + otherData + ", data=" + data + "]";
	}

}


class Son6<Integer> extends Father<Integer> {  
	  
    Integer otherData;// 它是什么类型呢？java.lang.Integer？NONONO 只能说不确定！  
  
    public Son6(Integer data, Integer otherData) {  
        super(data);  
        this.otherData = otherData;  
    }  
  
    @Override  
    public String toString() {  
        return "Son6 [otherData=" + otherData + ", data=" + data + "]";  
    }  
  
}  


class Son7 extends Father<ArrayList<Long>> {  
	  
    public Son7(ArrayList<Long> data) {
		super(data);
		// TODO Auto-generated constructor stub
	}

	Integer otherData;// 它是什么类型呢？java.lang.Integer？NONONO 只能说不确定！  
  
//    public Son7(Integer data, Integer otherData) {  
////        super(data);  
//        this.otherData = otherData;  
//    }  
  
    @Override  
    public String toString() {  
        return "Son6 [otherData=" + otherData + ", data=" + data + "]";  
    }  
    
    public ArrayList<Long> test(ArrayList<Long> fans) {
    	ArrayList<Long> afterfans = new ArrayList<Long>();
    	for(Long fan : fans) {
    		afterfans.add(fan);
    	}

		return data;
    	
    }
  
} 
