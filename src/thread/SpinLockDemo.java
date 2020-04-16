package thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class SpinLockDemo {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();
    // AtomicStampedReference<Thread> atomicReference = new AtomicStampedReference<Thread>();

    public void myLock() {
        Thread thread;
        do {
            thread = Thread.currentThread();
            System.out.println(Thread.currentThread().getName() + "\t" + " spin ======= ...");
        } while (!atomicReference.compareAndSet(null, thread));
        System.out.println(Thread.currentThread().getName() + "\t" + " come in ...");
    }

    public void myUnlock() {
        // Thread thread;
        // do {
        //     thread = Thread.currentThread();
        // } while (!atomicReference.compareAndSet(thread, null));
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "\t" + " unlock ...");
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(() -> {
            spinLockDemo.myLock();
            try {
                // TimeUnit.SECONDS.sleep(1);
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnlock();
        }, "AA").start();

        // try {
        //     TimeUnit.SECONDS.sleep(1);
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }

        new Thread(() -> {
            spinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnlock();
        }, "BB").start();
    }
}
