package review.thread;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 1 GET方法一般放最后一行
 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask(()->{
            TimeUnit.SECONDS.sleep(4);
            System.out.println("come in");
            return 2;
        });
        //同一个futureTask只执行一次
        new Thread(futureTask,"q").start();
        new Thread(futureTask,"c").start();

        //futureTask.get() 获取线程返回值 阻塞方法
        System.out.println(futureTask.get());
    }

}


class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        return 111;
    }
}