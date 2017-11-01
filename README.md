# JavaBaseStudy
## java的基础学习


## java log
### log4j2 --demo:com.tencent.java.log4j2
* 相关依赖--运行参考：http://blog.csdn.net/u011389474/article/details/70054256
* 配置解析：http://www.cnblogs.com/hafiz/p/6170702.html http://blog.csdn.net/lu8000/article/details/25754415
* 关键点：appender(输出的地方、日志级别、滚动方式等配置)、loggers(类、标签等 选择输出的appender)
### 测试结论FAQ
* loggers中的loggers和root之间的关系是什么？
是继承的关系，如果loggers中没有配置appender，会继承root的配置。如果配置了additivity="false"，表示只在这个logger中的appender里输出；
* 各个地方的日志level的关系是什么？
按照最具体的类的level为准,继承自root，但是会覆盖root, 然后appender的level再次过滤。
* loggers中的name 包的名字的作用范围是什么？


## mybatis使用
* 相关依赖  mysql-connector/mybatis ,运行参考：http://www.mybatis.org/mybatis-3/zh/getting-started.html
* MyBatis 允许你在已映射语句执行过程中的某一点进行拦截调用； jungle_db中有_log