package review.juc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 并发控制
 *  Collections.synchronized**()
 *  juc对应的包
 *
 */
public class NotSafeDemo {

    public static void main(String[] args) {

        //线程不安全
/*        ArrayList<Object> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }).start();
        }*/

        //安全 效率低
        /*List<Object> vector = new Vector<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                vector.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(vector);
            }).start();
        }*/

        //使用Collections方法锁集合
        List<Object> list2 = Collections.synchronizedList(new ArrayList<>());
        /*for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list2.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list2);
            }).start();
        }*/

        //使用juc 包的CopyOnWriteArrayList
 /*       List<Object> list3 =new CopyOnWriteArrayList<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list3.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list3);
            }).start();
        }*/


    }

}
