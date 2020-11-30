package series.day8;

/**
 * 主线程的线程组为main
 *
 * 根线程组为system
 */
public class Demo3 {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread());
        System.out.println(Thread.currentThread().getThreadGroup());
        System.out.println(Thread.currentThread().getThreadGroup().getParent());
        System.out.println(Thread.currentThread().getThreadGroup().getParent().getParent());
    }
}
