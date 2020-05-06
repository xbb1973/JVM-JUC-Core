package jvm;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class PhantomReferenceDemo {
    public static void main(String[] args) throws InterruptedException {
        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();

        // WeakReference weakReference = new WeakReference<>(o1, referenceQueue);
        // System.out.println(o1);
        // System.out.println(weakReference.get());
        // System.out.println(referenceQueue.poll());

        PhantomReference phantomReference = new PhantomReference(o1, referenceQueue);
        System.out.println(o1);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());

        System.out.println("GC===========");

        o1 = null;
        System.gc();
        Thread.sleep(500);

        // System.out.println(o1);
        // System.out.println(weakReference.get());
        // // weakReference GC 前会放入引用队列
        // System.out.println(referenceQueue.poll());


        System.out.println(o1);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());
    }
}
