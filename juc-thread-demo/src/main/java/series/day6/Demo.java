package series.day6;

import java.util.concurrent.TimeUnit;

/**
 * 创建线程的2中方式：继承Thread类；实现Runnable接口
 *
 * 启动线程：调用线程的start()方法
 *
 * 终止线程：调用线程的stop()方法，方法已过时，建议不要使用
 *
 * 线程中断相关的方法：调用线程实例interrupt()方法将中断标志置为true；使用线程实例方法isInterrupted()获取中断标志；调用Thread的静态方法interrupted()获取线程是否被中断，此方法调用之后会清除中断标志（将中断标志置为false了）
 *
 * wait、notify、notifyAll方法，这块比较难理解，可以回过头去再理理
 *
 * 线程挂起使用线程实例方法suspend()，恢复线程使用线程实例方法resume()，这2个方法都过时了，不建议使用
 *
 * 等待线程结束：调用线程实例方法join()
 *
 * 出让cpu资源：调用线程静态方法yeild()
 * 线程中断demo
 */
public class Demo {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    //休眠100秒
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        //sleep方法由于中断而抛出异常之后，线程的中断标志会被清除（置为false），所以在异常中需要执行this.interrupt()方法，将中断标志位置为true
                        this.interrupt();
                        e.printStackTrace();
                    }
                    if (this.isInterrupted()) {
                        System.out.println("我要退出了!");
                        break;
                    }
                    System.out.println("------------------------");
                }
            }
        };

        thread1.setName("thread1");
        thread1.start();
        TimeUnit.SECONDS.sleep(1);
        thread1.interrupt();
    }

}
