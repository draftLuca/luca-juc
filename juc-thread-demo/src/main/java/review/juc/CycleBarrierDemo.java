package review.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *
 * 和countDownLatch 相反，CyclicBarrier使用的时加法，当加到构造的parties时，
 * 执行参数二中的runable体内容（由最后一个线程执行）
 * eg输出：
 * 0	收集到第0颗龙珠
 * 4	收集到第4颗龙珠
 * 5	收集到第5颗龙珠
 * 6	收集到第6颗龙珠
 * 1	收集到第1颗龙珠
 * 2	收集到第2颗龙珠
 * 3	收集到第3颗龙珠
 * 3召唤神龙
 *
 */
public class CycleBarrierDemo {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println(Thread.currentThread().getName()+"召唤神龙！");
        });
        for (int i = 0; i < 7; i++) {
            final int tmp = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t收集到第"+tmp+"颗龙珠");
                try {
                    int await = cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }

    }

}
