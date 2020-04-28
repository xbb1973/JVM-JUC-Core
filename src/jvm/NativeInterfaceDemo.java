package jvm;

/**
 * @author ：xbb
 * @date ：Created in 2020/4/28 12:47 下午
 * @description：本地方法接口
 * @modifiedBy：
 * @version:
 */
public class NativeInterfaceDemo {
    public static void main(String[] args) {
        // Throwable
        Thread thread = new Thread();
        thread.start(); // 实际实现为start0 private native void start0();
        thread.start(); // Exception in thread "main" java.lang.IllegalThreadStateException


    }
}
