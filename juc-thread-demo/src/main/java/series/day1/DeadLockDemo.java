package series.day1;

import jdk.internal.org.objectweb.asm.tree.MultiANewArrayInsnNode;

/**
 * day1:
 *      1:同步（Synchronous）和异步（Asynchronous）
 *      2:并发（Concurrency）和并行（Parallelism）
 *      3:临界区(资源）
 *          用来表示一种公共资源或者说共享数据，可以被多个线程使用，但是每一次只能有一个线程使用它，一旦临界区资源被占用，其他线程要想使用这个资源就必须等待。
 *      4:阻塞（Blocking）和非阻塞（Non-Blocking）
 *      5:死锁（Deadlock）、饥饿（Starvation）和活锁（Livelock）
 *          死锁：
 *          饥饿：某一个或者多个线程因为种种原因无法获得所要的资源，导致一直无法执行（如线程优先级太低。。）
 *          活锁：线程互相“谦让”资源，导致一直阻塞
 */
public class DeadLockDemo {

}
