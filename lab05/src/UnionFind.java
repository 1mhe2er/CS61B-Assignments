import java.util.ArrayList;
import java.util.Arrays;

public class UnionFind {
    // TODO: Instance variables
    private int[] unionarray;
    private int size;
    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        // TODO: YOUR CODE HERE
        unionarray = new int[N];
        size = N;
        Arrays.fill(unionarray, -1);
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        // TODO: YOUR CODE HERE
        v = find(v);
        return -unionarray[v];
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        // TODO: YOUR CODE HERE
        return unionarray[v];
    }

    /* Returns true if nodes/vertices V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO: YOUR CODE HERE
        return find(v1) == find(v2);
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        // TODO: YOUR CODE HERE
        int rootofv = v;
        int parentofv = parent(v);

        if (v > size - 1) {
            throw new IllegalArgumentException("Cannot find an out of range vertex!");
        }
        while (parent(rootofv) > 0) {
            rootofv = parent(rootofv);
        }

        while (parent(v) > 0) {
            parentofv = parent(v);
            unionarray[v] = rootofv;
            v = parentofv;
        }
        return rootofv;
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing an item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        // TODO: YOUR CODE HERE
        if (v1 == v2) {
            return;
        }
        if (find(v1) == find(v2)) {
            return;
        }

        int sizeofv1 = sizeOf(v1);
        int sizeofv2 = sizeOf(v2);
        int parentofv1 = find(v1);
        int parentofv2 = find(v2);

        if (sizeofv1 < sizeofv2 || sizeofv1 == sizeofv2) {
            unionarray[parentofv1] = parentofv2;
            unionarray[parentofv2] = - sizeofv1 - sizeofv2;
        }else {
            unionarray[parentofv2] = parentofv1;
            unionarray[parentofv1] = - sizeofv1 - sizeofv2;
        }
    }

}
