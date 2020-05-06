package jvm;

/**
 * @author ：xbb
 * @date ：Created in 2020/5/3 10:26 上午
 * @description：查看jvm的参数
 * @modifiedBy：j
 * @version:
 */
public class JvmParameterCheck {
    public static void main(String[] args) throws InterruptedException {
        while (true) {
            new Object();
        }
        // TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);

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
        // jinfo -flag UseSerialGC 61895 // 串行垃圾回收器
        // jinfo -flag MetaspaceSize 62048 // 元空间大小
        //         -XX:MetaspaceSize=21807104
        //jinfo -flag MaxTenuringThreshold 62258 // 年龄阈值上限

        // 查看参数方案2
        // java -XX:+PrintFlagsInitial // 查看默认参数
        // java -XX:+PrintFlagsFinal // 查看修改过的参数
        // java -XX:+PrintCommandLineFlags // 查看一些常见的参数，比如所使用的垃圾回收器

        // 修改
        // java -XX:+PrintFlagsFinal -XX:MetaspaceSize=21810376 xx.java

        // jinfo -flag ThreadStackSize 1028
        //         -XX:ThreadStackSize=1024

    }
}
