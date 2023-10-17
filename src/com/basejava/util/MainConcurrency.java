package com.basejava.util;

import java.util.ArrayList;
import java.util.List;

public class MainConcurrency {
    private static int counter;
    private static final Object LOCK = new Object();
    private static final int THREADS_NUMBER = 10000;

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
            }
        };
        thread0.start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName());
//            }
//        }).start(); Другой способ запуска срэда через Runnable(). Ниже то же самое лямнда.

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState());
        }).start();
        System.out.println(thread0.getState());

//        for (int i = 0; i < 10000; i++) {
//            new Thread(() -> {
//                for (int j = 0; j < 100; j++) {
//                    inc();
//                }
//            }).start();
//        }
//        Thread.sleep(500);
//        System.out.println(counter);
//    }
//
//    private static synchronized void inc() {
//        double a = Math.sin(13.);
//        synchronized(LOCK) { //Можно ставить блок на обьектов в каждом потоке для общей очереди патоков,
//                             // удобно использовать для части метода.
//            counter++;
//        }
//    }

//        final MainConcurrency mainConcurrency = new MainConcurrency();
//        for (int i = 0; i < 10000; i++) {
//            new Thread(()-> {
//                for (int j = 0; j < 100; j++) {
//                    mainConcurrency.inc();
//                }
//            }).start();
//        }
//        Thread.sleep(500);
//        System.out.println(counter);
//    }
//
//    private void inc() {
////        synchronized(MainConcurrency.class) { // Можно встать в очередь к обьекту класса MainConcurrency другой способ
//        double a = Math.sin(13.);// для всего метода.
//        synchronized(this) { // обращение к обькту MainConcurrency
//            counter++;
//        }  //}
//    }

//        wait(), notify(), notifyAll()
//        final MainConcurrency mainConcurrency = new MainConcurrency();
//        for (int i = 0; i < 10000; i++) {
//            new Thread(()-> {
//                for (int j = 0; j < 100; j++) {
//                    mainConcurrency.inc();
//                }
//            }).start();
//        }
//        Thread.sleep(500);
//        System.out.println(counter);
//    }
//
//    private void inc() {
//        double a = Math.sin(13.);
//        try {
//            synchronized(this) {
//                counter++;
//                wait(); // Метод который останавливает текущий поток снимая блокировку и после него продолжаеться
////                readFile // выполнение другово потока.
//             //   notify(); // Метод который пробуждает предыдущий поток который был остановлен.
//             //   notifyAll();// Метод пробуждает все потоки по очереди. Все методы вызаваються внутри данного блока.
//            }
//        } catch(InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

//       join() вместо ожидания
        final MainConcurrency mainConcurrency = new MainConcurrency();
        List<Thread> threads = new ArrayList <>(THREADS_NUMBER);
        for (int i = 0; i < THREADS_NUMBER; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                }
            });
            thread.start();
            threads.add(thread);
        }
        threads.forEach(t -> {
            try {
                t.join();// вместо ожидания делаем данный меток к каждому патоку
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(counter);
    }

    private synchronized void inc() {
        counter++;
    }
}
