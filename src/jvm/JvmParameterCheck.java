package jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author ：xbb
 * @date ：Created in 2020/5/3 10:26 上午
 * @description：查看jvm的参数
 * @modifiedBy：j
 * @version:
 */
public class JvmParameterCheck {
    public static void main(String[] args) throws InterruptedException {
        gcOverHead();
        // while (true) {
        //     new Object();
        // }
        byte[] bigSize = new byte[30*1024*1024];
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);

        // [GC (Allocation Failure) [DefNew: 2431K->1K(2432K), 0.0030385 secs] 2543K->369K(7936K), 0.0030847 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
        //                              gc前新生代内存占用->gc后(young总大小)             gc前jvm堆内存占用->gc后堆(堆总大小)



        // -Xms8m -Xmx8m -XX:+PrintGCDetails -XX:+UseSerialGC -XX:MetaspaceSize=128m -XX:MaxTenuringThreshold=10
        //
        // 查看参数方案1
        // jps
        // jinfo -flags [pid]
        // jinfo -flags [paramenter] [pid]
        // e.g.
        // jinfo -flag PrintGCDetails 61895 // 打印GC信息
        // jinfo -flag UseSerialGC 61895 // 查看是否使用串行垃圾回收器
        // jinfo -flag UseParallelGC
        // jinfo -flag MetaspaceSize 62048 // 元空间大小
        //         -XX:MetaspaceSize=21807104
        //jinfo -flag MaxTenuringThreshold 62258 // 年龄阈值上限

        // 查看参数方案2
        // java -XX:+PrintFlagsInitial // 查看默认参数
        // java -XX:+PrintFlagsFinal // 查看修改过的参数
        // java -XX:+PrintCommandLineFlags // 查看一些常见的参数，比如所使用的垃圾回收器
        // (base) xbb1973deMacBook-Pro:jvm xbb1973$ java -XX:+PrintCommandLineFlags
        // -XX:InitialHeapSize=134217728 -XX:MaxHeapSize=2147483648
        // -XX:+PrintCommandLineFlags -XX:+UseCompressedClassPointers
        // -XX:+UseCompressedOops -XX:+UseParallelGC

        // 修改
        // java -XX:+PrintFlagsFinal -XX:MetaspaceSize=21810376 xx.java

        // jinfo -flag ThreadStackSize 1028
        //         -XX:ThreadStackSize=1024


        // 垃圾回收参数设置+查看
        // DefNew:Default New Gen
        // Tenured:Old
        // ParNew:Parallel New Gen
        // PSYoungGen:Parallel Scavenge
        // ParOldGen:Parallel Old Gen
        //
        // 新生代配置，会自动激活老年代的配置
        // -Xms8m -Xmx8m -XX:+PrintGCDetails -XX:+UseSerialGC
        // -Xms8m -Xmx8m -XX:+PrintGCDetails -XX:+UseParNewGC
        // -Xms8m -Xmx8m -XX:+PrintGCDetails -XX:+UseParallelGC
        //      GC (Allocation Failure) [PSYoungGen:] [ParOldGen:
        //
        // 老年代配置，也会激活新生代的配置
        // -Xms8m -Xmx8m -XX:+PrintGCDetails -XX:+UseParallelOldGC
        // -Xms8m -Xmx8m -XX:+PrintGCDetails // 不加用默认 -XX:+UseParallelGC
        // -Xms8m -Xmx8m -XX:+PrintGCDetails -XX:+UseConcMarkSweepGC
        //      [GC (CMS Initial Mark) [1 CMS-initial-mark: 3792K(5504K)] 4090K(7936K), 0.0011402 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
        //      [CMS-concurrent-mark-start]
        //      [CMS-concurrent-mark: 0.005/0.005 secs] [Times: user=0.02 sys=0.00, real=0.00 secs]
        //      [CMS-concurrent-preclean-start]
        //      [CMS-concurrent-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
        //      [GC (CMS Final Remark)
        //      [CMS-concurrent-sweep-start]

        // G1 特殊
        // -Xms8m -Xmx8m -XX:+PrintGCDetails -XX:+UseG1GC
        //
        // 内存块被分为各个regiion，用于分配eden survivor old old-H
        // Heap
        //  garbage-first heap   total 8192K, used 377K [0x00000007bf800000, 0x00000007bf900040, 0x00000007c0000000)
        //   region size 1024K, 1 young (1024K), 0 survivors (0K)
        //  Metaspace       used 3115K, capacity 4500K, committed 4864K, reserved 1056768K
        //   class space    used 342K, capacity 388K, committed 512K, reserved 1048576K
        //
        // GC四个阶段
        //
        // [GC concurrent-root-region-scan-start]
        // [GC concurrent-root-region-scan-end, 0.0009493 secs]
        // [GC concurrent-mark-start]
    }

    private static void gcOverHead() {
        int i = 0;
        List<String> list = new ArrayList<>();
        try {
            while (true) {
                list.add(String.valueOf(++i).intern());
                new Object();
            }
        } catch (Exception e) {
            System.out.println("************i" + i);
            e.printStackTrace();
            throw e;
        }
    }
}
