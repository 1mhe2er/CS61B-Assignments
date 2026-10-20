import org.junit.jupiter.api.*;

import java.util.Comparator;
import deque.MaxArrayDeque61B;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class MaxArrayDeque61BTest {
    private static class StringLengthComparator implements Comparator<String> {
        public int compare(String a, String b) {
            return a.length() - b.length();
        }
    }

    @Test
    public void basicTest() {
        MaxArrayDeque61B<String> mad = new MaxArrayDeque61B<>(new StringLengthComparator());
        mad.addFirst("");
        mad.addFirst("2");
        mad.addFirst("fury road");
        assertThat(mad.max()).isEqualTo("fury road");
    }

    @Test
    public void multiTest() {
        MaxArrayDeque61B<String> lld1 = new MaxArrayDeque61B<>(new StringLengthComparator());
        MaxArrayDeque61B<String> lld2 = new MaxArrayDeque61B<>(new StringLengthComparator());
        MaxArrayDeque61B<String> lld3 = new MaxArrayDeque61B<>(new StringLengthComparator());

        lld1.addFirst("");
        lld1.addFirst("2");
        lld1.addFirst("fury road");
        lld2.addFirst("");
        lld2.addFirst("799");
        lld2.addFirst("325");
        lld2.addFirst("7999");

        assertThat(lld1.max()).isEqualTo("fury road");
        assertThat(lld2.max()).isEqualTo("7999");
        assertThat(lld3.max()).isNull();
    }
}
