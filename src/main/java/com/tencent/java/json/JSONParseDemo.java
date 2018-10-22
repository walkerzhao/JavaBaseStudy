package com.tencent.java.json;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

/**
 * @description: add your desc
 * @author: ewanzhao
 * @create: 2018-10-22 16:16
 **/

public class JSONParseDemo {
	public static void main(String[] args) {
		String state = "{\"uri\":\"10001\",\"appid\":\"1005\",\"byPass\":2,\"domain\":\"nimo.tv\",\"strategy\":\"function(j){}\"}";
		testStateJson(state);


		String state2 = "{uri:10001,appid:1005,byPass:2,domain:nimo.tv,strategy:function(j){}}";
		testStateJson(state2);

	}

	private static void testStateJson(String state) {
		Gson gson = new Gson();
		Map<String, Object> retMap = gson.fromJson(state, new TypeToken<Map<String, Object>>() {
		}.getType());
		System.out.println(retMap);
	}
}
