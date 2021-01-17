package com.grishin.exchange;

import com.grishin.stack.impl.MyConcurrentStackImpl;

import java.util.Random;

public class Consumer implements Runnable {

    private final MyConcurrentStackImpl<Integer> queue;
    private volatile boolean ready = false;

    public Consumer(MyConcurrentStackImpl<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Random rand = new Random();

        while (!(ready && (!queue.isEmpty()))) {
            try {
                Thread.sleep(rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            queue.peek();
        }
        System.out.println("Поток заканчивает работу" + Thread.currentThread().getName());
    }

    public void shutdown() {
        ready = true;

    }
}
