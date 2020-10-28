package review.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaleTiket {
    public static void main(String[] args) {

        final Ticket ticket = new Ticket();
        new Thread(() -> {
            for (int i = 0; i < 4000; i++) ticket.saleTicker();
        }, "threadA:").start();
        new Thread(() -> {
            for (int i = 0; i < 4000; i++) ticket.saleTicker();
        }, "threadB:").start();
        new Thread(() -> {
            for (int i = 0; i < 4000; i++) ticket.saleTicker();
        }, "threadC:").start();
    }
}


class Ticket {

    private int number = 3000;
    private Lock lock = new ReentrantLock();

    public void saleTicker() {

        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出第\t" + (number--) + "张票,余票：" + number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}