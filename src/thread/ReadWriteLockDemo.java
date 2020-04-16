package thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 总结：读-读共存、读-写不共存、写-写不共存。
 * 写操作：原子性+线程独占；
 * 读操作：
 * 缓存三大常规操作，读、写、清空，get/ set/ clear/
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache cache = new MyCache();
        //写
        for (int i = 1; i <= 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                cache.put(tempInt + "", tempInt + "");
            }, String.valueOf(i)).start();
        }
        //读
        for (int i = 1; i <= 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                cache.get(tempInt + "");
            }, String.valueOf(i)).start();
        }
    }
}

/**
 * 缓存三大常规操作，读、写、清空，get/ set/ clear/
 */
class MyCache {
    // 缓存更新快，需要用volatile修饰
    private Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private Lock lock = new ReentrantLock(true);

    // public void put(String key, Object value) {
    //     System.out.println(Thread.currentThread().getName() + "\t" + "正在写入： " + key);
    //     //模拟网络传输
    //     try {
    //         TimeUnit.MILLISECONDS.sleep(300);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    //     map.put(key, value);
    //     System.out.println(Thread.currentThread().getName() + "\t" + "写入完成");
    // }
    //
    // public void get(String key) {
    //     System.out.println(Thread.currentThread().getName() + "\t" + "正在读取： " + key);
    //     //模拟网络传输
    //     try {
    //         TimeUnit.MILLISECONDS.sleep(300);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    //     Object result = map.get(key);
    //     System.out.println(Thread.currentThread().getName() + "\t" + "读取完成： " + result);
    // }

    // public synchronized void put(String key, Object value) {
    //     System.out.println(Thread.currentThread().getName() + "\t" + "正在写入： " + key);
    //     //模拟网络传输
    //     try {
    //         TimeUnit.MILLISECONDS.sleep(300);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    //     map.put(key, value);
    //     System.out.println(Thread.currentThread().getName() + "\t" + "写入完成");
    // }
    //
    // public synchronized void get(String key) {
    //     System.out.println(Thread.currentThread().getName() + "\t" + "正在读取： " + key);
    //     //模拟网络传输
    //     try {
    //         TimeUnit.MILLISECONDS.sleep(300);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    //     Object result = map.get(key);
    //     System.out.println(Thread.currentThread().getName() + "\t" + "读取完成： " + result);
    // }

    // public void put(String key, Object value) {
    //     lock.lock();
    //     try {
    //         System.out.println(Thread.currentThread().getName() + "\t" + "正在写入： " + key);
    //
    //         //模拟网络传输
    //         try {
    //             TimeUnit.MILLISECONDS.sleep(300);
    //         } catch (Exception e) {
    //             e.printStackTrace();
    //         }
    //         map.put(key, value);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     } finally {
    //         lock.unlock();
    //     }
    //     System.out.println(Thread.currentThread().getName() + "\t" + "写入完成");
    // }
    //
    // public void get(String key) {
    //     lock.lock();
    //     Object result = null;
    //     try {
    //         System.out.println(Thread.currentThread().getName() + "\t" + "正在读取： " + key);
    //
    //         //模拟网络传输
    //         try {
    //             TimeUnit.MILLISECONDS.sleep(300);
    //         } catch (Exception e) {
    //             e.printStackTrace();
    //         }
    //         result = map.get(key);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     } finally {
    //         lock.unlock();
    //     }
    //     System.out.println(Thread.currentThread().getName() + "\t" + "读取完成： " + result);
    // }

    public void put(String key, Object value) {
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t" + "正在写入： " + key);
            //模拟网络传输
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                rwLock.writeLock().unlock();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t" + "写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void get(String key) {
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t" + "正在读取： " + key);
            //模拟网络传输
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t" + "读取完成： " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            rwLock.readLock().unlock();
        }
    }
}
