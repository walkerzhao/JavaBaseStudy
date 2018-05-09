package com.tencent.java.collections;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.logging.log4j.core.lookup.MainMapLookup;

/**
 * 一致性哈希的研究：https://www.cnblogs.com/xrq730/p/5186728.html 一致性哈希的深入研究。
 * 衡量一致性哈希的四个标准：平衡性、单调性、分散性、负载。 节点组成了一个环，数据的哈希值 落在环上，离它顺时针最近的一个点上。这就是一致性哈希算法。
 * 
 * @author andy
 * @param <T>
 *
 */
public class ConsistentHash<T> {
	private final HashFunction hashFunction;

	private final int numberOfReplicas;// 节点的复制因子,实际节点个数 * numberOfReplicas =
										// 虚拟节点个数

	private final SortedMap<Long, T> circle = new TreeMap<Long, T>();// 存储虚拟节点的hash值到真实节点的映射

	public static void main(String[] args) {
		HashFunction hashFunction = new HashFunction();
		System.out.println(hashFunction.hash("hello"));

	}

	public ConsistentHash(HashFunction hashFunction, int numberOfReplicas, Collection<T> nodes) {
		super();
		this.hashFunction = hashFunction;
		this.numberOfReplicas = numberOfReplicas;
		for (T node : nodes)
			add(node);
	}

	public void add(T node) {
		for (int i = 0; i < numberOfReplicas; i++)
			// 对于一个实际机器节点 node, 对应 numberOfReplicas 个虚拟节点
			/*
			 * 不同的虚拟节点(i不同)有不同的hash值,但都对应同一个实际机器node
			 * 虚拟node一般是均衡分布在环上的,数据存储在顺时针方向的虚拟node上
			 */
			circle.put(hashFunction.hash(node.toString() + i), node);
	}
	
	public void remove(T node) {
		for(int i=0; i<numberOfReplicas; i++) {
			circle.remove(hashFunction.hash(node.toString()+i));
		}
	}
	
	
	   /*
     * 获得一个最近的顺时针节点,根据给定的key 取Hash
     * 然后再取得顺时针方向上最近的一个虚拟节点对应的实际节点
     * 再从实际节点中取得 数据
     */
    public T get(Object key) {
        if (circle.isEmpty())
            return null;
        long hash = hashFunction.hash((String) key);// node 用String来表示,获得node在哈希环中的hashCode
        if (!circle.containsKey(hash)) {//数据映射在两台虚拟机器所在环之间,就需要按顺时针方向寻找机器
            SortedMap<Long, T> tailMap = circle.tailMap(hash);
            hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        }
        return circle.get(hash);
    }

    public long getSize() {
        return circle.size();
    }
    
    /*
     * 查看MD5算法生成的hashCode值---表示整个哈希环中各个虚拟节点位置
     */
    public void testBalance(){
        Set<Long> sets  = circle.keySet();//获得TreeMap中所有的Key
        SortedSet<Long> sortedSets= new TreeSet<Long>(sets);//将获得的Key集合排序
        for(Long hashCode : sortedSets){
            System.out.println(hashCode);
        }
        
        System.out.println("----each location 's distance are follows: ----");
        /*
         * 查看用MD5算法生成的long hashCode 相邻两个hashCode的差值
         */
        Iterator<Long> it = sortedSets.iterator();
        Iterator<Long> it2 = sortedSets.iterator();
        if(it2.hasNext())
            it2.next();
        long keyPre, keyAfter;
        while(it.hasNext() && it2.hasNext()){
            keyPre = it.next();
            keyAfter = it2.next();
            System.out.println(keyAfter - keyPre);
        }
    }
    

	
	

}

class HashFunction {
	private MessageDigest md5 = null;

	/**
	 * 使用一个key的MD5值来保证哈希算法的平衡性
	 * 
	 * @param key
	 */
	public long hash(String key) {
		if (md5 == null) {
			try {
				md5 = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		md5.reset();
		md5.update(key.getBytes());
		byte[] bkey = md5.digest();
		long result = ((bkey[3] & 0xFF) << 24) + ((bkey[2] & 0xFF) << 16) + ((bkey[1] & 0xFF) << 8) + (bkey[0] & 0xFF);

		return result;
	}

	public static void main(String[] args) {

	}

}
