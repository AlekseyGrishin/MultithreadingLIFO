package com.grishin.exchange;

import com.grishin.stack.impl.MyConcurrentStackImpl;

import java.util.Random;

public class Producer implements Runnable {

    private final MyConcurrentStackImpl<Integer> queue;
    private volatile boolean ready = false;

    public Producer(MyConcurrentStackImpl<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (!ready) {
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            queue.push(random.nextInt(100));
            System.out.println("Значение: " + queue.peek());
        }
        System.out.println("Поток заканчивает работу" + Thread.currentThread().getName());
    }

    public void shutdown() {
        ready = true;
    }
}
