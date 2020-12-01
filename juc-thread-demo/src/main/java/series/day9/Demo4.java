package series.day9;

import java.util.concurrent.TimeUnit;

/**
 *用户线程和守护线程:
 *  1:java中的线程分为用户线程和守护线程
 *
 *  2:程序中的所有的用户线程结束之后，不管守护线程处于什么状态，java虚拟机都会自动退出
 *
 *  3:调用线程的实例方法setDaemon()来设置线程是否是守护线程
 *
 *  4:setDaemon()方法必须在线程的start()方法之前调用，在后面调用会报异常，并且不起效
 *
 * 线程的daemon默认值和其父线程一样
 *
 *  dameon的默认值为为父线程的daemon:
 *      也就是说，父线程如果为用户线程，子线程默认也是用户现场，父线程如果是守护线程，子线程默认也是守护线程。
 */
public class Demo4 {
    public static class T1 extends Thread {
        public T1(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println(this.getName() + ".daemon:" + this.isDaemon());
        }
    }

    public static void main(String[] args) throws InterruptedException {

        System.out.println(Thread.currentThread().getName() + ".daemon:" + Thread.currentThread().isDaemon());

        /*
        t1是由主线程(main方法所在的线程)创建的，main线程是t1的父线程，所以t1.daemon为false，说明t1是用户线程
         */
        T1 t1 = new T1("t1");
        t1.start();

        Thread t2 = new Thread() {
            @Override
            public void run() {
                System.out.println(this.getName() + ".daemon:" + this.isDaemon());
                /*
                t2线程调用了 setDaemon(true);将其设为守护线程，t3是由t2创建的，所以t3默认线程类型和t2一样，t2.daemon为true。
                 */
                T1 t3 = new T1("t3");
                t3.start();
            }
        };

        t2.setName("t2");
        t2.setDaemon(true);
        t2.start();

        TimeUnit.SECONDS.sleep(2);
    }
}
