package thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile+atomicInteger(+CAS+原子引用)+BlockQueue+线程交互
 */
public class ProdConsBlockQueueDemo {
    public static void main(String[] args) {
        // MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));
        MyResource myResource = new MyResource(new LinkedBlockingQueue<>(10));
        // MyResource myResource = new MyResource(new SynchronousQueue<>());

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t生产线程启动");
            try {
                myResource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "prod").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t消费线程启动");
            try {
                myResource.myCons();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "cons").start();


        try {
            TimeUnit.SECONDS.sleep(8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("8秒钟后，叫停");
        myResource.stop();
    }
}

class MyResource {
    private volatile boolean FLAG = true; //默认开启，进行生产+消费
    private AtomicInteger atomicInteger = new AtomicInteger(); // 舍弃i++等非原子操作

    // 设值注入、构造注入
    private BlockingQueue<String> blockingQueue;

    // 不断抽象
    // 传接口不传类，扩展性强，传入不同的ArraryBQ、LinkBQ，都能够兼容
    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        // 打印日志判断
        // System.out.println(blockingQueue.getClass().getName());

    }

    public void myProd() throws Exception {
        String data = null;
        boolean retValue;
        while (FLAG) {
            data = atomicInteger.incrementAndGet() + ""; // ++i
            retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS); // 2s超时
            if (retValue) {
                System.out.println(Thread.currentThread().getName() + "\t" + "插入队列" + data + "成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t" + "插入队列" + data + "失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName() + "\tFLAG==false，停止生产");
    }

    public void myCons() throws Exception {
        String res;
        while (FLAG) {
            res = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (null == res || res.equalsIgnoreCase("")) {
                FLAG = false;
                System.out.println(Thread.currentThread().getName() + "\t超过2秒钟没有消费，退出消费");
                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t消费队列" + res + "成功");
        }
    }

    public void stop() {
        this.FLAG = false;
    }
}
