package com.tencent.java.protobuf;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.googlecode.protobuf.format.JsonFormat;
import com.googlecode.protobuf.format.util.TextUtils;
import com.tencent.java.proto.HoroscopeSnippetProtos.HoroscopeSnippet;

/**
 * 一个json文件转换为pb数据
 * @author ewanzhao
 *
 */
public class JsonFile2Proto {
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		//PB生成json字符串
		
		String json = pb2Json();
		
		jsonFile2String();
		
		json2Pb(json);								
		
	}

	private static void json2Pb(String json) throws IOException {
		//* json转pb
		HoroscopeSnippet.Builder msg = HoroscopeSnippet.newBuilder();
		JsonFormat jsonFormat = new JsonFormat();
		jsonFormat.merge(TextUtils.toInputStream(json), msg);
		System.out.println(msg);
//		jsonFormat.merge(json, msg);
		
	}

	private static void jsonFile2String() throws JsonParseException, JsonMappingException, IOException {
		//* 读取json文件
		ObjectMapper mapper = new ObjectMapper();		
		Object json = mapper.readValue(new File("./json/test.json"), Object.class);
		System.out.println(json);
		
	}

	/**
	 * pb生成json文件
	 */
	private static String pb2Json() {
		HoroscopeSnippet.Builder msg = HoroscopeSnippet.newBuilder();
		msg.setAuthor("ewanzhao");
		System.out.println(msg);
		
		HoroscopeSnippet test = msg.build();
		JsonFormat jsonFormat = new JsonFormat();
		String asJson = jsonFormat.printToString(test);
		System.out.println(asJson);
		return asJson;
		
	}

}
