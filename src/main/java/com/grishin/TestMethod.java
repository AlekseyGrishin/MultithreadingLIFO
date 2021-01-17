package com.grishin;

import com.grishin.exchange.Consumer;
import com.grishin.exchange.Producer;
import com.grishin.stack.impl.MyConcurrentStackImpl;

public class TestMethod {

    static volatile MyConcurrentStackImpl<Integer> myConcurrentStack = new MyConcurrentStackImpl<>();

    public static void main(String[] args) throws InterruptedException {

        Producer producer = new Producer(myConcurrentStack);
        Consumer consumer = new Consumer(myConcurrentStack);

        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(producer);
        Thread t3 = new Thread(producer);
        Thread t4 = new Thread(consumer);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        Thread.sleep(5000);

        producer.shutdown();

        t1.join();
        t2.join();
        t3.join();

        consumer.shutdown();

        t4.join();

        System.out.println("Finish!");

    }
}
