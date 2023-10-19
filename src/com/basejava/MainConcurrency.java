package com.basejava;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainConcurrency {
    private int counter;
    private final AtomicInteger atomicCounter = new AtomicInteger();
    protected static final Object LOCK = new Object();
    protected static final Object LOCK1 = new Object();
    private static final Lock LOCK2 = new ReentrantLock();
    private static final ThreadLocal <SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat();
        }
    };
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

//        new Thread(() -> {
//            System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState());
//        }).start();
//        System.out.println(thread0.getState());
//
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
//        final MainConcurrency mainConcurrency = new MainConcurrency();
//        List <Thread> threads = new ArrayList <>(THREADS_NUMBER);
//        for (int i = 0; i < THREADS_NUMBER; i++) {
//            Thread thread = new Thread(() -> {
//                for (int j = 0; j < 100; j++) {
//                    mainConcurrency.inc();
//                }
//            });
//            thread.start();
//            threads.add(thread);
//        }
//        threads.forEach(t -> {
//            try {
//                t.join();// вместо ожидания делаем данный меток к каждому патоку
//            } catch(InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//
//        DeadlockExample.runDeadlock();
//        System.out.println(mainConcurrency.counter);
//    }
//
//    private synchronized void inc() {
//        counter++;
//    }

//    использование пакета java.util.concurrent.*
//    CountDownLatch
//        final MainConcurrency mainConcurrency = new MainConcurrency();
//        CountDownLatch latch = new CountDownLatch(THREADS_NUMBER);
//        for (int i = 0; i < THREADS_NUMBER; i++) {
//            Thread thread = new Thread(() -> {
//                for (int j = 0; j < 100; j++) {
//                    mainConcurrency.inc();
//                }
//                latch.countDown();
//            });
//            thread.start();
//
//        }
//
//       // DeadlockExample.runDeadlock();
//        latch.await(10, TimeUnit.SECONDS);
//        System.out.println(mainConcurrency.counter);
//    }
//
//    private synchronized void inc() {
//        counter++;
//    }

//      Executors
//        final MainConcurrency mainConcurrency = new MainConcurrency();
//        CountDownLatch latch = new CountDownLatch(THREADS_NUMBER);
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        for (int i = 0; i < THREADS_NUMBER; i++) {
////            executorService.submit(() -> {// submit использует интерфейс Runnable()
//////            Thread thread = new Thread(() -> {
////                for (int j = 0; j < 100; j++) {
////                    mainConcurrency.inc();
////                }
////                latch.countDown();
////                return 5;// submit использует интерфейс Callable()
////            });
//            Future <Integer> future = executorService.submit(() -> {// submit Callable() возращает future
////            Thread thread = new Thread(() -> {
//                for (int j = 0; j < 100; j++) {
//                    mainConcurrency.inc();
//                }
//                latch.countDown();
//                return 5;
//            });
////            thread.start();
//
//        }
//
//        // DeadlockExample.runDeadlock();
//        latch.await(10, TimeUnit.SECONDS);
//        executorService.shutdown();
//        System.out.println(mainConcurrency.counter);
//    }
//
//    private synchronized void inc() {
//        counter++;
//    }

//      lock,atomicInteger
//        final MainConcurrency mainConcurrency = new MainConcurrency();
//        CountDownLatch latch = new CountDownLatch(THREADS_NUMBER);
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        for (int i = 0; i < THREADS_NUMBER; i++) {
//            Future <Integer> future = executorService.submit(() -> {// submit Callable() возращает future
////            Thread thread = new Thread(() -> {
//                for (int j = 0; j < 100; j++) {
//                    mainConcurrency.inc();
//                }
//                latch.countDown();
//                return 5;
//            });
////            thread.start();
//
//        }
//
//        // DeadlockExample.runDeadlock();
//        latch.await(10, TimeUnit.SECONDS);
//        executorService.shutdown();
////        System.out.println(mainConcurrency.counter);
//        System.out.println(mainConcurrency.atomicCounter.get());
//    }
//
//    private void inc() {
////        lock.lock();
////        try {
//        atomicCounter.incrementAndGet();
////            counter++;
////        } finally {
////            lock.unlock();
////        }
//    }

//      SimpleDateFormat
        final MainConcurrency mainConcurrency = new MainConcurrency();
        CountDownLatch latch = new CountDownLatch(THREADS_NUMBER);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < THREADS_NUMBER; i++) {
            Future <Integer> future = executorService.submit(() -> {// submit Callable() возращает future
//            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                    System.out.println(threadLocal.get().format(new Date()));
                }
                latch.countDown();
                return 5;
            });
//            thread.start();

        }

        // DeadlockExample.runDeadlock();
        latch.await(10, TimeUnit.SECONDS);
        executorService.shutdown();
//        System.out.println(mainConcurrency.counter);
        System.out.println(mainConcurrency.atomicCounter.get());
    }

    private void inc() {
//        lock.lock();
//        try {
        atomicCounter.incrementAndGet();
//            counter++;
//        } finally {
//            lock.unlock();
//        }

    }
}

class DeadlockExample {
    public static void runDeadlock() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            performTask(MainConcurrency.LOCK, MainConcurrency.LOCK1);
        });

        Thread thread2 = new Thread(() -> {
            performTask(MainConcurrency.LOCK1, MainConcurrency.LOCK);
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }

    private static void performTask(Object lock1, Object lock2) {
        synchronized(lock1) {
            System.out.println("Thread: Holding lock1...");
            try {
                Thread.sleep(100);
            } catch(InterruptedException e) {
            }
            System.out.println("Thread: Waiting for lock2...");
            synchronized(lock2) {
                System.out.println("Thread: Holding lock1 and lock2...");
            }
        }
    }
}


