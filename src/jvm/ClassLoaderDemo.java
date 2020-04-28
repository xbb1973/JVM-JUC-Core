package jvm;

/**
 * @author ：xbb
 * @date ：Created in 2020/4/28 11:58 上午
 * @description：类加载器
 * @modifiedBy：
 * @version:
 */
public class ClassLoaderDemo {
    public static void main(String[] args) {
        Object obj = new Object();
        // BootstrapCL实际打印为null
        System.out.println(obj.getClass().getClassLoader());
        // System.out.println(obj.getClass().getClassLoader().getParent());
        // System.out.println(obj.getClass().getClassLoader().getParent().getParent());

        ClassLoaderDemo myClassLoaderDemo = new ClassLoaderDemo();
        System.out.println(myClassLoaderDemo.getClass().getClassLoader());
        System.out.println(myClassLoaderDemo.getClass().getClassLoader().getParent());
        System.out.println(myClassLoaderDemo.getClass().getClassLoader().getParent().getParent());

    }
}
