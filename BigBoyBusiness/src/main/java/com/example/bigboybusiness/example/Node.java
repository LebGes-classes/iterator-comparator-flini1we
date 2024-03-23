package com.example.bigboybusiness.example;

public class Node<E> {
    E value;
    Node<E> next;
    Node<E> prev;

    Node(E value){
        this.value = value;
    }
}