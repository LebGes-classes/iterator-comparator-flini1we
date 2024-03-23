package com.example.bigboybusiness.example;




import java.util.Comparator;
import java.util.NoSuchElementException;

class ListNode<E> implements List<E>, systemIF<E>{

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
            return index != sizeList;
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




    public NodeComparator NodeComparator(){
        return new NodeComparator();
    }
    private class NodeComparator implements java.util.Comparator<Node<E>> {
        @Override
        public int compare(Node<E> o1, Node<E> o2) {
            return ((Comparable<E>)o1.value).compareTo(o2.value);
        }
        //private Comparator<Node<E>> nodeComporator = new NodeComparator();
    }




    Node<E> head;
    int sizeList = 0;

    ListNode(){}

    ListNode(E value){
        head = new Node<>(value);
        sizeList++;
    }

    public void listToString() throws NullPointerException{

        Node<E> _current = head;
        System.out.print("List: ");
        while (_current != null){
            System.out.print(_current.value + " ");
            _current = _current.next;
        }
        System.out.println();

    }

    public E get(int index){
        Node<E> current = head;
        if(index >= sizeList){
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

    public void add(E value){
        if(head == null){
            head = new Node<>(value);
            sizeList++;
            return ;
        }
        Node _current = head;
        while (_current.next != null){
            _current = _current.next;
        }

        _current.next = new Node<>(value);
        sizeList++;
    }


    public void add(int pos, E val) throws NullPointerException {
        Node<E> newNext = new Node<E>(val);
        if(pos == 0){
            newNext.next = head;
            head = newNext;
            sizeList++;
            return;
        }
        int counter = 0;
        Node<E> _current = head;

        while (counter < pos - 1 && _current != null) {
            _current = _current.next ; counter++;
        }


        newNext.next = _current.next;
        _current.next = newNext;
        sizeList++;

    }


    public void clear(){
        head = null;
        sizeList = 0;
    }

    public void removeIndex(int index){
        if(sizeList < index){
            throw new IndexOutOfBoundsException("Index out of range");
        }

        if(index == 0){
            head = head.next;
            sizeList--;
            return;
        }
        int counter = 0;
        Node<E> _current = head;
        while (counter < index -1 && _current != null){
            counter++;
            _current = _current.next;
        }



        _current.next = _current.next.next;
        sizeList--;
    }


    public E findIndex(int index) {
        if(sizeList <= index){
            throw new IndexOutOfBoundsException("Index out of range");
        }
        int counter =  0;
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





}
