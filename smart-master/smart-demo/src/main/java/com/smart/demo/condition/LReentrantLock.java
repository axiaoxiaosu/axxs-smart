package com.smart.demo.condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class LReentrantLock implements Runnable {
    static ReentrantLock lock1 = new ReentrantLock();
    static ReentrantLock lock2 = new ReentrantLock();

    //这样设置的锁 是公平锁 公平锁会维护一个有序队列,线程执行循序会有序执行,缺点:效率不够,优点:避免多线程下资源抢占出现饥饿资源 公平锁也叫做重入锁
    static ReentrantLock lock3 = new ReentrantLock(true);


    int state;

    public LReentrantLock(int state) {
        this.state = state;
    }

    /**
     * 模拟死锁
     */
    @Override
    public void run() {
        try {
            if (state == 1) {
                lock1.lockInterruptibly();
                TimeUnit.SECONDS.sleep(1);
                lock2.lockInterruptibly();
            } else if (state == 3) {
                while (true) {
                    lock3.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName() + "获取锁");
                    lock3.unlock();
                }
            } else {
                lock2.lockInterruptibly();
                TimeUnit.SECONDS.sleep(1);
                lock1.lockInterruptibly();
            }
            System.out.println(Thread.currentThread().getName() + "获取锁");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock1.isHeldByCurrentThread()) {
                lock1.unlock();
            }
            if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
            }
            if (lock1.isHeldByCurrentThread() && lock2.isHeldByCurrentThread())
                System.out.println("所有线程锁已释放");
        }

    }

    public static void main1(String[] args) throws InterruptedException {
        Thread thread = new Thread(new LReentrantLock(1), "lock1线程");
        Thread thread2 = new Thread(new LReentrantLock(1), "lock2线程");
        thread.start();
        thread2.start();
        TimeUnit.SECONDS.sleep(2);
        //中断线程
        // thread2.interrupt();
    }

//    public static void main(String[] args) {
//        Thread thread = new Thread(new LReentrantLock(3), "线程1");
//        Thread thread2 = new Thread(new LReentrantLock(3), "线程2");
//        thread.start();
//        thread2.start();
//    }

}
