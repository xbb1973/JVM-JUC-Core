package jvm;

import java.util.ArrayList;
import java.util.List;

//-Xms10m -Xmx10m -XX:MaxDirectMemorySize=5m -XX:+PrintGCDetails
public class GCOverheadDemo {
    // private byte[] bigSize = new byte[2*1024*1024];
    Object instance = null;

    public static void main(String[] args) {
        // autoGC();
        referenceCountGC();
    }

    private static void referenceCountGC() {
        GCOverheadDemo overheadDemo1 = new GCOverheadDemo();
        GCOverheadDemo overheadDemo2 = new GCOverheadDemo();
        overheadDemo1.instance = overheadDemo2;
        overheadDemo2.instance = overheadDemo1;
        overheadDemo1 = null;
        overheadDemo2 = null;
        System.gc();
    }

    private static void autoGC() {
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
