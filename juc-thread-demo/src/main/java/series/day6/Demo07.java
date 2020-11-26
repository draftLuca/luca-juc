package series.day6;

import java.util.concurrent.TimeUnit;

/**
 *挂起（suspend）和继续执行（resume）线程
 * 不推荐使用：
 *  suspend()方法去挂起线程是因为suspend()方法导致线程暂停的同时，并不会释放任何锁资源。
 *  此时，其他任何线程想要访问被它占用的锁时，都会被牵连，导致无法正常运行（如图2.7所示）。
 *  直到在对应的线程上进行了resume()方法操作，被挂起的线程才能继续，从而其他所有阻塞在相关锁上的线程也可以继续执行。
 *  但是，如果resume()方法操作意外地在suspend()方法前就被执行了，那么被挂起的线程可能很难有机会被继续执行了。
 *  并且，更严重的是：它所占用的锁不会被释放，因此可能会导致整个系统工作不正常。
 *  而且，对于被挂起的线程，从它线程的状态上看，居然还是Runnable状态，这也会影响我们队系统当前状态的判断。
 */
public class Demo07 {
    static Object object = new Object();

    public static class T1 extends Thread {
        public T1(String name) {
            super(name);
        }

        @Override
        public void run() {
            synchronized (object) {
                System.out.println("in " + this.getName());
                Thread.currentThread().suspend();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T1 t1 = new T1("t1");
        t1.start();
        Thread.sleep(100);
        T1 t2 = new T1("t2");
        t2.start();
        t1.resume();
        t2.resume();
        t1.join();
        t2.join();
    }
}
