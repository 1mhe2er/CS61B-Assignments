import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

     @Test
     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
     void noNonTrivialFields() {
         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                 .toList();

         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
     }



        @Test
        public void testAddFirst() {
            Deque61B<Integer> lld1 = new ArrayDeque61B<>();

            lld1.addFirst(8);
            lld1.addFirst(7);
            lld1.addFirst(6);
            lld1.addFirst(5);
            lld1.addFirst(4);
            lld1.addFirst(3);
            lld1.addFirst(2);
            lld1.addFirst(1);
            lld1.addFirst(0);

            assertThat(lld1.get(0)).isEqualTo(0);
            assertThat(lld1.get(2)).isEqualTo(2);
            assertThat(lld1.get(8)).isEqualTo(8);
        }

        @Test
        public void testAddLast() {
            Deque61B<Integer> lld1 = new ArrayDeque61B<>();

            lld1.addLast(10);
            lld1.addLast(20);
            lld1.addLast(3);
            lld1.addLast(4);
            lld1.addLast(5);
            lld1.addLast(6);
            lld1.addLast(7);
            lld1.addLast(8);
            lld1.addLast(9);

            assertThat(lld1.get(0)).isEqualTo(10);
            assertThat(lld1.get(1)).isEqualTo(20);
            assertThat(lld1.get(2)).isEqualTo(3);
            assertThat(lld1.get(7)).isEqualTo(8);
            assertThat(lld1.get(8)).isEqualTo(9);
        }

        @Test
        public void testGet() {
            Deque61B<Integer> lld1 = new ArrayDeque61B<>();

            lld1.addFirst(10);
            lld1.addFirst(20);
            lld1.addFirst(30);

            assertThat(lld1.get(0)).isEqualTo(30);
            assertThat(lld1.get(1)).isEqualTo(20);
            assertThat(lld1.get(100)).isNull();
            assertThat(lld1.get(-1)).isNull();
        }

        @Test
        public void is_empty() {
            Deque61B<Integer> lld1 = new ArrayDeque61B<>();
            Deque61B<Integer> lld2 = new ArrayDeque61B<>();

            lld1.addFirst(10);

            assertThat(lld1.isEmpty()).isFalse();
            assertThat(lld2.isEmpty()).isTrue();
        }

        @Test
        public void testsize() {
            Deque61B<Integer> lld1 = new ArrayDeque61B<>();
            Deque61B<Integer> lld2 = new ArrayDeque61B<>();

            lld1.addFirst(10);
            lld1.addFirst(20);

            assertThat(lld1.size()).isEqualTo(2);
            assertThat(lld2.size()).isEqualTo(0);
        }

        @Test
        public void testToList() {
            Deque61B<Integer> lld1 = new ArrayDeque61B<>();
            Deque61B<Integer> lld2 = new ArrayDeque61B<>();

            lld1.addFirst(0);
            lld1.addLast(1);
            lld1.addFirst(-1);
            lld1.addLast(2);
            lld1.addFirst(-2);
            lld1.addLast(3);
            lld1.addFirst(-3);
            lld1.addLast(4);

            assertThat(lld1.toList()).containsExactly(-3, -2, -1, 0, 1, 2, 3 ,4).inOrder();
        }

        @Test
        public void testRemove() {
            Deque61B<Integer> lld1 = new ArrayDeque61B<>();
            Deque61B<Integer> lld2 = new ArrayDeque61B<>();

            for (int i = 0; i < 32; i++) {
                lld1.addLast(i);
                lld2.addLast(i);
            }

            for (int i = 0; i < 25; i++){
                lld1.removeLast();
                lld2.removeFirst();
            }

            assertThat(lld1.toList()).containsExactly(0, 1, 2, 3, 4, 5, 6).inOrder();
            assertThat(lld2.toList()).containsExactly(25, 26, 27, 28, 29, 30, 31).inOrder();
        }

    }

