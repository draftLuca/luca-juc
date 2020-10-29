package review.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 *
 * 用于锁定资源，构造permits表只能三个线程可以同时访问semaphore.acquire()标注的资源
 * semaphore.acquire() 获取
 *
 */
public class SemaphoreDemo {

    public static void main(String[] args) {

        //模拟资源类，3个空车位
        Semaphore semaphore = new Semaphore(3);
        for (int i=1;i<=6;i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t抢到车位");
                    TimeUnit.SECONDS.sleep(4);
                    System.out.println(Thread.currentThread().getName()+"\t离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }

    }

}
