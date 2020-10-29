package review.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 抛出异常：add() remove() element()
 * 特殊值：offer(e) poll() peek()
 * 阻塞： put(e) take()
 * 超时： offer(e,time,unit) poll(time,unit)
 *
 */
public class BrokingQueueDemo {

    public static void main(String[] args) throws InterruptedException {

        //抛出异常
        BlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        System.out.println(blockingQueue.offer("sd"));

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.peek());
        System.out.println(blockingQueue.poll());
//        System.out.println(blockingQueue.remove());
//        blockingQueue.offer("sd")



    }

}
