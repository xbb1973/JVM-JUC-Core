package thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 1、队列
 *
 * 2、阻塞队列
 *  2-1、阻塞队列的优点？
 *
 *  2-2、不得不阻塞，如何管理？
 *
 *
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(4);
        addAndRemove(blockingQueue);
        offerAndPoll(blockingQueue);
        putAndTake(blockingQueue);
        outOfTime(blockingQueue);
    }

    private static void outOfTime(BlockingQueue<String> blockingQueue) throws InterruptedException {
        System.out.println(blockingQueue.offer("a",2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a",2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a",2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("a",2L, TimeUnit.SECONDS));
    }

    private static void putAndTake(BlockingQueue<String> blockingQueue) throws InterruptedException {
        blockingQueue.put("a3");
        blockingQueue.put("b3");
        blockingQueue.put("c3");
        blockingQueue.put("d3");
        // blockingQueue.put("x3"); // 已满阻塞
        blockingQueue.offer("x3", 5L, TimeUnit.SECONDS); // 超时取消阻塞，阻塞一定时长
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        // System.out.println(blockingQueue.take()); // 已空阻塞
    }

    private static void offerAndPoll(BlockingQueue<String> blockingQueue) {
        System.out.println(blockingQueue.offer("a2"));
        System.out.println(blockingQueue.offer("b2"));
        System.out.println(blockingQueue.offer("c2"));
        System.out.println(blockingQueue.offer("e2"));
        // System.out.println(blockingQueue.offer("x2"));
        System.out.println(blockingQueue.peek());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
    }

    private static void addAndRemove(BlockingQueue<String> blockingQueue) {
        System.out.println(blockingQueue.add("a1"));
        System.out.println(blockingQueue.add("b1"));
        System.out.println(blockingQueue.add("c1"));
        System.out.println(blockingQueue.add("e1"));
        // System.out.println(blockingQueue.add("x1"));
        System.out.println(blockingQueue.element());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        // System.out.println(blockingQueue.remove());
    }
}
