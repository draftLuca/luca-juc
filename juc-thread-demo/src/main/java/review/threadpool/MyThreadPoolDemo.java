package review.threadpool;

import java.sql.Time;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程池构造参数
 * int corePoolSize,  实际线程数
 * int maximumPoolSize,  线程池最大线程数
 * long keepAliveTime,
 * TimeUnit unit,       时间单位
 * BlockingQueue<Runnable> workQueue,    线程数满时，保存任务的阻塞队列
 * ThreadFactory threadFactory,          线程工厂
 * RejectedExecutionHandler handler      线程数和阻塞队列满时 的 拒绝策略
 *      AbortPolicy    超出时报错
 *      CallerRunsPolicy   在任务被拒绝添加后，会用调用execute函数的上层线程去执行被拒绝的任务
 *      DiscardOldestPolicy 当任务呗拒绝添加时，会抛弃任务队列中最旧的任务也就是最先加入队列的，再把这个新任务添加进去。
 *      DiscardPolicy       默默地丢弃
 */
public class MyThreadPoolDemo {

    public static void main(String[] args) {

        //5个线程 固定数量
        ExecutorService threadPool0 = Executors.newFixedThreadPool(5);

        //1池1线程
        ExecutorService threadPool1 = Executors.newSingleThreadExecutor();

        //1池N线程
        ExecutorService threadPool = Executors.newCachedThreadPool();

        try {
            for (int i = 1; i <= 10 ; i++) {
//                TimeUnit.SECONDS.sleep(1);
                final int temp = i;
                threadPool.execute(()->{
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        if (temp == 3){
                            throw new InterruptedException();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName());
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }

}
