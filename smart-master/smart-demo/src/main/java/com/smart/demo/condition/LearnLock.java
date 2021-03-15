package com.smart.demo.condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LearnLock implements Runnable {
    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();


    @Override
    public void run() {
        try {
            lock.lockInterruptibly();
            System.out.println("获取了锁");
            condition.await();
            System.out.println("释放锁,并进入等待");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //  lock.unlock();
            if (lock.isHeldByCurrentThread())
                System.out.println(Thread.currentThread().getName() + "释放锁,并且执行结束");
        }
    }

//    public static void main(String[] args) {
//        Thread thread = new Thread(new LearnLock(), "hello");
//        thread.start();
//        try {
//            TimeUnit.SECONDS.sleep(3);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        //lock.newCondition 这样声明锁唤醒时必须有锁的存在
//        lock.lock();
//        condition.signal();
//        lock.unlock();
//    }
}
