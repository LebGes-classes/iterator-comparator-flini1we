package com.example.bigboybusiness.example;

import java.util.NoSuchElementException;

public class DoublyLinkedList<E> implements List<E>, systemIF<E>{
    public Iterator iterator(){
        return new Iterator();
    }

    /*
    creating a nested class
     */
    private class Iterator implements java.util.Iterator<E> {
        private int index = 0;
        private int lastIndex = -1;

        /*
        checking if index of current
        is less than size of List
        */
        public boolean hasNext(){
            return index != size;
        }


        /*
        if Elem hasNext is false -> no element
        to return by next field

        Creating fiels Enext according to index
        incrementing index and move lastIndex
        to current position
        */
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            E next = get(index);
            lastIndex = index;
            index++;

            return next;
        }


        /*
        if index to remove is less than 0 -> !hasNext

        current index become lastIndex
         */
        public void remove(){
            if(lastIndex < 0){
                throw new IllegalStateException();
            }

            removeIndex(lastIndex);


            index = lastIndex;
            lastIndex = index - 1;
        }
    }



    Node<E> head;
    Node<E> last;
    int size = 0;

    DoublyLinkedList(){}

    DoublyLinkedList(E value){
        head = new Node<>(value);
        last = head;
        size++;
    }


    public void listToString(){
        if(head == null) {
            System.out.println("List is Empthy.");
            return ;
        }
        Node<E> _current = head;
        System.out.print("List: ");
        while (_current != null){
            System.out.print(_current.value + " ");
            _current = _current.next;
        }
        System.out.println();
    }

    public void add(E value){
        Node<E> newNode = new Node<>(value);

        if(head != null) {
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
            size++;
            return;
        }
        last = newNode;
        head = newNode;
        size++;

    }

    public void add(int pos, E val){
        Node<E> nextNode = new Node<>(val);
        if(pos == 0){
            if(head == null){
                head = nextNode;
                last = nextNode;
                size++;
                return;
            }
            size++;
            head.prev = nextNode;
            nextNode.next = head;
            head = nextNode;
            size++;
            return ;
        }

        int index = 0;
        Node<E> _current = head;
        while(index < pos && _current != null){
            if(index + 1 == pos){
                nextNode.next = _current.next;

                if(_current.next != null){
                    _current.next.prev = nextNode;
                }
                _current.next = nextNode;
                nextNode.prev = _current;
                if(nextNode.next == null){
                    last = nextNode;
                }
                size++;
                return;
            }index++; _current = _current.next;
        }

        if(_current == null) {
            add(val);

            size++;
        }
    }

    public void clear(){
        head = null;
        last = null;
        size = 0;
    }

    public void removeIndex(int index) {
        if(size < index){
            throw new IndexOutOfBoundsException("Index out of range");
        }
        if(index == 0){
            head = head.next;
            size--;
            return;
        }
        if(index == size - 1){
            last = last.prev;
            size--;
            return ;
        }
        int counter = 0;
        Node<E> _current = head;
        while(counter < index-1 && _current != null){
            counter++; _current = _current.next;
        }

        _current.next = _current.next.next;
        _current.next.prev = _current;
        size--;
    }

    public E findIndex(int index){
        if(size < index){
            throw new IndexOutOfBoundsException("Index out of range");
        }
        int counter = 0;
        Node<E> _current = head;
        while(counter < index && _current != null){
            counter++;
            _current = _current.next;
        }
        return _current.value;
    }

    public int findElement(E val){
        int counter = 0;
        Node<E> _current = head;
        while(_current != null){
            if(_current.value == val){
                return counter;
            }
            _current = _current.next;
            counter++;
        }
        return -1;
    }


    public E get(int index){
        Node<E> current = head;
        if(index >= size){
            throw new IndexOutOfBoundsException();
        }else{
            int pos = 0;
            while (pos < index){
                current = current.next;
                pos++;
            }
            return current.value;
        }
    }
}
