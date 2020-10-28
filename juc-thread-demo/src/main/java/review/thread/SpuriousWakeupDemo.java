package review.thread;

/**
 * 虚假唤醒
 *  原由：wait()并不是由notify()或notifyall()唤醒
 *  解决：定义一个循环去判断是否条件真能满足
 *
 * 1.高内聚低耦合前提下，线程操作资源类
 * 2.判断|干活|通知
 * 3.多线程交互中，必须要防止多线程的虚假唤醒
 * 4.标志位
 *
 */
public class SpuriousWakeupDemo {
    public static void main(String[] args) {
        ShareData resources = new ShareData();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    resources.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "producer1").start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    resources.decrement();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, "consumer1").start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    resources.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "producer2").start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    resources.decrement();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, "consumer2").start();
    }
}

class ShareData {
    private int number = 0;//初始值为零的一个变量

    public synchronized void increment() throws InterruptedException {
        //1判断   防止虚假唤醒使用while循环判断
        if (number != 0) {
            System.out.println("生产线程" + Thread.currentThread().getName() + "准备休眠");
            this.wait();
            System.out.println("生产线程" + Thread.currentThread().getName() + "休眠结束");
        }
        //2干活
        ++number;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        //3通知
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        // 1判断
        if (number == 0) {
            this.wait();
        }
        // 2干活
        --number;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        // 3通知
        this.notifyAll();
    }
}
