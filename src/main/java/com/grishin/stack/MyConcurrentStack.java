package com.grishin.stack;

public interface MyConcurrentStack<T> {

    void push(T t);

    T pop();

    T peek();

    boolean isEmpty();

}
