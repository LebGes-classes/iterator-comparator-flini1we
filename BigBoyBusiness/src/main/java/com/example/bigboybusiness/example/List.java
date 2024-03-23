package com.example.bigboybusiness.example;

public interface List<E> {
    void add(E value);
    void add(int position, E value);
    void clear();
    void removeIndex(int index);
    E findIndex(int index);
    int findElement(E value);
    E get(int index);
}