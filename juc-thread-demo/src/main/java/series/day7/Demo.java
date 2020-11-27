package series.day7;

/**
 * volatile && JMM
 *   volation 特点：
 *     1 线程中读取的时候，每次读取都会去主内存中读取共享变量最新的值，然后将其复制到工作内存
 *     2 线程中修改了工作内存中变量的副本，修改之后会立即刷新到主内存
 *
 *
 */
public class Demo {
    /**
     * 程序不会停止原因：
     *  主线程修改了flag之后，未将其刷新到主内存，所以t1看不到
     *  主线程将flag刷新到了主内存，但是t1一直读取的是自己工作内存中flag的值，没有去主内存中获取flag最新的值
     */
    public static boolean flag = true;

    /**
     * 解决以上问题：
     *  是否有这样的方法：线程中修改了工作内存中的副本之后，立即将其刷新到主内存；
     *  工作内存中每次读取共享变量时，都去主内存中重新读取，然后拷贝到工作内存。
     * 变量上加上volatile可以达到以上效果
     */
    public volatile static boolean flag1 = true;

    public static class T1 extends Thread {
        public T1(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println("线程" + this.getName() + " in");
            while (flag) {
                ;
            }
            System.out.println("线程" + this.getName() + "停止了");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new T1("t1").start();
        //休眠1秒
        Thread.sleep(1000);
        //将flag置为false
        flag = false;
    }
}
