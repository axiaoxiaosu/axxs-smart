package com.smart.demo.functioninterface;

import java.util.concurrent.ArrayBlockingQueue;

public class TestEat {
    private static final Object lock = new Object();
    private static final ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10);


//    public static void main(String[] args) {
//
//        System.out.println("核心数" + Runtime.getRuntime().availableProcessors());

//        new Thread(() -> {
//            try {
//                int i = 0;
//                for (; ; ) {
//                    String msg = "第" + (++i) + "个";
//                    queue.put(msg);
//                    System.out.println("放进去一个东西队列剩余"+queue.size()+"个元素");
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        }).start();
//        new Thread(() -> {
//            try {
//                while (true) {
//                    String msg = (String) queue.take();
//                 //   System.out.println("取出来" + msg);
//                    System.out.println("从队列取走一个元素，队列剩余"+queue.size()+"个元素");
////                    System.out.println("取出来" + msg);
////                    System.out.println(queue.size());
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();


        //比较执行时间大于超时时间会有什么情况
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(0, 5, 2, TimeUnit.SECONDS, new SynchronousQueue<>());
//        executor.execute(() -> {
//            try {
//                Long oldTime = System.currentTimeMillis();
//                Thread.sleep(3000l);
//                System.out.println((System.currentTimeMillis() - oldTime) / 1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });


//        ThreadGroup group = new ThreadGroup("group1") {
//            @Override
//            public void uncaughtException(Thread t, Throwable e) {
//                System.out.println(e.toString());
//                System.out.println("出现错误");
//            }
//        };
//
//        Thread threada = new Thread(group, () -> {
//
//            synchronized (lock) {
//                System.out.println(Thread.currentThread().getName() + "你好啊");
//                try {
//                    lock.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(Thread.currentThread().getName() + "还不错");
//            }
//        });
//
//        Thread threadb = new Thread(group, () -> {
//            synchronized (lock) {
//                System.out.println(Thread.currentThread().getName() + "你好啊");
//                lock.notify();
//            }
//        });
//
//
//        threada.start();
//
//        // threada.join();
//        // Thread.sleep(1000L);
//        threadb.start();
//        // threadb.notifyAll();
//        System.out.println(threada.getState());
//        System.out.println(threadb.getState());
//        for (;;) {
//            System.out.println("你好啊");
//        }
//    }


}
