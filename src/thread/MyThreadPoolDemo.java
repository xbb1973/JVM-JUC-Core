package thread;

import java.util.concurrent.*;

/**
 * 第四种使用Java多线程的方式，线程池
 * <p>
 * 线程池做的工作主要是控制运行的线程的数量，处理过程中将任务放入队列BQ，
 * 然后在线程创造后启动这些任务，如果线程数量超过最大数量，则超出的任务排队等候，
 * 等待其他线程执行完毕，再从队列中取出任务来执行。
 * 特点：线程复用、控制最大并发数、管理线程。
 * 优点：
 * 1、降低资源消耗，重复利用已创建的线程降低线程创建/销毁的消耗
 * 2、提高响应速度，任务到达时不需要等待线程的创建就能立即执行
 * 3、提高线程管理性，线程是稀缺资源，无限制创建会消耗资源，降低系统稳定性，使用TP可以统一管理分配，调优和监控
 * 缺点：频繁的链接和关闭消耗资源
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {

        // System.out.println("Fixed Thread Pool");
        // fixedThreadPool();
        // System.out.println("Single Thread Pool");
        // singleThreadPool();
        // System.out.println("Cached Thread Pool");
        // cachedThreadPool();
        // System.out.println("Custom Thread Pool");
        // customThreadPool();

        System.out.println("My Basic Thread Pool");
        ThreadPoolBasicLearning();
    }

    private static void ThreadPoolBasicLearning() {

        // 查看依赖
        // ScheduledThreadPoolExecutor

        // 使用的BQ为LinkedBlockingQueue/ SynchronousQueue
        // ExecutorService threadPool = Executors.newFixedThreadPool(3);
        // ExecutorService threadPool = Executors.newSingleThreadExecutor();
        // ExecutorService threadPool = Executors.newCachedThreadPool();

        ExecutorService threadPool =
                new ThreadPoolExecutor(
                        2,
                        5,
                        1,
                        TimeUnit.SECONDS,
                        new LinkedBlockingQueue<>(3),
                        Executors.defaultThreadFactory(),
                        new ThreadPoolExecutor.AbortPolicy()
                        // new ThreadPoolExecutor.CallerRunsPolicy()
                        // new ThreadPoolExecutor.DiscardOldestPolicy()
                        // new ThreadPoolExecutor.DiscardPolicy()
                );
        try {
            for (int i = 1; i <= 12; i++) {
                final int tempI = i;
                threadPool.execute(() -> {
                    // try {
                    //     TimeUnit.SECONDS.sleep(1l);
                    // } catch (InterruptedException e) {
                    //     e.printStackTrace();
                    // }

                    // 触发扩容的线程任务立即运行
                    // pool-1-thread-1	顾客0办理业务
                    // pool-1-thread-2	顾客1办理业务
                    // pool-1-thread-3	顾客5办理业务
                    System.out.println(Thread.currentThread().getName() + "\t" + "顾客" + tempI + "办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    private static void customThreadPool() {
        ExecutorService threadPool =
                new ThreadPoolExecutor(2,
                        5,
                        1L,
                        TimeUnit.SECONDS,
                        new LinkedBlockingQueue<>(3),
                        Executors.defaultThreadFactory(),
                        new ThreadPoolExecutor.AbortPolicy());
        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t办理业务");
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    private static void cachedThreadPool() {
        //不定量线程
        ExecutorService threadPool = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < 9; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t办理业务");
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    private static void singleThreadPool() {
        //一池1个线程
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        try {
            for (int i = 0; i < 9; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t办理业务");
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    private static void fixedThreadPool() {
        //一池5个线程
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        //一般常用try-catch-finally
        //模拟10个用户来办理业务，每个用户就是一个线程
        try {
            for (int i = 0; i < 9; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t办理业务");
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}