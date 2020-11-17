package series.day2;

/**
 * 并发级别
 *      阻塞、无饥饿、无障碍、无锁、无等待
 *
 *    阻塞:
 *      一个线程是阻塞的，那么在其他线程释放资源之前，当前线程无法继续执行。
 *    无饥饿(Starvation-Free):
 *      如果线程之间是有优先级的，使用非公平锁高优先级的线程会插队执行，导致底优先级的饥饿。使用公平锁不允许插队，无饥饿。
 *    无障碍(Obstruction-Free):
 *      无障碍是一种最弱的非阻塞调度。两个线程如果无障碍地执行，那么不会因为临界区的问题导致一方被挂起。多个线程可同时操作同一个资源，如果发生冲突
 *      立刻回滚操作，可能导致所有线程都不成功，线程无法走出临界区，可以使用乐观锁解决
 *    无锁(Lock-Free):(?)
 *      无锁的并行都是无障碍的。在无锁的情况下，所有的线程都能尝试对临界区进行访问，但不同的是，无锁的并发保证必然有一个线程能够在有限步内完成操作离开临界区。
 *    无等待:(?)
 *      无锁只要求有一个线程可以在有限步内完成操作，而无等待则在无锁的基础上更进一步扩展。它要求所有线程都必须在有限步内完成，这样不会引起饥饿问题。
 *
 */
public class DemoEG {
}
