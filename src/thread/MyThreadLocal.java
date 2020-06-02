package thread;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ：xbb
 * @date ：Created in 2020/6/1 2:42 下午
 * @description：
 * @modifiedBy：
 * @version:
 */
public class MyThreadLocal {
    public static ThreadLocal<Long> x = new ThreadLocal<Long>() {
        @Override
        protected Long initialValue() {
            System.out.println("Initial Value run ..");
            return Thread.currentThread().getId();
        }
    };

    public static MyDefThreadLocal<Long> myDefThreadLocal = new MyDefThreadLocal<Long>() {
        @Override
        protected Long initialValue() {
            System.out.println("Initial Value run ..");
            return Thread.currentThread().getId();
        }
    };

    public static MyDefThreadLocal<Long> myDefThreadLocal2 = new MyDefThreadLocal<Long>() {
        @Override
        protected Long initialValue() {
            System.out.println("Initial Value run ..");
            return Thread.currentThread().getId();
        }
    };

    public static void main(String[] args) {
        // System.out.println(x.get());
        // x.set((long) 2000);
        // System.out.println(x.get());
        int count = 5;
        // for (int i = 0; i < count; i++) {
        //     new Thread(() -> {
        //         System.out.println(x.get());
        //     }, "thread" + i).start();
        // }
        // System.out.println(x.get());
        // x.set((long) 8080);
        // x.remove();
        // System.out.println(x.get());

        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                System.out.println(myDefThreadLocal.get());
                System.out.println(myDefThreadLocal);
            }, "thread" + i).start();
        }

        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                System.out.println(myDefThreadLocal2.get());
                System.out.println(myDefThreadLocal2);
            }, "thread" + i).start();
        }

    }

}

class MyDefThreadLocal<T> {
    private static AtomicInteger atomicInteger = new AtomicInteger();
    Integer threadLocalHash = atomicInteger.addAndGet(0x61c88647);
    private static HashMap<Thread, HashMap<Integer, Object>> threadHashMapHashMap = new HashMap<>();

    private synchronized static HashMap<Integer, Object> getMap() {
        Thread thread = Thread.currentThread();
        if (!threadHashMapHashMap.containsKey(thread)) {
            threadHashMapHashMap.put(thread, new HashMap<Integer, Object>());
        }
        return threadHashMapHashMap.get(thread);
    }

    protected T initialValue() {
        return null;
    }

    public T get() {
        HashMap<Integer, Object> map = getMap();
        if (!map.containsKey(this.threadLocalHash)) { // this是该对象
            map.put(this.threadLocalHash, initialValue());
        }
        return (T) map.get(this.threadLocalHash);
    }

    public void set(T val) {
        HashMap<Integer, Object> map = getMap();
        map.put(this.threadLocalHash, val);
    }

    @Override
    public String toString() {
        return "MyDefThreadLocal{" +
                "threadLocalHash=" + threadLocalHash +
                '}';
    }
}