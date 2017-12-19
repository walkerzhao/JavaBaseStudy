package com.tencent.java.generic;

import java.lang.reflect.ParameterizedType;
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
//		Son1 test = new Son1<Long>(1L);
//		System.out.println(test.getConfigType());
		
		SubSon1 test3 = new SubSon1(1L);
		System.out.println(test3.getConfigType());
		
		
		ArrayList<Long> data = new ArrayList<>();
		data.add(416548283L);
		Son7 son = new Son7(data);
		System.out.println(son.getConfigType());
//		List<Long> after = son.test(data);
//		System.out.println(after);
		
		
		Son2 test2 = new Son2(1L);
		System.out.println(test2.getConfigType());
	}
}

abstract class Father<T> {
	T data;

	public Father(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Father [data=" + data + "]";
	}
	
	public Class<T> getConfigType() {
		ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
		System.out.println("type:"+type);
		return (Class<T>) (type).getActualTypeArguments()[0];
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

class SubSon1 extends Son1<Long> {// 最正常的继承，子类的泛型参数和父类的参数是一致的

	public SubSon1(Long data) {
		super(data);
	}

	@Override
	public String toString() {
		return "Son1 [data=" + data + "]";
	}

}

class Son2 extends Father<Long> {// 最正常的继承，子类的泛型参数和父类的参数是一致的

	public Son2(Long data) {
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


class Son7 extends Father<ArrayList> {  
	  
    public Son7(ArrayList data) {
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
