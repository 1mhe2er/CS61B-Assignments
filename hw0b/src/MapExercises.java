import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapExercises {
    /** Returns a map from every lower case letter to the number corresponding to that letter, where 'a' is
     * 1, 'b' is 2, 'c' is 3, ..., 'z' is 26.
     */
    public static Map<Character, Integer> letterToNum() {
        // TODO: Fill in this function.
        char c = 'a';
        Map<Character, Integer> letter = new HashMap<>();

        for (int i = 1; i <= 26; i++) {
            letter.put(c, i);
            c++;
        }
        return letter;
    }

    /** Returns a map from the integers in the list to their squares. For example, if the input list
     *  is [1, 3, 6, 7], the returned map goes from 1 to 1, 3 to 9, 6 to 36, and 7 to 49.
     */
    public static Map<Integer, Integer> squares(List<Integer> nums) {
        // TODO: Fill in this function.
        Map<Integer, Integer> squaring = new HashMap<>();

        for (int i: nums) {
            squaring.put(i, i * i);
        }
        return squaring;
    }

    /** Returns a map of the counts of all words that appear in a list of words. */
    public static Map<String, Integer> countWords(List<String> words) {
        // TODO: Fill in this function.
        Map<String, Integer> count = new HashMap<>();

        for (String word: words) {
            if (count.containsKey(word)) {
                count.put(word, count.get(word) + 1);
            }else {
                count.put(word, 1);
            }
        }
        return count;
    }
}
