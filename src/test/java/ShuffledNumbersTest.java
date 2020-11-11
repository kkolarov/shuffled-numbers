import org.junit.Assert;
import org.junit.Test;

import java.util.BitSet;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ShuffledNumbersTest {

    @Test
    public void testWith1000000Elements() {
        int n = 10000000;
        List<Integer> shuffledElements = getShuffledElements(n, 54321);

        Assert.assertEquals(54321, getMissingElement(n, shuffledElements));
    }

    @Test
    public void testWith100Elements() {
        int n = 100;
        List<Integer> shuffledElements = getShuffledElements(n, 100);

        Assert.assertEquals(100, getMissingElement(n, shuffledElements));
    }

    @Test
    public void testWith100ElementsAndMissingNumberGreaterThan100() {
        int n = 100;
        List<Integer> shuffledElements = getShuffledElements(n, 101);

        Assert.assertEquals(-1, getMissingElement(n, shuffledElements));
    }

    @Test
    public void testWith100ElementsAndMissingNumberLowerThan100() {
        int n = 100;
        List<Integer> shuffledElements = getShuffledElements(n, -100);

        Assert.assertEquals(-1, getMissingElement(n, shuffledElements));
    }

    @Test
    public void testWith0Elements() {
        int n = 0;

        List<Integer> shuffledElements = getShuffledElements(n, 0);

        Assert.assertEquals(-1, getMissingElement(n, shuffledElements));
    }

    public List<Integer> getShuffledElements(int n, int excludedElement) {
        excludedElement = excludedElement -1;

        List<Integer> array = subsequentElements(n);

        if (!array.isEmpty()) {
            if (excludedElement >= 0 && excludedElement < n) {
                array.remove(excludedElement);

                Collections.shuffle(array);

                return array;
            }
        }

        return new LinkedList<Integer>();
    }

    public List<Integer> subsequentElements(int n) {
        List<Integer> array = new LinkedList<Integer>();

        for (int i = 0; i < n; ++i) {
            array.add(i);
        }

        return array;
    }


    public int getMissingElement(int n, List<Integer> array) {
        if (!array.isEmpty()) {
            BitSet bitset = new BitSet(n);

            for (int integer : array) {
                bitset.set(integer);
            }

            for (int i = 0; i < n; ++i) {
                if (!bitset.get(i)) {
                    return i + 1;
                }
            }
        }

        return -1;
    }
}
