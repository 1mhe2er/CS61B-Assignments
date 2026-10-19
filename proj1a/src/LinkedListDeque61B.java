import java.util.List;
import java.util.ArrayList; // import the ArrayList class

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
}
