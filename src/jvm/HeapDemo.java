package jvm;

/**
 * @author ：xbb
 * @date ：Created in 2020/4/28 4:49 下午
 * @description：堆
 * @modifiedBy：
 * @version:
 */
public class HeapDemo {
    public static void main(String[] args) {
        long cpuNums = Runtime.getRuntime().availableProcessors();
        System.out.println(cpuNums);

        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("-Xmx "+maxMemory);

        long totalMemory = Runtime.getRuntime().totalMemory();
        System.out.println("-Xms "+totalMemory);

        // 配置VMoption
        // -Xms8m -Xmx8m -XX:+PrintGCDetails

        // while (true) {
        //     new Object();
        // }
        byte[] bytes = new byte[1024*1024*1024];
        // [GC (Allocation Failure)
        // [PSYoungGen: 1320K->496K(2048K)] 1320K->520K(7680K), 0.0014548 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
        // [Full GC (Allocation Failure)
        // [PSYoungGen: 496K->0K(2048K)]
        // [ParOldGen: 24K->367K(5632K)] 520K->367K(7680K),
        // [Metaspace: 2909K->2909K(1056768K)], 0.0044854 secs] [Times: user=0.01 sys=0.00, real=0.01 secs]
    }
}
