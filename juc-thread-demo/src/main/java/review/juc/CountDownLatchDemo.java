package review.juc;

import java.util.concurrent.CountDownLatch;

/**
 * 同步辅助工具
 *  在执行countDownLatch.await()时会阻塞，直到countDownLatch 的计数器为0 才会继续执行主线程
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"离开教室");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"离开教室");

    }

}
