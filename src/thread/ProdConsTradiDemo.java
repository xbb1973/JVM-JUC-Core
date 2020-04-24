package thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 初始值为0的变量，两个线程交替操作，一个+1，一个-1，执行五轮
 * 多线程企业级模版口诀：
 * 1 线程  操作  资源类 (高内聚低耦合)
 * 2 判断  干活  通知
 * 3 防止虚假唤醒机制,wait await之类的方法需要放入while循环中，
 *
 * 虚假唤醒的原因：当一个消费者线程遇到产品为0时，等待，并释放锁标志，
 * 然后另外一个消费者线程获取到该锁标志，由于产品仍然为0，也等待，并释放锁标志。
 * 这时候，生产者线程获取到锁，在生产一个产品后，执行notifyAll()唤醒所有线程，
 * 这时候，一个消费者线程消费一个产品使得产品为0，另外一个消费者线程再消费一个产品使得产品变为了负数，
 * 这种现象称为虚假唤醒。在Object.wait()方法的javadoc中叙述了该如何解决这种问题：
 * 即，将get和sale方法中的if都改为while，这样，每次被唤醒后，都会再次判断产品数是否>=0：
 */
public class ProdConsTradiDemo {
    public static void main(String[] args) {
        threadBasicLearning();
        productorConsumerVersion1();

        // 版本2至于版本1的提升，使用了lock代替synchronize
        // synchronize和lock有什么区别？用新的lock有什么好处？举例
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
        //      synchronize无Condition，而synchronize只能随机或者全部唤醒。
        //      lock通过Condition可以实现分组唤醒需要唤醒的线程们，精确唤醒。
        //      跳转至SyncAndReentrantLockDemo
        // differenceOfSyncAndLock();
        productorConsumerVersion2();

        productorConsumerVersion3();

    }

    private static void differenceOfSyncAndLock() {
        synchronized (new Object()){
        }
        new ReentrantLock();
        System.out.println("check out java-p");
    }

    private static void threadBasicLearning() {
        TicketSeller ticketSeller = new TicketSeller();
        new Thread(ticketSeller, "TicketSeller1").start();
        new Thread(ticketSeller, "TicketSeller2").start();
        new Thread(ticketSeller, "TicketSeller3").start();
    }

    private static void productorConsumerVersion1() {
        Clerk clerk = new Clerk();
        Productor p1 = new Productor(clerk);
        Customer c1 = new Customer(clerk);

        // Thread t1 = new Thread(p1);
        // Thread t2 = new Thread(c1);
        // Thread t3 = new Thread(c1);
        // Thread t4 = new Thread(c1);
        // t1.setName("生产者1");
        // t2.setName("消费者1");
        // t3.setName("消费者2");
        // t4.setName("消费者3");
        // t1.start();
        // t2.start();
        // t3.start();

        new Thread(p1, "生产者1").start();
        new Thread(c1, "消费者1").start();
        new Thread(c1, "消费者2").start();
        new Thread(c1, "消费者3").start();
    }

    private static void productorConsumerVersion2() {
        ShareData shareData = new ShareData();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "Producer1").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "Producer2").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "Consumer1").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "Consumer2").start();

    }


    private static void productorConsumerVersion3() {
        System.out.println("checkout on ProdConsTradiDemo");
    }


}

class ShareData {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {
        lock.lock();
        try {
            //1 判断
            while (number != 0) {
            // if (number != 0) { // 产生虚假唤醒
                //等待，不能生产
                condition.await();
            }
            //2 干活
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //3 通知唤醒
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            //1 判断
            while (number == 0) {
            // if (number == 0) { // 产生虚假唤醒
                //等待，不能生产
                condition.await();
            }
            //2 干活
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //3 通知唤醒
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

class Clerk {
    private int num;

    // 构造器
    public Clerk() {
        super();
    }

    // 方法
    public synchronized void produce() {
        if (num >= 20) { // 停止生产
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            try {
                Thread.currentThread().sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("库存: " + getNum());
            ++num;
            System.out.println(Thread.currentThread().getName() + "生产了一个");
            notifyAll();
        }

    }

    public synchronized void consume() {
        if (num <= 0) { // 停止消费
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("库存: " + getNum());
            --num;
            System.out.println(Thread.currentThread().getName() + "客户买走了一个");
            notifyAll(); // notify all threads waiting on the condition
        }
    }

    public int getNum() {
        return num;
    }

}

class Productor implements Runnable {
    private Clerk clerk;

    // 构造器
    public Productor(Clerk clerk) {
        super();
        this.clerk = clerk;
    }

    // 方法
    public void produce() {
        clerk.produce();
    }

    @Override
    public void run() {
        // 不停的生产
        while (true) {
            produce();
        }
    }
}

class Customer implements Runnable {
    private int limit = 10;
    private Clerk clerk;

    // 构造器
    public Customer(Clerk clerk) {
        this.clerk = clerk;
    }

    // 方法
    public void consume() {
        clerk.consume();
    }

    @Override
    public void run() {
        int i = 0;
        // 不停的买
        while (i < limit) {
            consume();
            i++;
        }
    }
}

class TicketSeller implements Runnable {
    private int ticketNum = 100;
    private static Object obj = new Object();
    private Lock lock = new ReentrantLock();


    @Override
    public void run() {
        while (true) {
            lock.lock();
            // synchronized (obj) {
                if (ticketNum > 0) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(100L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "/t" + "剩余库存" + ticketNum);
                    ticketNum--;
                    lock.unlock();
                } else {
                    lock.unlock();
                    break;
                }
            // }
        }

    }
}