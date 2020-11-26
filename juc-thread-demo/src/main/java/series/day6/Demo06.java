package series.day6;

import java.util.concurrent.TimeUnit;

/**
 * wait() && notify()
 *  条件：
 *     1 都必须在枷锁的代码块中才能够调用
 *     2 方法属于object对象 ，wait() 和 notify() 要属于同一个对象才能够起到作用
 */
public class Demo06 {
    static Demo06 demo = new Demo06();
    static Demo06 demo1 = new Demo06();
    static Object object = new Object();
    static Object object2 = new Object();

    public static class T1 extends Thread {
        @Override
        public void run() {
            synchronized (demo) {
                System.out.println(System.currentTimeMillis() + ":T1 start!");
                try {
                    System.out.println(System.currentTimeMillis() + ":T1 wait for object");
                    demo.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + ":T1 end!");
            }
        }
    }

    public static class T2 extends Thread {
        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                this.interrupt();
                e.printStackTrace();
            }
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + ":T2 start，notify one thread! ");
                object.notify();
                System.out.println(System.currentTimeMillis() + ":T2 end!");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new T1().start();
        new T2().start();
    }
}
