package com.tencent.java.protobuf;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.text.WordUtils;

import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.GeneratedMessage;
import com.googlecode.protobuf.format.JsonFormat;
import com.tencent.java.proto.HoroscopeSnippetProtos.HoroscopeSnippet;

public class Protobuf2Json {
	
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException, SecurityException {
		HoroscopeSnippet.Builder msg = HoroscopeSnippet.newBuilder();
		msg.setAuthor("ewanzhao");
		
		HoroscopeSnippet test = msg.build();
		JsonFormat jsonFormat = new JsonFormat();
		String asJson = jsonFormat.printToString(test);
		System.out.println(asJson);
//		System.out.println(test);
//		System.out.println(generateJsonFromMsg(msg.build()));
	}
	
	//msg is any protobuf object 

//	public static String generateJsonFromMsg(GeneratedMessage msg) throws    IllegalAccessException, IllegalArgumentException, InvocationTargetException,  ClassNotFoundException, NoSuchMethodException, SecurityException{
//
//	    Map<FieldDescriptor,Object> allFields = msg.getAllFields();
//	    System.out.println(allFields);
//	    StringBuilder sb = new StringBuilder();
//	    boolean isRepeated = false;
//	    Class<?> c = Class.forName(msg.getClass().getName());
//
//	    for(FieldDescriptor field: allFields.keySet()){
//	        String fieldName = field.getName();
//	        sb.append("\"")
//	          .append(fieldName)
//	          .append("\"")
//	          .append(":");
//
//	        isRepeated = field.isRepeated();
//
//	        if(isRepeated){
//	            fieldName = fieldName + "List";
//	        }
//	        try{
//	            Method method = c.getDeclaredMethod ("get"+ WordUtils.capitalize(fieldName ,new char [] {'_'}).replace("_", "") );
//	            Object value = method.invoke(msg);
//
//	            if(value instanceof GeneratedMessage){
//	                if(!isRepeated) {
//	                     sb.append("{");
//	                }
//
//	                sb.append(generateJsonFromMsg((GeneratedMessage)value));
//
//	                if(!isRepeated) {
//	                     sb.append("}");
//	                }
//	            } else {
//	                if(isRepeated){
//	                    sb.append("[")
//	                      .append(generateJsonFromList((List)value))
//	                      .append("]");
//	                } else {
//	                    sb.append("\"")
//	                      .append(value)
//	                      .append("\"");
//	                }
//	            }
//	        }catch(Exception e){
//	            System.out.println(e);
//	        }
//
//	        sb.append(",");
//	    }
//
//	    return sb.deleteCharAt(sb.length() -1).toString();
//	}
//	
//	
//	private static String generateJsonFromList(List list) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException, SecurityException{
//		StringBuilder sb = new StringBuilder();
//	    int size = list.size();
//	    for(int i=0 ; i < size ; i++){
//	        Object item = list.get(i);
//
//	        if(item instanceof GeneratedMessage){
//	            sb.append("{")
//	              .append(generateJsonFromMsg((GeneratedMessage)(item)))
//	              .append("}");
//	        } else { 
//	            sb.append(item);
//	        }
//	        if(i!=size-1){
//	            sb.append(",");
//	        }
//	    }
//	    return sb.toString();
//	}  

}
