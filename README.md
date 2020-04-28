# JUC、JMM核心知识点笔记

尚硅谷周阳老师课程——[互联网大厂高频重点面试题第2季](https://www.bilibili.com/video/av48961087/)笔记。


[JUC笔记](https://github.com/MaJesTySA/JVM-JUC-Core/blob/master/docs/JUC.md) / [JVM笔记](https://github.com/MaJesTySA/JVM-JUC-Core/blob/master/docs/JVM.md) / [脑图下载](https://github.com/MaJesTySA/JVM-JUC-Core/raw/master/docs/jvm%20juc.xmind)

![脑图](https://raw.githubusercontent.com/MaJesTySA/JVM-JUC-Core/master/imgs/mindmap.png)

# JUC知识点

JUC
	JUC基础篇
		  1、JUC是什么
			    java.util.concurrent在并发编程中使用的工具类
			    进程/线程回顾
				      1进程/线程是什么？
				      2进程/线程例子？
				      3线程状态？
				      4 wait/sleep的区别？
				      5什么是并发？什么是并行？
		  2、Lock接口
			    复习Synchronized
				      多线程编程模板上
					        1、线程 操作 资源类
					        2、高内聚低耦合
				      实现步骤
					        1、创建资源类
					        2、资源类里创建同步方法、同步代码块
				      例子卖票程序
					        SaleTicket.java
			    Lock
				      是什么
				      Lock接口的实现 ReentrantLock可重入锁
					        如何使用
				      创建线程方式
					        继承Thread
						          不能这样写
					        new Thread()
						          不能这样写
					        实现runable接口
				      代码
				      实现runnable方法
					        新建类实现runnable接口
					        匿名内部类
					        lambda表达式
		  3、Java8之lambda表达式复习
			  lambda表达式
				    什么是Lambda
				    查看例子：LambdaDemo
				    要求
					      接口只有一个方法
				    写法
					      拷贝小括号（），写死右箭头->，落地大括号{...}
				    函数式接口
			  接口里是否能有实现方法？
				    default方法
				    静态方法实现
			  代码
		  4、线程间通信
			  面试题：两个线程打印
			  例子：NotifyWaitDemo
			  线程间通信：1、生产者+消费者2、通知等待唤醒机制
			  多线程编程模板中
				    1、判断
				    2、干活
				    3、通知
			  synchronized实现
				    代码
				    换成4个线程
				    解决办法
					      代码
					      原理图
			  多线程编程模板下
				    1、注意多线程之间的虚假唤醒
			  java8新版实现
				    对标实现
				    Condition
					      代码
		6、NotSafeDemo
			  需求
				    请举例说明集合类是不安全的
			  证明集合不安全
				    例子：NotSafeDemo
				    线程不安全错误
				    原理
			  解决方案
				    Vector
				    Collections
				    写时复制
			  写时复制
				    不加锁性能提升出错误，加锁数据一致性能下降
				    CopyOnWriteArrayList定义
				    举例：名单签到
				    CopyOnWrite理论
				    扩展类比
					      HashSet
					      HashMap
			  代码
		5、线程间定制化调用通信
			  例子：ThreadOrderAccess
			  线程-调用-资源类
			  判断-干活-通知
			  代码
		7、多线程锁
			  例子：Lock_8
			  锁的8个问题
			  8锁分析
			  代码
		8、Callable接口
			  是什么
				    面试题：获得多线程的方法几种？
				    函数式接口
			  与runnable对比
				    实现方法对比
			  怎么用
				    直接替换runnable是否可行？
				    认识不同的人找中间人
			  FutureTask
				    是什么
				    原理
				    代码
		9、JUC强大的辅助类讲解
			  CountDownLatch 减少计数
				    例子：CountDownLatchDemo
				    原理
				    代码
			  CyclicBarrier 循环栅栏
				    例子：CyclicBarrierDemo
				    原理
				    代码
			  Semaphore 信号灯
				    例子：SemaphoreDemo
				    原理
				    代码
		10、ReentrantReadWriteLock 读写锁
			  例子：ReadWriteLockDemo
			  类似案例
				    红蜘蛛
				    缓存
			  问题例子
			  代码
		11、BlockingQueueDemo 阻塞队列
			  例子：BlockingQueueDemo
			  栈与队列
				    栈：先进后出，后进先出
				    队列：先进先出
			  阻塞队列
			  阻塞队列的用处
			  架构梳理、种类分析
				    架构介绍
				    种类分析
					      ArrayBlockingQueue：由数组结构组成的有界阻塞队列。
					      LinkedBlockingQueue：由链表结构组成的有界（但大小默认值为integer.MAX_VALUE）阻塞队列。
					      PriorityBlockingQueue：支持优先级排序的无界阻塞队列。
					      DelayQueue：使用优先级队列实现的延迟无界阻塞队列。
					      SynchronousQueue：不存储元素的阻塞队列，也即单个元素的队列。
					      LinkedTransferQueue：由链表组成的无界阻塞队列。
					      LinkedBlockingDeque：由链表组成的双向阻塞队列。
			  BlockingQueue核心方法
			  代码
		12、ThreadPool线程池
			  例子：MyThreadPoolDemo
			  为什么用线程池
			  线程池如何使用
				    架构说明
				    编码实现
					      代码
					      Executors.newFixedThreadPool(int)
						        执行长期任务性能好，创建一个线程池， 一池有N个固定的线程，有固定线程数的线程
					      Executors.newSingleThreadExecutor()
						        一个任务一个任务的执行，一池一线程
					      Executors.newCachedThreadPool()
						        执行很多短期异步任务，线程池根据需要创建新线程， 但在先前构建的线程可用时将重用它们。可扩容，遇强则强
				    ThreadPoolExecutor底层原理
			  线程池几个重要参数
				    7大参数
					      4、unit：keepAliveTime的单位 
					      5、workQueue：任务队列，被提交但尚未被执行的任务
					      6、threadFactory：表示生成线程池中工作线程的线程工厂， 用于创建线程，一般默认的即可
					      7、handler：拒绝策略，表示当队列满了，并且工作线程大于 等于线程池的最大线程数（maximumPoolSize）时如何来拒绝 请求执行的runnable的策略
					      1、corePoolSize：线程池中的常驻核心线程数
					      2、maximumPoolSize：线程池中能够容纳同时 执行的最大线程数，此值必须大于等于1
					      3、keepAliveTime：多余的空闲线程的存活时间 当前池中线程数量超过corePoolSize时，当空闲时间 达到keepAliveTime时，多余线程会被销毁直到 只剩下corePoolSize个线程为止
			  线程池底层工作原理
			  线程池用哪个？生产中如设置合理参数
				线程池的拒绝策略
					  是什么
					JDK内置的拒绝策略
						  AbortPolicy(默认)：直接抛出RejectedExecutionException异常阻止系统正常运行
						  CallerRunsPolicy：“调用者运行”一种调节机制，该策略既不会抛弃任务，也不 会抛出异常，而是将某些任务回退到调用者，从而降低新任务的流量。
						  DiscardOldestPolicy：抛弃队列中等待最久的任务，然后把当前任务加人队列中 尝试再次提交当前任务。
						  DiscardPolicy：该策略默默地丢弃无法处理的任务，不予任何处理也不抛出异常。 如果允许任务丢失，这是最好的一种策略。
					  以上内置拒绝策略均实现了 RejectedExecutionHandle接口
				    在工作中单一的/固定数的/可变的三种创建线程池的方法哪个用的多？超级大坑
					      答案是一个都不用，我们工作中只能使用自定义的
					      Executors中JDK已经给你提供了，为什么不用？
				    在工作中如何使用线程池，是否自定义过线程池
					      代码
		13、Java8之流式计算复习
			  例子：StreamDemo
			  函数式接口
				    java.util.function
				    java内置核心四大函数式接口
				    实例
			  Stream流
				    What
				    Why
					      特点
						        Stream 不会改变源对象。相反， 他们会返回一个持有结果的新Stream。
						        Stream 操作是延迟执行的。这意味着 他们会等到需要结果的时候才执行。
						        Stream 自己不会存储元素
				    How
					      源头=>中间流水线=>结果
					      阶段
						        创建一个Stream：一个数据源（数组、集合）
						        中间操作：一个中间操作，处理数据源数据
						        终止操作：一个终止操作，执行中间操作链，产生结果
		14、分支合并框架
			  例子：ForkJoinDemo
			  原理
			  相关类
				    ForkJoinPool
				    ForkJoinTask
				    RecursiveTask
			  实例
		15、异步回调
			  例子：CompletableFutureDemo
			  原理
			  实例
	JUC进阶篇
		1.谈谈对volatile理解
			1. volatile是JVM提供的轻量级的同步机制
				1.1 保证可见性
				1.2 不保证原子性
				1.3 禁止指令重排
					volatile禁止指令重排优化，避免多线程环境下程序出现乱序执行的现象。
			2. JMM
				2.1 可见性
					共享变量由volatile修饰，该共享变量改变时，能够通知其他线程该值改变的消息及时通知机制。
				2.2 原子性
					不会被线程调度器中断的操作，都可认为是原子性。
						number++在多线程中就算加了volatile也不是安全的，如何不加synchronized解决？synchronized太重了，影响性能、效率。
							1、synchronized关键字
								在java代码中使用synchronized关键字的时候，jvm底层会尝试先使用偏向锁，如果偏向锁不可用，则转换为轻量级锁，如果轻量级锁不可用，则转换为重量级锁。
							2、juc.AtomicInteger类型
								底层基于CAS+volatile，CAS保证原子性，volatile保证可见行和禁止指令重排。CAS保证原子性的核心是unsafe。
						一条i++语句已经被分为两条操作指令，那么在这两条操作指令执行之间，可能由于中断而被调度到不同线程，于是，不安全性就产生了。
							想要进行原子操作，方法有很多种，其中一种比较简单的是在原子语句之间先关闭中断，然后进行原子操作后再打开中断就OK了。
								补充：这并不是说单条汇编语句就是线程安全的，这取决于CPU架构，因为单条汇编指令可能在多个CPU时钟周期内进行，现在的CPU大部分都是一条指令执行完后才能响应中断的。
									想要进行原子操作，方法有很多种，其中一种比较简单的是在原子语句之间先关闭中断，然后进行原子操作后再打开中断就OK了。
				2.3 有序性
					指令重排以及禁止重排原理
						代码变成指令的过程：源代码->编译器优化的重排->指令并行的重排->系统内存的重排->最终执行的指令
							单线程环境里面确保程序最终执行结果和代码顺序执行结果一致。
							处理器在进行重排时需要考虑指令间的数据依赖。
								不存在数据依赖的指令可以重排
							多线程环境中线程交替执行，由于编译器优化重排的存在，两个线程使用的变量无法保证一致性，执行结果无法预测。
							为何要指令重排？       
								1、现在的CPU一般采用流水线来执行指令。一个指令的执行被分成：取指、译码、访存、执行、写回、等若干个阶段。然后，多条指令可以同时存在于流水线中，同时被执行。
									语句的执行顺序取决于语句的所有指令执行结束的顺序。
								2、指令流水线并不是串行的，并不会因为一个耗时很长的指令在“执行”阶段呆很长时间，而导致后续的指令都卡在“执行”之前的阶段上。
									2.1、流水线是并行的，多个指令可以同时处于同一个阶段，只要CPU内部相应的处理部件未被占满即可。比如说CPU有一个加法器和一个除法器，那么一条加法指令和一条除法指令就可能同时处于“执行”阶段, 而两条加法指令在“执行”阶段就只能串行工作。
									2.2、乱序可能就产生了。比如一条加法指令原本出现在一条除法指令的后面，但是由于除法的执行时间很长，在它执行完之前，加法可能先执行完了。再比如两条访存指令，可能由于第二条指令命中了cache而导致它先于第一条指令完成。
									2.3、指令重排案例
										重排案例1
										重排案例2
										重排案例3
								3、相比于串行+阻塞的方式，流水线像这样并行的工作，效率是非常高的。
								4、“顺序流入，乱序流出”：一般情况下，指令乱序并不是CPU在执行指令之前刻意去调整顺序。CPU总是顺序的去内存里面取指令，然后将其顺序的放入指令流水线。但是指令执行时的各种条件，指令与指令之间的相互影响，可能导致顺序放入流水线的指令，最终乱序执行完成。
						禁止指令重排底层原理
							volatile实现
								1、对volatile变量进行写操作时，会在写操作后加入一条store屏障指令，将工作内存内的变量刷新会到主内存。
								2、对volatile变量进行读操作时，会在读操作前加入load屏障指令，从主内存获取共享变量。
							内存屏障（Memory Barrier/内存栅栏）是一个CPU指令。
								1、保证特定操作的执行顺序
									什么指令都无法与MB进行重排序，因此可以禁止其指令前后指令重排，保证执行顺序。
								2、保证某些变量的内存可见性
									MB强制刷出各种CPU的缓存数据，因此在此CPU上的线程都可以更新数据的最新版本。
			3. 在哪些地方用过volatile？
				3.1 单例模式DCL代码
					//DCL模式 Double Check Lock 双端检索机制：在加锁前后都进行判断
						源码
					指令重排引起DCL线程安全的问题
					指令重排引起DCL线程安全的问题
						new SingletonDemo()的处理流程
							1、分配内存空间。
							 2、初始化对象。
							3、设置instance指向分配的内存，此时instance != null。
						new的指令2 3不存在指令依赖，可能存在指令重排，执行顺序为123或者132.导致3先执行。读取为!=null时，可能时初始化未完成，最后return时因为2初始化未完成，导致取到null。
							解决方案：volatile修饰对象，private static volatile SingletonDemo singletonDemo=null;
				3.2 单例模式volatile分析
					Spring中使用DCL+volatile实现单例模式生成Bean的实际案例。
						Spring的依赖注入（包括lazy-init方式）都是发生在 AbstractBeanFactory 的 getBean 里。
						AbstractBeanFactory类public abstract class AbstractBeanDefinition 
							源码
						方法Object getSingleton
							源码
			4.简单理解volatile是基于lock和总线mesi缓存一致性协议的改进
				4.1 lock：对共享变量加锁，但是会使得并发线程变为串行线程。
				4.2 总线mesi缓存一致性协议：cpu总线嗅探机制（监听），使得线程工作内存的变量失效。
于是，线程重新从主内存取值即可。但是存在问题，store时经过总线，而还未write时，另一线程已经进行了read，则仍旧无法读取正确的改变。
				4.3 Volatile缓存可见性：在mesi的基础上，在内存区域的缓存加锁（在store之前），write完在释放锁。
		2.CAS：Compare and Swap，即比较再交换。
			1. 比较并交换
				内存值V，预期值A，修改值B，if V==A do V=B
			2. CAS底层原理？谈谈对UnSafe的理解？为什么不用synchronized也能实现++操作？
				AtomicInteger.getAndIncrement源码实现基于Unsafe，而Unsafe的核心操作基于CAS。
					public class AtomicInteger extends Number implements java.io.Serializable {              
      private volatile int value;
					   private static final Unsafe unsafe = Unsafe.getUnsafe();
						sun.misc.Unsafe类源码
							1、Unsafe是CAS的核心类。CAS是xxx为什么能保证原子性
							2、所有方法都由native修饰，基于该类可以操作特定内存的数据。内部方法操作类似C语言中的指针。
							3、Unsafe.getAndAddInt
								源码
									var1为Atomic对象本身，var2为对象值的引用地址，va4为增量值，var5为线程内存值。
									先通过var1 2获取var5预期值A，判断var5线程工作内存值和var1 2获得的主内存值是否相等，然后修改为var5修改值B。
									总结：源码有注释，相等修改，不相等自旋锁。
					   private static final long valueOffset;
						变量valueOffset表示变量值在内存中的便宜地址，因为Unsafe就是根据内存偏移地址获取数据的。
					Atomic类源码总结：volatile+Unsafe，Unsafe核心操作getAndAddInt基于CAS。CAS保证原子性，volatile保证可见行和禁止指令重排。
				简单总结
					CAS是一条CPU并发原语
					功能是判断内存某个未知的值是否为预期值，如果是，则更改为新的值。JVM实现出CAS的硬件指令，完全依赖于硬件的功能。CAS就是比较当前工作内存中和主内存的值，如果相等，则执行规定操作，否则继续比较，直到相等为止。CAS有三个操作数，内存值V，预期值A，要修改的值B。当且仅当V=A时，才将V对应的值修改为B。
					原语概念
			3. CAS的缺点？
				循环时间长，开销大
				只能保证一个共享变量的原子操作
				会引出ABA问题
					狸猫换太子：你儿子看上去还是你儿子，但他已经不是你儿子了。
		3.ABA问题？原子更新引用知道吗？
			ABA问题的产生
				算法实现的前提需要取出内存中某时刻的数据并在当前时刻比较并替换，这个时间差内会导致数据变化。若线程1短期内将值从A改为B后又改为A，而线程2无法知晓ABA过程，认为数据未修改，然后将其修改为C。
			原子引用AtomicReference
				定义范型原子引用，AtomicInteger=AtomicReferece<Integer>
				存在ABA问题
					蛋糕店，低于20则奖励一次补贴，补贴到账时刚好消费，又低于20，重复奖励补贴。
			时间戳原子引用AtomicStampedReference
				stamp标记作为版本号，类似乐观锁，记录变化过程，解决ABA问题。
				源码
		4.集合类+线程不安全，案例，分析
			集合类
				1、List集合类
					ArrayList、Vector和LinkedList都是实现了List接口（允许数据重复）的容器类，它们都能对元素做增删改查的操作。
						ArrayList
						Vector
						LinkedList
					区别
						1、从初始化、扩容、线程安全三方面对比ArrayList与Vector
							ArrayList采用懒加载策略（第一次add时才初始化内部数组，默认初始化大小为10）   扩容为原先数组大小的1.5倍。采用异步处理，线程不安全，性能较高       ArrayList在大部分场合（80%，频繁查找、在集合末端插入与删除）都采用ArrayList
							Vector在实例化对象时就初始化内部数组（大小为10），capacityIncrement默认为0，扩容为原先数组大小的2倍。采用synchronized修饰增删改查方法，线程安全，性能较低（锁的粒度太粗，将当前集合对象锁住，读读都互斥），即使要用性能安全的集合，也不推荐使用Vector
						2.LinkedList采用异步处理，线程不安全  频繁在任意位置的插入与删除考虑使用LinkedList  LinkedList是Queue接口的常用子类
					注意⚠️
						remove方法重载了，注意是传的包装类还是int索引
				2、Set集合类
					0、Set是一种无序、可重复的数据容器，与List一样继承与Collection接口。重点介绍HashSet和TreeSet，这两种Set是日常工作中用的比较多的，在面试的过程中也会涉及。
						HashSet
							LinkedHashSet
						TreeSet
					1、无序性
						不等于随机性，存储的数据在底层数组中并非按照数组索引添加，而是hash值
					2、不可重复性
						保证添加的元素按照equals判断时，不能返回true；即相同的元素只能添加一个。(先判断hash值是否一样，再判断equals)。
				3、Map集合类
					HashMap
					HashTable（与HashMap关联）
					LinkedHashMap
					TreeMap
			线程不安全，案例，分析
				1、List集合类线程不安全，案例、分析
					1、单线程不安全
						1、集合使用Iterator迭代器时出现的异常(集合的fail-fast机制)
							ArrayList和Iterator的实现关系
								源码
								上面是ArrayList的部分实现，可以看出我们调用iterator()，方法其实拿到的是一个Itr的实例，这个内部类，通过向上转型为接口后，隐藏了内部的实现。JDK中Iterator的实现绝大多数采用了这种方式。
							对Vector、ArrayList在迭代的时候如果使用原对象进行修改， 就会抛出java.util.ConcurrentModificationException异常。
								关键点就在于：调用list.remove()方法导致modCount和expectedModCount的值不一致，导致了fail-fast机制生效。
									源码分析:Itr类的具体实现，它是AbstractList的一个成员内部类。
										首先我们看一下它的几个成员变量：
										cursor：表示下一个要访问的元素的索引，从next()方法的具体实现就可看出
										lastRet：表示上一个访问的元素的索引
										expectedModCount：表示对ArrayList修改次数的期望值，它的初始值为modCount。
										modCount是AbstractList类中的一个成员变量
											该值表示对List的修改次数，查看ArrayList的add()和remove()方法就可以发现，每次调用add()方法或者remove()方法就会对modCount进行加1操作。
												源码
									源码
							解决方案：使用迭代器的remove方法，Iter.remove
								关键点在于checkForComodification后令expectedModCount = modCount
									源码
						案例
					2、多线程不安全
						1、集合使用Iterator迭代器时出现的异常(集合的fail-fast机制)
							案例
							原因
								有可能有朋友说ArrayList是非线程安全的容器，换成Vector就没问题了，实际上换成Vector还是会出现这种错误。
							解决方案
								一般有2种解决办法：
　　1）在使用iterator迭代的时候使用synchronized或者Lock进行同步；
　　2）使用并发容器CopyOnWriteArrayList代替ArrayList和Vector。
						2、并发修改异常
							案例
								结果无法预测<=10000
							原因
								写覆盖+写冲突，线程不安全的原因是因为ArrayList的方法不是线程安全的，（和volatile对比没有使用没有synchronize修饰），为了保证效率，但是不安全。
							解决方案
								1、ArrayList变为VectorList
									关键实现：Synchronized修饰方法
								2、Collections集合工具类转换ArrayList为线程安全的集合类
									关键实现：转换为Collections定义的Synchronized类，方法内使用mutex to synchronize。
								3、CopyOnWriteArrayList类，写时复制，读写分离
									源码
										关键实现：Volatile+ReentrantLock+copy
					总结：其他的集合案例分析或解决方案都雷同
				2、Set集合类线程不安全，案例、分析
					1、单线程不安全
					2、多线程不安全
						案例
					总结：其他的集合案例分析或解决方案都雷同
				3、Map集合类线程不安全，案例、分析
					1、单线程不安全
					2、多线程不安全
						解决方案
							1、HashMap变为HashTable
								关键实现：Synchronized修饰方法
							2、ConcurrentHashMap
								关键实现：put方法里面加casTabAt(CAS+synchronize
							3、CopyOnWriteArrayList类，写时复制，读写分离
								源码
									关键实现：Volatile+ReentrantLock+copy
					总结：其他的集合案例分析或解决方案都雷同
			总结：大同小异
		5.公平锁/非公平锁/可重入锁/递归锁/自旋锁的理解？手写一个自旋锁？
			公平/非公平
				是什么？
					ReentrantLock的实现，继承AbstractQueuedSynchronizer队列类，有front、end、state
						源码
				区别？
					不同
						获取锁的顺序是否按照申请锁的顺序？（若允许插队、加塞，则是非公平锁，提升效率而存在）
							具体细节：并发环境下，线程获取锁会先去看锁维护的等待队列是否为空或者线程本身处于第一位，则获取锁。而非公平锁会直接获取，获取不到则变为公平锁。
								非公平锁吞吐量大
								队头出队可以再获取锁
					相同
						都有同步队列
				问题
					在高并发的环境下，非公平锁可能造成：1、优先级反转；2、饥饿现象。公平锁吞吐量小，效率低。
				题外话
					synchronized也是非公平锁
			可重入锁(递归锁)
				是什么？
					线程可以进入任何一个它已经拥有锁所同步着的代码块。
						同一线程外层函数获得锁之后，内层递归函数仍然能获取该锁的代码，在同一个线程外层方法获取锁的时候，在进入内层方法会自动获取锁。
					总结
						同步函数方法嵌套，申请锁时递归逐层访问同步函数，然后在将所需的锁依次返回。
							同一个锁对象，重复加锁，修改状态值，当状态值为0，其他线程才能访问该锁。
					ReentrantLock和Synchronized都是默认的非公平可重入锁
				优点
					可重入锁的好处就是可以解决死锁问题
						参考上述介绍可以知道，我只要拿到最外层的锁就可以了，不会因为拿到了最外层的锁，但是内部函数的锁拿不到而死锁。
				案例
					ReentrantLockDemo，使用可重入锁可连续访问get、set，非可重入则需要等待锁释放才能访问set。
				原理
					同锁，重复加锁，状态值state累加，lock&unlock需要配对。
						到了这里，相信大家应该明白了什么是可重入锁了吧。就是一个线程在获取了锁之后，再次去获取了同一个锁，这时候仅仅是把状态值进行累加。如果线程A释放了一次锁，就成这样了：
线程A释放一次锁，仅仅是把状态值减了，只有线程A把此锁全部释放了，状态值减到0了，其他线程才有机会获取锁。当A把锁完全释放后，state恢复为0，然后会通知队列唤醒B线程节点，使B可以再次竞争锁。当然，如果B线程后面还有C线程，C线程继续休眠，除非B执行完了，通知了C线程。注意，当一个线程节点被唤醒然后取得了锁，对应节点会从队列中删除。 
					谈谈你对可重入锁ReentrantLock的的实现思路
			自旋锁
				是什么？
					尝试索取锁当线程不会立即阻塞，而是采用循环当方式获取。
				优缺点
					减少线程切换的上下文消耗，但是循环增加CPU的使用率。
				手写一个自旋锁
					SpinLockDemo
			独占锁（写锁）/共享锁（读锁）/互斥锁
				实现缓存读写（读写分离，写原子独占，读读共存）
					ReadWriteLockDemo
						实现1
							ReentrantLock+volatile
								实现写原子独占，但无法实现读读共存，并发效率低
						实现2
							ReentrantReadWriteLock+volatile
								分有两把锁，readLock和writeLock
								实现了写原子独占，读读共存，并发效率提升
						实现3
							synchronized暂时不能实现缓存读写同时进行，因为sync锁分离，自动释放。
			悲观锁&乐观锁
				独占锁是一种悲观锁，synchronized就是一种独占锁，会导致其它所有需要锁的线程挂起，等待持有锁的线程释放锁。而另一个更加有效的锁就是乐观锁。所谓乐观锁就是，每次不加锁而是假设没有冲突而去完成某项操作，如果因为冲突失败就重试，直到成功为止。乐观锁用到的机制就是CAS，Compare and Swap。
			synchronize和lock的区别？
				 // synchronize和lock有什么区别？用新的lock有什么好处？举例
					  案例SyncAndReentrantLockDemo
				        // 1、synchronize属于JVM层面，属于java关键字，底层操作主要是monitorenter/ monitorexit，通过monitor对象完成（wait notify也是依赖monitor对象）
					        //      lock是JUC的类，属于api层面，通过javap看两者的JVM汇编，可以看出前者使用monitor操作，后者使用的是new语句
				        // 2、synchronize不需要用户手动释放锁，lock需要用户主动释放否则造成死锁。
				        // 3、等待是否可中断？
					        //      synchronize不可中断，除非抛出异常或者正常运行。
					        //      lock可中断，1、设置超时方法trylock(timeout, timeunit) 2、LockInterruptibly()放入代码块，调用interrupt方法可中断
				        // 4、加锁是否公平？
					        //      synchronize不公平。
					        //      lock都可以。
				        // 5、锁绑定多个条件Condition？
					        //      lock通过Condition可以实现分组唤醒需要唤醒的线程们，精确唤醒。
					        //      synchronize无Condition，而synchronize只能随机或者全部唤醒。
		6.CountDownLatch/CyclicBarrier/Semaphore使用过吗？
			CountDownLatch
				让一些线程阻塞直到另一些线程完成操作后才被唤醒（班长关门）
				主要有两个方法，await方法会被阻塞。countDown会让计数器-1，不会阻塞。将计数器变为0时，调用await方法的线程会被唤醒，继续执行。countDownLatch.await()->使当前线程等待直至countdown到0。
				CountDownLatchDemo
			CyclicBarrier
				CyclicBarrie字面上就是可循环使用的屏障。当一组线程得到一个屏障（同步点）时被阻塞，直到最后一个线程到达屏障时，屏障才会打开，所有被屏障拦截的线程才会继续工作。进入屏障通过await方法。
				CyclicBarrierDemo
					七龙珠
			Semaphore
				信号量主要用于两个目的，一个是多个共享资源的互斥使用，一个是并发线程数的控制。
				SemaphoreDemo
					争车位
		7.阻塞队列知道吗？
			队列/阻塞队列
				当阻塞队列时空，从队列中获取元素的操作被阻塞；
当阻塞队列是满，向队列中插入元素的操作被阻塞；
生产者、消费者。
			为什么用？好处？
				不需要关心什么时候阻塞/唤醒线程，因为BQ已经实现了。
			BlockingQueue核心方法
				add,    offer,put,offer/  插入
remov,poll,take,poll/   移除
element,peek/              检查队首元素
add/remove/offer/poll/put/take
					第一列抛出异常组-插入、移除失败抛出异常，
第二列特殊值组-插入失败返回true，移除时队列为空返回null
第三列阻塞组-插入移除失败时不断阻塞，
第四列超时组-插入移除失败时阻塞一定时长，超时取消阻塞。
			架构梳理+种类分析
				Collection-->Queue-->BlockingQueue-->7个实现类
					参照List容器Collection-->List->AbstractList->ArrayList
				种类分析
					ArrayBlockingQueue：由数组结构组成的有界阻塞队列
					LinkedBlockingQueue：由链表结构组成的有界阻塞队列。界为Integer.MAX_VALUE。。。
					PriorityBlockingQueue：支持优先级排序的无界阻塞队列
					DelayQueue：使用优先级队列实现的延迟无界阻塞队列
					SynchronousQueue：只存储一个元素的阻塞队列，即单个元素的队列
						理论
							同步、信号量、wait/notify
						SynchronousQueueDemo
					LinkedTransferQueue：由链表组成的无界阻塞队列
					LinkedBlockingDeque：有链表组成的双向阻塞队列
			用在哪里？
				生产者消费者模式
					传统版ProdConsTradiDemo
						1.0版本synchronize， sync wait notify
						2.0版本lock， lock await singal （Lock Condition）
					阻塞队列版 ProdConsBlockQueueDemo
						3.0版本blockqueue(高并发)
							volatile+atomicInteger(+CAS+原子引用)+BlockQueue+线程交互
					口诀
				线程池
					ThreadLocal
				消息中间件
					activeMQ
		8.线程池用过吗？ThreadPoolExector谈谈你的理解？
			线程的实现4种方法
				extend Thread
				implement Runable
				implement Callable
					1、有返回值
					2、可以抛出异常
						高并发线程，如果100个线程有个别现象出问题，需要返回错误/异常。
					适配器设计模式
				ThreadPool
			为什么用线程池？优势？
				
					工作原理
						        * 线程池做的工作主要是控制运行的线程的数量，处理过程中将任务放入队列BQ，
						        * 然后在线程创造后启动这些任务，如果线程数量超过最大数量，则超出的任务排队等候，
						        * 等待其他线程执行完毕，再从队列中取出任务来执行。
					 特点：线程复用、控制最大并发数、管理线程。
					优点：
						        * 1、降低资源消耗，重复利用已创建的线程降低线程创建/销毁的消耗
						        * 2、提高响应速度，任务到达时不需要等待线程的创建就能立即执行
						        * 3、提高线程管理性，线程是稀缺资源，无限制创建会消耗资源，降低系统稳定性，使用TP可以统一管理分配，调优和监控
					缺点：频繁的链接和关闭消耗资源
			5种+线程池如何使用？
				架构说明
					Executor接口-->ExecutorService等
				编码实现
					了解
						Executors.newScheduledThreadPool()
						Executors.newWorkStealingPool(int)
					重点
						Excutors.newFixedThreadPool(int) 一池固定线程。执行长期任务，性能会很好
						Excutors.newSingleThreadPool(int) 一池单线程。一个任务一个线程
						Excutors.newCachedThreadPool(int) 一池多线程。适用于短期异步小任务或者负载较轻的任务。
				关键类ThreadPoolExecutor、工具类Executors
				工作中用什么线程池？
					工作中一个也不用！！工作时不允许使用Executors工具类创建已有的线程，需要使用ThreadPoolExecutor手动创建(7个参数)
			线程池的几个重要参数？
				七大参数
					1、corePoolSize：线程池中常驻的核心线程数
					3、maxiumPoolSize：能够容纳的最大线程数，必须大于等于1。Queue队列满时，才根据MaxiumPoolSize扩容，否则只需要corePoolSize个线程数。触发扩容的线程会直接抢占线程，不会进入Queue！！。
					5、keepAliveTime：多余空闲线程的存货时间。当前线程数量超过corePoolSize时，当空闲时间达到keepAliveTime时，多余线程会被销毁直到只剩下corePoolSize个线程位置。
					unit：keepAliveTime的单位，Seconds、MillSeconds之类。
					2、workQueue：任务队列，被提交但未被执行的任务
					threadFactory：生成线程池中工作线程的线程工厂，用于创建线程，默认即可。
					4、RejectedExecutionHandler：拒绝策略，表示当Queue队列满了并且工作线程大于等于最大线程数Maxium，如何拒绝请求。
				面试问题，提供相关参数分析线程池的行为。
			线程池底层工作原理？
关键类ThreadPoolExecutor、工具类Executors
				流程：结合7大参数1～5进行理解。
				ThreadPoolExecutor的理解
		9.生产上如何设置线程池参数？拒绝策略怎么配？
			谈谈拒绝策略
				是什么？
					当达到最大线程数且等待队列满时，就要启用拒绝策略。
				JDK的四种拒绝策略
					AbortPolicy（默认）：直接抛出RejectedException。
					CallerRunsPolicy：“调用者运行”一种调节机制，不会抛弃任务也不会抛出异常，而是回退给调用者
					DiscardOldestPolicy：丢弃等待最久的任务，然后把当前任务加入队列中尝试再次提交当前任务。
					DiscardPolicy：直接丢弃任务，不做任何处理，如果任务允许丢失，这是最好的方案。
				以上四种内置策略均实现了RejectedExecutionHandler接口
			！！！单一、固定、可变哪个用得最多？大坑
				答案是一个都不用，生产中用自己定义的
				JDK已经提供给你了，为什么不用？
					坑就在LinkedBlockingQueue的最大长度是21亿，会导致OOM。
			工作中如何使用线程池的？自己如何定义一个线程池？
				自己用ThreadPoolExecutor创建
			合理配置线程池你是如何考虑的？
				由低向上依次查看，硬件->软件->业务
				CPU密集型
					配置尽可能少的线程数量，一般CPU核数+1
				IO密集型
					1、配置尽可能的线程数量，一般CPU核数*2
					2、考虑阻塞，一般CPU核数/(1-阻塞系数) (阻塞系数0.8～0.9之间。例，8核CPU，则配80个)
		10.死锁编码及定位分析
			是什么？
				产生死锁的原因
					系统资源不足
					进程运行推进顺序不对
					资源分配不当
				 * 死锁产生原因：
					 *      非剥夺资源的竞争和进程的不恰当推进顺序。
				 * 死锁4条件：
					 *      1、互斥-资源互斥/ 临界资源，预防时无法破坏该条件
					 *      2、不剥夺-获得的资源在未使用完之前不可剥夺-
					 *      3、请求和保持-保持至少一个资源，又申请其他资源
					 *      4、循环等待-死锁进程循环等待各个进程的资源
				 * 解决死锁：
					 *      1、预防（破坏4条件），
						 *              互斥：无法破坏互斥
						 *              不剥夺：可剥夺会增加系统开销降低吞吐/
						 *              请求和保持：不请求会严重浪费资源可能造成饥饿/
						 *              循环等待：不循环等待会浪费资源+编程不便
					 *      2、避免，安全状态（安全序列）+ 银行家算法（资源预分配，检查是否为安全序列）
						 * 银行家算法：Available、Max、Allcoate、Need、Request
					 *      3、检测，资源分配图，进行化简，若无法化简则为死锁
					 *      4、解除，资源剥夺法-挂起死锁进程/ 撤销进程法-干掉死锁进程/ 进程回退法-回退至可避免状态
			死锁案例
			解决
				jps -l
jstack xxx
					jps命令定位进程号
					jstack找到死锁查看

JVM
	前提复习
		JVM体系结构
			运行时数据区RDA
				堆
				java栈
				本地方法栈
				方法区
				程序计数器
			类加载器子系统CL
				是什么
					根启动加载器
					扩展加载器
					应用加载器
				双亲委派机制
				沙箱安全机制
			字节码执行引擎EE
			本地方法接口NI
			本地方法库ddl
		Java8的JVM
		GC作用域
		垃圾回收算法
			引用计数（应该是分代收集）
			复制
			标记清除
			标记整理
	1.JVM垃圾回收的时候如何确定垃圾？什么是GC Roots？
		什么是垃圾？
			内存中不再被使用的空间就是垃圾
		如何判断一个对象可以被回收？
			引用计数法
			枚举根节点做可达性分析（根搜索路径算法）
				什么是可达性分析？
				哪些对象可以作为GC Roots？
					虚拟机栈（栈帧中的局部变量区）中的对象
					本地方法栈中JNI（Native）方法引用的对象
					方法区中常量引用的对象
					方法区中的类静态属性引用的对象
	2.JVM调优和参数配置？如何查看JVM系统默认值
		JVM的参数类型（三种）
			标配参数
				-version
				-help
				-showversion
			X参数（了解）
				-Xint
					解释执行
				-Xcomp
					第一次使用就编译成本地代码
				-Xmixed
					混合模式（默认）
			XX参数
				布尔类型
					公式
						-XX:+或者-某个属性值
						+表示开启，-表示关闭
					Case
						是否打印GC收集信息
							-XX:-PrintGCDetails
							-XX:+PrintGCDetails
				KV设值类型
					公式
						-XX:属性key=值value
					Case
						-XX:MetaspaceSize=128m
						-XX:MaxTenuringThreshold=15
				jinfo举例，如何查看当前程序运行的配置
					jinfo -flag 某个参数 pid 或者 jinfo -flags pid 显示所有参数
				题外话（坑题）
					两个经典参数：-Xms -Xmx
					既不是布尔型，又不是KV型。而且只有一个X。怎么解释？
						-Xms 等价于Xx:InitialHeapSize。
						-Xmx 等价于Xx:MaxHeapSize
		查看JVM默认值
			-XX:+PrintFlagsInitial
				主要查看JVM根据系统设定的初始默认值
				公式
					java -XX:+PrintFlagsInitial
			-XX+PrintFlagsFinal
				主要查看更改后的JVM参数
				公式
					java -XX:+PrintFlagsFinal
			PrintFlagsFinal举例，运行java命令的同时打印出参数
				java -XX:+PrintFlagsFinal -Xss128k javaClass
			-XX:+PrintCommandLineFlags
				查看一些常见的参数，比如所使用的垃圾回收器
	3.用过的JVM常用基本配置参数有哪些？
		常用参数
			-Xms
				初始大小内存，默认为物理内存的1/64
				等价于-XX:InitialHeapSzie
			-Xmx
				最大分配内存，默认为物理内存的1/4
				等价于-XX:MaxHeapSzie
			-Xss
				单个线程栈的大小，默认为512~1024K
				等价于-XX:ThresholdStackSize
			-Xmn
				新生代大小，一般不调
			-XX:MetaspaceSize
				设置元空间大小。元空间与永久代的最大区别在于，永久代在虚拟机中，而元空间在本地内存中。
				-Xms10m -Xmx10m -Xx:MetaspaceSize1024m 
			-XX:+PrintGCDetails
				输出GC收集日志信息
				GC日志信息
				FullGC日志信息
			-XX:SurvivorRatio
				新生代Eden与两个Survivor的比例
			-XX:NewRatio
				新生代和老年代的比例
			-XX:MaxTenuringThreshold
				设置进入老年代的时间，默认是15次。如果改成0，那么就不在新生代的S区分配，直接进入老年代。
	4.强引用、软引用、弱引用、虚引用分别是什么
		整体架构
			Object-->Reference-->Soft/Weak/PhantomRef
		强引用
			在GC的时候，就算出现OOM，也不会回收对象，死了也不回收。
		软引用
		弱引用
			软应用和弱引用的适用场景
			WeakHashMap
		虚引用
	5.谈谈你对OOM的理解？
		java.lang.StackOverflowError
		java.lang.OOME:Java heap space
		OOME:gc overhead limit exceeded
		OOME:Direct buffer memory
		OOME:unable to create new native thread
		OOME:Metaspace
	6.GC垃圾回收算法和垃圾收集器的关系
		GC算法—标记整理、清除、复制、分代【老师是引用计数】
		没有完美的收集器，GC收集器是GC收集算法的实现。
		4种主要垃圾收集器
			串行垃圾回收器（Serial）
				为单线程环境设计且只使用一个线程进行GC，会暂停所有用户线程，不适用于服务器。
			并行垃圾回收器（Parrallel）
				多个GC线程并行工作，此时用户线程是暂停的，适用于科学计算、大数据后台，交互性不敏感的场合。
			并发垃圾回收器（CMS）
				用户线程和GC线程同时执行（不一定是并行，交替执行），不需要停顿用户线程，互联网公司多用，适用对响应时间有要求的场合。
			G1垃圾回收器
				将堆内存分割成不同的区域，然后并发地进行垃圾回收。
	7.怎么查看服务器的默认GC收集器？生产上如何配置GC收集器？谈谈你的GC收集器的理解？
		怎么查看默认的GC收集器？
			java -XX:+PrintCommandLineFlags -version
		默认的GC收集器有哪些？
			Serial、Parrallel、ConcMarkSweep（CMS）、ParNew、ParallelOld、G1。还有一个SerialOld，已被淘汰。
		垃圾收集器
			部分参数说明
				DefNew:Default New Gen
				Tenured:Old
				ParNew:Parallel New Gen
				PSYoungGen:Parallel Scavenge
				ParOldGen:Parallel Old Gen
			Server/Client模式分别是什么意思
			新生代
				串行GC Serial/Serial Copying
				并行GC ParNew
				并行回收GC Parallel/Parallel Scavenge
			老年代
				串行GC SerialOld/Serial MSC
				并行GC Parallel Old/Parallel MSC
				并发标记清除GC CMS
					4个步骤
						初始标记
						并发标记
						重新标记
						并发清除
					优缺点
						优点：停顿时间段
						缺点：CPU压力大。产生大量碎片。
		如何选择垃圾收集器 ？
	8.G1垃圾收集器
		以前收集器特点
			年轻代和老年代是各自独立且连续的内存块
			年轻代收集使用单Eden+S0+S1进行复制算法
			老年代收集必须扫描整个老年代区域
			都是尽可能少而快地执行GC为设计原则
		G1是什么
			既提高吞吐量，又减少GC时间。内存区域不再是新、老代，而是一个一个Region。没有内存碎片。可以预测停顿。
		底层原理
			Region区域化垃圾收集器
			回收步骤
			4步过程
		常用配置（了解）
			-XX:UseG1GC
			-XX:G1HeapRegionSize=n
			-XX:MaxGCPauseMillis：最大GC停顿时间，是个软目标，尽量达到。
			-XX:InitiatingHeapOccupancyPercent:堆占用了多少执行GC，默认45%
			-XX:ConcGCThreads：并发GC使用的线程数
			-XX:G1ReservePercent：设置为空闲空间的预留内存比
		和CMS对比的优势
			指定停顿时间和无内存碎片
	9.生产环境服务器变慢，诊断思路和性能评估谈谈？
		java -server -Xms xxx -jar 
		整机：top，uptime精简版
		CPU：vmstat
		内存：free
		硬盘：df
		磁盘IO：iostat
		网络IO：ifstat
	10.假如生产环境CPU占用过高，谈谈分析思路和定位
		1.先用top命令找出CPU占比最高的进程
		2.ps -ef或者jps查看进程编号
		3.定位到具体的线程或者代码
			从进程定位到线程
		4.将线程的id转换为16进制
		5.jstack 线程id | grep tid （16进制） 得到java程序详细信息，定位到第几行。
	11.对于JDK自带的JVM监控和性能分析工具用过哪些？一般怎么用的？
		jps 进程状态工具
		jinfo java配置信息工具
		jmap 内存映像工具
		jstat 统计信息监视工具

