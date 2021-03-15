package com.smart.demo.condition;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LearnSemaphore implements Runnable {

    //5份许可证书  这个可以用来跑目标啊
    static Semaphore semaphore = new Semaphore(2);
    static int count = 1;
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock(false);

    @Override
    public void run() {
        try {
            //写入锁
            lock.writeLock().lock();
            //获取许可
            semaphore.acquire();
            ++count;
            lock.writeLock().unlock();
            System.out.println(Thread.currentThread().getName() + "进入澡堂");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isWriteLockedByCurrentThread())
                lock.writeLock().unlock();
            //用后还回去
            semaphore.release();
            System.out.println(Thread.currentThread().getName() + "洗好滚蛋了");
            System.out.println("总共洗了" + count + "人");
        }
    }
//    public static void main(String[] args) {
//        ThreadPoolExecutor executor =
//                new ThreadPoolExecutor(4, 4, 0, TimeUnit.SECONDS,
//                        new LinkedBlockingDeque<>(10));
//        //线程池饱和策略 当线程池执行的任务饱和时会将任务返回给调用主线程(这样多余的任务就没有多线程处理了)
//        //executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
//        LearnSemaphore semaphore = new LearnSemaphore();
//        for (int i = 1; i <= 4; i++) {
//            executor.execute(semaphore);
//        }
//        executor.shutdown();
//    }


}
