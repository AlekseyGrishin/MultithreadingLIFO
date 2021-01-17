package com.grishin.stack.impl;

import com.grishin.stack.MyConcurrentStack;

import java.util.concurrent.atomic.AtomicReference;


//1.	Реализовать стэк, "последний вошёл - первый вышел" (LIFO), с возможностью работы в многопоточной среде,
// необходимо использовать алгоритм, не блокирующий весь стэк.
//2.	Используя свою реализацию стэка, реализовать шаблон Consumer-Producer, с топологией 3 продюсера и 1 консьюмер.


public class MyConcurrentStackImpl<T> implements MyConcurrentStack<T> {

    private final AtomicReference<Node<T>> head = new AtomicReference<>();
    public MyConcurrentStackImpl() {}

    @Override
    public void push(T element) {
        Node<T> newTop = new Node<>(element, null);
        while (true) {
            Node<T> oldTop = head.get();
            newTop.next = oldTop;
            if (head.compareAndSet(oldTop, newTop)) {
                break;
            }
        }
    }

    @Override
    public T pop() {
        return head.get().data;
    }

    @Override
    public T peek() {
        while (true) {
            Node<T> oldTop = this.head.get();
            Node<T> newTop = oldTop.next;
            if (head.compareAndSet(oldTop, newTop)) {
                return oldTop.data;
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return head.get() == null;
    }

    private static class Node<E> {

        private final E data;
        private Node<E> next;

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }
}
