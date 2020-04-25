package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

// 线程的实现4种方法
//         extend Thread
//         implement Runable
//         Callable
//         ThreadPool
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 当前线程：main、FutureTask、GC、

        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread()); // 适配器模式
        new Thread(futureTask, "AA").start();
        new Thread(futureTask, "BB").start(); // 多个futureTask实际上只进入。
        int result01 = 100;
        System.out.println("result1=" + (result01));

        int result02 = 0;
        // 获取结果方法1、要求获得callable线程的计算结果，未完成则强求导致主线程阻塞，不推荐使用。
        result02 = futureTask.get();
        // 获取结果方法2、
        // while (!futureTask.isDone()) {
        //     result02 = futureTask.get();
        // }

        System.out.println("result1=" + (result01));
        System.out.println("result1 + result2=" + (result01 + result02));
    }
}

class MyThread implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "\t" + "callable come in ...");
        System.out.println(Thread.currentThread().getName() + "\t" + "operated time 6s ...");
        TimeUnit.SECONDS.sleep(6L);
        return 1024;
    }
}
