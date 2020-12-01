package series.day9;

/**
 * day9:用户线程和守护线程:
 *  守护线程是一种特殊的线程，在后台默默地完成一些系统性的服务，比如垃圾回收线程、JIT线程都是守护线程。
 *  与之对应的是用户线程，用户线程可以理解为是系统的工作线程，它会完成这个程序需要完成的业务操作。
 *  如果用户线程全部结束了，意味着程序需要完成的业务操作已经结束了，系统可以退出了。
 *  所以当系统只剩下守护进程的时候，java虚拟机会自动退出。
 *  java线程分为用户线程和守护线程，线程的daemon属性为true表示是守护线程，false表示是用户线程
 *
 *
 *
 */
public class Demo1 {

    public static class T1 extends Thread {
        public T1(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println(this.getName() + "开始执行," + (this.isDaemon() ? "我是守护线程" : "我是用户线程"));
            while (true) ;
        }
    }

    public static void main(String[] args) {

        //程序只有守护线程时，系统会自动退出 此时程序会死循环不会退出
        /*T1 t1 = new T1("子线程1");
        t1.start();*/

        //此时ti被设置为守护线程，用户线程结束了，不管守护线程是否结束，程序都会结束，setDaemon()必须在start()前才生效
        T1 t1 = new T1("子线程1");
        t1.setDaemon(true);
        t1.start();

        System.out.println("主线程结束");
    }
}