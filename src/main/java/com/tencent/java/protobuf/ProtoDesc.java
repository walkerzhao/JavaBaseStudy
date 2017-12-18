//package com.tencent.java.protobuf;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.codehaus.jackson.map.ObjectMapper;
//
//import com.alibaba.fastjson.JSON;
//import com.google.protobuf.DescriptorProtos.FileDescriptorProto;
//import com.google.protobuf.DescriptorProtos.FileDescriptorSet;
//import com.google.protobuf.Descriptors.Descriptor;
//import com.google.protobuf.Descriptors.FieldDescriptor;
//import com.google.protobuf.Descriptors.FieldDescriptor.JavaType;
//import com.google.protobuf.Descriptors.FileDescriptor;
//
///**
// * http://blog.csdn.net/lufeng20/article/details/8736584
// * http://www.blogjava.net/DLevin/archive/2015/04/01/424012.aspx
// * descriptor_set_out 识别列表
// * protoc.exe --descriptor_set_out=addressbook.desc addressbook.proto
// * @author andy
// *
// */
//public class ProtoDesc {
//	public static void main(String[] args) throws IOException, Exception {
//		/*
//		 * Runtime run = Runtime.getRuntime();
//		 * 
//		 * String dir = System.getProperty("user.dir"); String source = dir +
//		 * "/protoc/"; System.out.println(source); String cmd = "cmd /c " +
//		 * source + "protoc.exe -I=" + source + " --descriptor_set_out="+ source
//		 * +"addressbook.desc "+ source +"addressbook.proto";
//		 * System.out.println(cmd);
//		 * 
//		 * Process p = run.exec(cmd);
//		 * 
//		 * // 如果不正常终止, 则生成desc文件失败 if (p.waitFor() != 0) { if (p.exitValue() ==
//		 * 1) {//p.exitValue()==0表示正常结束，1：非正常结束 System.err.println("命令执行失败!");
//		 * System.exit(1); } }
//		 */
//
//		Map<String, String> mapping = new HashMap<String, String>();
//
//		FileInputStream fin = new FileInputStream("addressbook.desc");
//		FileDescriptorSet descriptorSet = FileDescriptorSet.parseFrom(fin);
//
//		for (FileDescriptorProto fdp : descriptorSet.getFileList()) {
//			FileDescriptor fd = FileDescriptor.buildFrom(fdp, new FileDescriptor[] {});
//			System.out.println(fd.getFullName());
//			for (Descriptor descriptor : fd.getMessageTypes()) {
//				System.out.println(descriptor.getName()); // Message名字
//				Map<String, Object> result = descriptorDesc(descriptor);
//				System.out.println(result);
//				//map转json数据
//				ObjectMapper objectMapper = new ObjectMapper();
//				System.out.println(objectMapper.writeValueAsString(result));
////				String jsonString = JSON.toJSONString(result);
//				
//			}
//
//		}
//
//		// Descriptor md = fd.getDescriptorForType();
//		// byte[] data = null ;
//		// DynamicMessage m = DynamicMessage.parseFrom(md, data);
//
//	}
//
//	public static Map<String, Object>  descriptorDesc(Descriptor descriptor) {
//		// String className = fdp.getOptions().getJavaPackage() + "."
//		// + fdp.getOptions().getJavaOuterClassname() + "$"
//		// + descriptor.getName();
//		
//		List<FieldDescriptor> types = descriptor.getFields();
//		Map<String, Object> result = new HashMap<String, Object>();
//		for (FieldDescriptor type : types) {
//			// System.out.println(type);
////			System.out.println(type.isRepeated());
////			System.out.println(type.getLiteType() );
////			System.out.println(type.getFile());
//			JavaType javaType = type.getJavaType();
//			String name = type.getName();
//			Object defaultValue = null;
//			if(type.isRepeated()) {   //数组类型
//				List<Object> repeatData = new ArrayList<Object>();
//				if (javaType == JavaType.MESSAGE) {    //如果是嵌套类型
//					defaultValue = descriptorDesc(type.getMessageType());
//					repeatData.add(defaultValue);
//					result.put(name, repeatData);
//				} else {  //普通类型的repeated
//					defaultValue = type.getDefaultValue();
//					repeatData.add(defaultValue);
//					result.put(name, defaultValue);
//				}
//			} else {   //不是数组
//				if (javaType == JavaType.MESSAGE) {    //如果是嵌套类型
//					defaultValue = descriptorDesc(type.getMessageType());
//					result.put(name, defaultValue);
//				} if(javaType == javaType.ENUM){   //枚举类型
//					defaultValue = String.valueOf(type.getDefaultValue());
//					result.put(name, defaultValue);
//				}	else {
//					defaultValue = type.getDefaultValue();
//					result.put(name, defaultValue);
//				}
//				
//			}
//
//		}
//		
//		return result;
//
//	}
//
//}
