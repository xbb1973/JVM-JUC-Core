package jvm;

public class StrongReferenceDemo {
    public static void main(String[] args) {
        Object o1=new Object();
        // Object o2=new Object();
        Object o2 = o1;
        o1=null;
        System.gc();
        // o1会被回收，而o2是强引用不会被回收
        System.out.println(o1);
        System.out.println(o2);
    }
}
