package thread;

import java.util.concurrent.TimeUnit;

/**
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
 *      3、检测，资源分配图，进行化简，若无法化简则为死锁
 *      4、解除，资源剥夺法-挂起死锁进程/ 撤销进程法-干掉死锁进程/ 进程回退法-回退至可避免状态
 * 银行家算法：Available、Max、Allcoate、Need、Request
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldLockThread(lockA, lockB), "ThreadA").start();
        new Thread(new HoldLockThread(lockB, lockA), "ThreadB").start();
        /**
         * 如何确认是死锁导致
         *
         * linux ps -ef|grep xxxx     ls -l
         * jps -l
         */
    }
}

class HoldLockThread implements Runnable {
    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "\t" + "持有"+lockA+"尝试获取"+lockB);
            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName() + "\t" + "持有"+lockB);
            }
        }
    }
}
