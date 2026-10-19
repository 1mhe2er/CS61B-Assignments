import net.sf.saxon.expr.Component;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private int dequesize;
    private T[] item;
    private int nextFirst;
    private int nextLast;
    private int arraysize;

    private int addindex(int x) {
        return Math.floorMod(x + 1, arraysize);
    }

    private int subtractindex(int x) {
        return Math.floorMod(x - 1, arraysize);
    }

    private void resizingup(int capacity) {
        T[] a = (T[]) new Object[capacity];

        for (int i = 0; i < dequesize; i++) {
            a[i] = this.get(i);
        }
        arraysize = a.length;

        nextFirst = subtractindex(0);
        nextLast = addindex(dequesize - 1);
        item = a;
    }

    public ArrayDeque61B() {
        item = (T[]) new Object[8];
        dequesize = 0;
        arraysize = item.length;
        nextFirst = 3;
        nextLast = 4;
    }

    @Override
    public void addFirst(T x) {
        if (arraysize == dequesize) {
            this.resizingup(2 * arraysize);
        }
        dequesize++;
        item[nextFirst] = x;
        nextFirst = subtractindex(nextFirst);
    }

    @Override
    public void addLast(T x) {
        if (arraysize == dequesize) {
            this.resizingup(2 * arraysize);
        }
        dequesize++;
        item[nextLast] = x;
        nextLast = addindex(nextLast);
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();

        for (int i = 0; i < dequesize; i++) {
            returnList.add(this.get(i));
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if (dequesize == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return dequesize;
    }

    @Override
    public T removeFirst() {
        T remove = this.get(0);
        item[addindex(nextFirst)] = null;
        dequesize--;
        nextFirst = addindex(nextFirst);

        if (arraysize > 15 && arraysize > 4 * dequesize) {
            resizingup(arraysize / 4);
        }
        return remove;
    }

    @Override
    public T removeLast() {
        T remove = this.get(arraysize - 1);
        item[subtractindex(nextLast)] = null;
        dequesize--;
        nextLast = subtractindex(nextLast);

        if (arraysize > 15 && arraysize > 4 * dequesize) {
            resizingup(arraysize / 4);
        }
        return remove;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > dequesize - 1) {
            return null;
        }

        int returnindex = Math.floorMod(nextFirst + index + 1, arraysize);
        return item[returnindex];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

}
