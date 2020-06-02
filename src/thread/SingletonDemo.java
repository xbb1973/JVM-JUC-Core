package thread;

public class SingletonDemo {
    // private static SingletonDemo singletonDemo = null;
    private static volatile SingletonDemo singletonDemo = null;

    private SingletonDemo() {
        System.out.println(Thread.currentThread().getName() + "\t 我是构造方法");
    }

    //DCL模式 Double Check Lock 双端检索机制：在加锁前后都进行判断
    public static /*synchronized*/ SingletonDemo getInstance() {

        if (singletonDemo == null) {
            synchronized (SingletonDemo.class) {
                if (singletonDemo == null) {
                    singletonDemo = new SingletonDemo();
                }
            }
        }
        return singletonDemo;
    }

    public static void main(String[] args) throws InterruptedException {
        // for (int i = 0; i < 10000; i++) {
        //     new Thread(() -> {
        //         SingletonDemo.getInstance();
        //     }, String.valueOf(i + 1)).start();
        // }

        int n = 1000;
        for (int i = 0; i < n; i++) {
            new Thread(()->{
                SingletonTest.getInstance();
            }).start();
        }
    }
}

class SingletonTest {
    // private volatile static SingletonTest singletonTest = null;
    private static SingletonTest singletonTest = new SingletonTest();

    SingletonTest() {
        System.out.println("getInstance");
    }

    public static SingletonTest getInstance() {
        // if (singletonTest == null) {
        //     synchronized (SingletonTest.class){
        //         if (singletonTest == null) {
        //             singletonTest = new SingletonTest();
        //         }
        //     }
        // }
        return singletonTest;
    }
}


