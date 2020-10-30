package review.threadpool;

import java.util.concurrent.*;

/**
 * 一个大任务 使用线程池开启多个线程执行，最后结果合并
 */
public class ForkJoinDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        MyTask myTask = new MyTask(0, 10000);
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        ForkJoinTask<Integer> submit = forkJoinPool.submit(myTask);
        System.out.println(submit.get());
    }
}


class MyTask extends RecursiveTask<Integer> {

    private static final Integer ADJUST_VALUE = 10;

    private int begin;
    private int end;
    private int result;

    public MyTask(int begin, int middle) {
        this.begin = begin;
        this.end = middle;
    }

    @Override
    protected Integer compute() {
        if (end-begin <= ADJUST_VALUE) {
            for (int i = begin; i <= end; i++) {
                result = result + i;
            }
        }else {
            int middle = (end +begin)/2;
            MyTask myTask = new MyTask(begin, middle);
            MyTask myTask2 = new MyTask(middle +1, end);
            myTask.fork();
            myTask2.fork();
            result = myTask.join() + myTask2.join();
        }
        return result;
    }
}