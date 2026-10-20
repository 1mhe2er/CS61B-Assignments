package deque;

import java.util.Comparator;

public class MaxArrayDeque61B<T> extends ArrayDeque61B<T>{
    private Comparator<T> comparator;

    public MaxArrayDeque61B(Comparator<T> c) {
        comparator = c;
    }

    public T max() {
        if (this.isEmpty()) {
            return null;
        }

        T maxitem = get(0);
        for (int index = 1; index < size(); index++) {
            if (comparator.compare(maxitem, get(index)) < 0) {
                maxitem = get(index);
            }
        }

        return maxitem;
    }

    public T max(Comparator<T> c) {
        if (this.isEmpty()) {
            return null;
        }

        T maxitem = get(0);
        for (int index = 1; index < size(); index++) {
            if (c.compare(maxitem, get(index)) < 0) {
                maxitem = get(index);
            }
        }

        return maxitem;
    }
}
