package com.xml.parser;

import java.util.Arrays;
import java.util.Collection;
import java.util.EmptyStackException;

/**
 * Created by Oleg on 19 Aug 2015.
 *
 */
public class Stack<E>{

    private E[] elements;

    private int size = 0;

    private int INITIAL_CAPACITY = 16;

    @SuppressWarnings("unchecked")
    public Stack() {
        elements = (E[]) new Object[INITIAL_CAPACITY];
    }

    public void push(E e) {
        enshureCapacity();
        elements[size++] = e;
    }

    public void pushAll(Iterable<? extends E> src) {
        for (E e : src) {
            push(e);
        }
    }

    public E pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        E result = elements[--size];
        elements[size] = null;
        return result;
    }

    public void popAll(Collection<? super E> dst) {
        while (!isEmpty()) {
            dst.add(pop());
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void enshureCapacity() {

        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }

}
