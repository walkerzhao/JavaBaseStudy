# JavaBaseStudy
## java的基础学习


## java log
### log4j2 --demo:com.tencent.java.log4j2
* 相关依赖--运行参考：http://blog.csdn.net/u011389474/article/details/70054256
* 配置解析：http://www.cnblogs.com/hafiz/p/6170702.html
* 关键点：appender(输出的地方、日志级别、滚动方式等配置)、loggers(类、标签等 选择输出的appender)
### 测试结论FAQ
* loggers中的loggers和root之间的关系是什么？
是继承的关系，如果loggers中没有配置appender，会继承root的配置。如果配置了additivity="false"，表示只在这个logger中的appender里输出；
* 各个地方的日志level的关系是什么？