package thread;

import java.util.*;

public class ContainerNotSafeDemo {
    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        for (Integer integer:
        integers) {
            
        }
        // myListNotSafe();
        listNotSafe();
        // setNoSafe();
        // mapNotSafe();

    }

    private static void mapNotSafe() {
        Map<String,String> map=new HashMap<>();
        // Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(Thread.currentThread().getName() + "\t" + map);
            }, String.valueOf(i)).start();
        }
    }

    private static void setNoSafe() {
        // 在HashSet中，元素都存到HashMap键值对的Key上面，
        // 而Value时有一个统一的值private static final Object PRESENT = new Object();，
        // (定义一个虚拟的Object对象作为HashMap的value，将此对象定义为static final。)
        Set<String> set=new HashSet<>();
        // Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 1; i <= 100000; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                // System.out.println(Thread.currentThread().getName() + "\t" + set);
            }, String.valueOf(i)).start();
        }
        while (Thread.activeCount() > 2) {

        }
        System.out.println(set.size());
    }

    private static void listNotSafe() {
        List<String> list = new ArrayList<>();
        // List<String> list = new Vector<>();
        // List<String> list = Collections.synchronizedList(new ArrayList<>());
        // List<String> list = new CopyOnWriteArrayList<>();
        // 30个线程会出ConcurrentModificationException
        for (int i = 1; i <= 10000; i++) {
            new Thread(() -> {
                // synchronized (ContainerNotSafeDemo.class){
                    list.add(UUID.randomUUID().toString().substring(0, 8));
                // }
                // System.out.println(Thread.currentThread().getName() + "\t" + list);
            }, String.valueOf(i)).start();
        }
        while (Thread.activeCount() > 2) {

        }
        System.out.println(list.size());
    }

    /**
     * 1、单线程 fail-fast
     * 2、多线程 fail-fast
     */
    private static void myListNotSafe() {

        // 1、单线程 fail-fast
        // 对Vector、ArrayList在迭代的时候如果同时对其进行修改，
        // 就会抛出java.util.ConcurrentModificationException异常。
        List<Integer> list = new ArrayList<Integer>();
        System.out.println("我是单线程fail-fast代码，执行！");
        list.add(2);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer integer = iterator.next();
            if (integer == 2) {
                // KEY ERROR CODE
                // {
                //     System.out.println("ERROR EXAMPLE");
                //     list.remove(integer);
                // }
                // RIGHT WAY TO DO
                {
                    System.out.println("DO THE RIGHT THING");
                    iterator.remove();
                }
            }
        }
        // 2、多线程
        // 因为用Arrays.asList()得到的是数组，不能resized。即不能增加删除，只能替换。
        // 所以remove时会出现UnsupportedOperationException的异常。
        // Exception in thread "t2" java.lang.UnsupportedOperationException
        // List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5, 6);

        // add方法来模拟JUC 异常，得到t1的一个异常，虽然已经使用了iterator.remove方法。
        // Exception in thread "t1" java.util.ConcurrentModificationException
        // List<Integer> list2 = new ArrayList<>();
        List<Integer> list2 = new Vector<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(4);
        list2.add(5);
        Iterator<Integer> iterator1 = list2.iterator();
        Iterator<Integer> iterator2 = list2.iterator();
        new Thread(() -> {
            while (iterator1.hasNext()) {
                Integer integer = iterator1.next();
                System.out.println(integer);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();
        new Thread(() -> {
            while (iterator2.hasNext()) {
                Integer integer = iterator2.next();
                if (integer == 5)
                    iterator2.remove();
            }
        }, "t2").start();

    }


}

