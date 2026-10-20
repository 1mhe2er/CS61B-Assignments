package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    private Node sentinel;
    private int size;

    public LinkedListDeque61B() {
        sentinel = new Node(null, null, null);
        sentinel.previous = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    private class Node {
        public Node previous;
        public T item;
        public Node next;

        public Node (T x, Node pre, Node nex) {
            previous = pre;
            next = nex;
            item = x;
        }
    }

    @Override
    public void addFirst(T x) {
        Node temp = new Node(x, sentinel, sentinel.next);
        size += 1;
        sentinel.next.previous = temp;
        sentinel.next = temp;
    }

    @Override
    public void addLast(T x) {
        Node temp = new Node(x, sentinel.previous, sentinel);
        size += 1;
        sentinel.previous.next = temp;
        sentinel.previous = temp;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node pointer = new Node(null, null, sentinel.next);

        while (pointer.next != sentinel) {
            returnList.add(pointer.next.item);
            pointer = pointer.next;
        }

        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if (sentinel.next == sentinel) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        size -= 1;
        Node remove = sentinel.next;

        sentinel.next = remove.next;
        sentinel.next.previous = remove.previous;

        return remove.item;
    }

    @Override
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        size -= 1;
        Node remove = sentinel.previous;

        sentinel.previous = remove.previous;
        sentinel.previous.next = remove.next;

        return remove.item;
    }

    @Override
    public T get(int index) {
        Node pointer = new Node(null, null, sentinel.next);

        if (index >= this.size || index < 0) {
            return null;
        }

        for (int i = 0; i < index; i++) {
            pointer = pointer.next;
        }
        return pointer.next.item;
    }


    @Override
    public T getRecursive(int index) {

        return getRecursivehelper(sentinel.next, index);
    }

    public T getRecursivehelper(Node p, int index) {
        if (index >= this.size || index < 0) {
            return null;
        }

        if (index == 0) {
            return p.item;
        }else {
            return getRecursivehelper(p.next, index - 1);
        }
    }

    //implement iterator
    public Iterator<T> iterator() {
        return new LinkedDequeIterator();
    }

    private class LinkedDequeIterator<T> implements Iterator<T> {
        private int index;

        public LinkedDequeIterator() {
            index = 0;
        }

        public boolean hasNext() {
            return index < size;
        }

        public T next() {
            T returnitem = (T) LinkedListDeque61B.this.get(index);
            index++;
            return returnitem;
        }
    }

    //Override equals() method
    @Override
    public boolean equals(Object other) {
        if (this == other) { return true; }

        if (other instanceof LinkedListDeque61B<?> otherdeque) {
            if (otherdeque.size != this.size) {
                return false;
            }

            for (int index = 0; index < size; index++) {
                if ( this.get(index) != ((LinkedListDeque61B<?>) other).get(index)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return toList().toString();
    }
}
