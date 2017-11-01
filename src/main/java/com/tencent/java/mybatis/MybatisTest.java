package com.tencent.java.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisTest {
	public static void main(String[] args) throws IOException {
		System.out.println("mybatis test");
		
		String resource = "com/tencent/java/mybatis/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession session = sqlSessionFactory.openSession();
		try {
			
			//获取指定mapper的session
			//可以使用Guice的注解方式来获取
		  BlogMapper mapper = session.getMapper(BlogMapper.class);
		  List<Map<String, Object>> blog = mapper.selectBlog(101);
		  
		  System.out.println(blog);
		} finally {
		  session.close();
		}
	}

}
