package com.smart.demo.functioninterface;

import java.util.concurrent.Exchanger;

public class ExchangerDemo {
//    public static void main(String[] args) throws InterruptedException {
//        Exchanger<String> exchanger = new Exchanger<>();
//
//        new Thread(() -> {
//            try {
//                System.out.println("这是线程A，得到了另一个线程的数据："
//                        + exchanger.exchange("1"));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
//
//        System.out.println("这个时候线程A是阻塞的，在等待线程B的数据");
//        Thread.sleep(1000);
//
//        new Thread(() -> {
//            try {
//                System.out.println("这是线程B，得到了另一个线程的数据："
//                        + exchanger.exchange("2"));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
//
//        new Thread(() -> {
//            try {
//                System.out.println("这是线程c，得到了另一个线程的数据："
//                        + exchanger.exchange("3"));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
//        new Thread(() -> {
//            try {
//                System.out.println("这是线程d，得到了另一个线程的数据："
//                        + exchanger.exchange("4"));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
//    }
}
