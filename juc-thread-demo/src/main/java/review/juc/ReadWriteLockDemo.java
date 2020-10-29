package review.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 多线程读可以不用锁
 * 多线程写时为保证数据一致性加上锁
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {

        MyCache myCache = new MyCache();
        for (int i = 1; i <= 5; i++) {
            final int tmp = i;
            new Thread(() -> {
                myCache.put(tmp + "", tmp + "");
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++) {
            final int tmp = i;
            new Thread(() -> {
                myCache.get(tmp+"");
            },String.valueOf(i)).start();
        }


    }
}

class MyCache {
    private volatile HashMap<String, Object> map = new HashMap();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        //加写锁，保证数据的一致性
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t写入数据" + key);

            TimeUnit.MILLISECONDS.sleep(300);

            
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void get(String key) {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t读取数据" + key);
            String o = (String) map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t读取完成" + o);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}