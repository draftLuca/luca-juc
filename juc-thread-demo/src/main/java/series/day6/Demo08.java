package series.day6;

/**
 * 等待线程结束（join）和谦让（yeild）
 *
 */
public class Demo08 {
    static int num = 0;

    public static class T1 extends Thread {
        public T1(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + ",start " + this.getName());
            for (int i = 0; i < 10; i++) {
                num++;
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(System.currentTimeMillis() + ",end " + this.getName());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T1 t1 = new T1("t1");
        t1.start();
        t1.join();
        //num的结果为10，1、3行的时间戳相差2秒左右，说明主线程等待t1完成之后才继续执行的。
        System.out.println(System.currentTimeMillis() + ",num = " + num);
    }
}
