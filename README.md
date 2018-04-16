# JavaBaseStudy
## java的基础学习


## java log
### log4j2 --demo:com.tencent.java.log4j2
* 相关依赖--运行参考：http://blog.csdn.net/u011389474/article/details/70054256
* 配置解析：http://www.cnblogs.com/hafiz/p/6170702.html http://blog.csdn.net/lu8000/article/details/25754415
* 关键点：appender(输出的地方、日志级别、滚动方式等配置)、loggers(类、标签等 选择输出的appender)
### 测试结论FAQ
* loggers中的loggers和root之间的关系是什么？

是继承的关系，如果loggers中没有配置appender，会继承root的配置，如果配置了，会覆盖父类的配置。如果配置了additivity="false"，表示只在这个logger中的appender里输出；
* 各个地方的日志level的关系是什么？
按照最具体的类的level为准,继承自root，但是会覆盖root, 然后appender的level再次过滤。
* loggers中的name 包的名字的作用范围是什么？


## mybatis使用
* 相关依赖  mysql-connector/mybatis ,运行参考：http://www.mybatis.org/mybatis-3/zh/getting-started.html
* MyBatis 允许你在已映射语句执行过程中的某一点进行拦截调用； jungle_db中有_log


## java多线程 @com.tencent.java.multithread
参考java多线程中常见的问题：http://www.importnew.com/18459.html
* 死锁 @com.tencent.java.multithread.DeadLockDemo：什么是死锁？ 死锁demo；怎么排查死锁；怎么避免死锁？   参考文章：http://www.importnew.com/9668.html
* 多线程的作用：发挥多核的作用，同时运行提升cpu利用率； 防止阻塞； 便于建模，让代码逻辑更清晰。
* 如何创建线程.参考文章：https://www.cnblogs.com/gw811/archive/2012/10/15/2724882.html
* 线程启动start和run区别。start开启新线程，会调用run方法； run只是一个普通的方法。参见：com.tencent.java.multithread.ThreadStartAndRunMethod
* Runnable和Callable的区别. callable可以返回结果以及向上层抛出异常、可以了解任务的执行情况并终止任务等。二者都可以用来多线程编程。 https://www.cnblogs.com/frinder6/p/5507082.html
* CyclicBarrier和CountDownLatch的区别. CyclicBarrier是可以重复使用的，一个栅栏，所有线程共同等待某个条件发生之后，继续执行；CountDownLatch是一个计数器，倒计，等待依赖的线程执行完毕之后才继续执行  https://www.cnblogs.com/dolphin0520/p/3920397.html
* volatile关键字的作用. volatile可以保证可见性和有序性，但是不能保证原子性。需要了解内存布局，cpu的运算速度远高于内存的运算速度，所以会在线程内部做一层高速缓存。:http://www.importnew.com/18126.html
* 线程安全：代码在多线程环境下执行和单线程环境执行的结果一样，那就是线程安全地。ArrayList、LinkedList、HashMap等都是线程非安全的类
* 如何获得线程堆栈。打开线程堆栈dump，可以分析很多问题，比如死循环、死锁、阻塞等等。获得线程堆栈也很简单：ps获得进程号，然后jstack 打印线程堆栈即可。在代码里Thread类提供了一个getStackTrace()方法也可以用于获取线程堆栈。
* 线程中出现了异常会怎么样？   如果线程没有catch出异常，那么这个线程就会终止。如果这个线程持有某个某个对象的监视器，那么这个对象监视器会被立即释放。
* 线程之间如何共享数据？ 线程之间共享对象就可以了，需要通过wait/notify/notifyall进行唤起和等待。阻塞队列BlockingQueue就是为线程之间共享数据而设计的。 比如多线程中的生产者和消费者线程之间，通过队列来实现数据共享。参考：http://wsmajunfeng.iteye.com/blog/1629354
* sleep和wait方法之间的区别。两者都可以用来放弃cpu一段时间。不同的地方在于，如果线程持有某个对象的监视器，sleep不会释放这个监视器，wait会放弃这个监视器。
* 生产者和消费者模型的作用：平衡生产能力和消费能力，来提升整个系统的运行效率； 解耦。
* ThreadLocal的作用。线程局部变量。多个线程共享同一个变量的时候，希望线程的局部值可以互相独立，不干扰，此时就可以用ThreadLocal，其实变量里为每个线程存储了副本，是以空间换时间的一种方式，而synchronized是同步的一种方式，会阻塞，效率不高，是以时间换空间的方式。
* wait/notify/notifyall为什么要在同步块中使用。





## java泛型
* java的泛型在编译的时候会类型擦除；可以使用反编译验证；
* java泛型继承几种用法。

## java性能分析工具使用
* jvm参数调优--制造例子
* 如何查看占用cpu最长的线程。ps 找到java对应的进程id，top -H -p pid，查看进程里的线程占用cpu的百分比，这里的线程id是原生线程，jstack里的线程id有jvm的和native的，需要转换一下，看看jvm里的哪个线程占用cpu最高。http://blog.csdn.net/hanghangaidoudou/article/details/51488249. mac下这个命令用不了。待搜索。


## 算法
* 剑指offer相关--@see com.tencent.java.algorithm.swordoffer;
* todo: 查找字符串中所有的回文串； 查找字符串集合中 包含自定 前缀的 字符串


