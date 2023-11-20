/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
        int max = 0;
        for (String s : asciis) {
            if (s.length() > max)
                max = s.length();
        }

        String[] sorted = asciis.clone();
        for (int idx = max; idx >= 0; idx--) {
            sortHelperLSD(sorted, idx);
        }

        return sorted;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        final int R = 256;

        int[] counts = new int[R+1];
        for (String s : asciis) {
            int ch = charAtLSD(s, index);
            counts[ch] += 1;
        }

        int[] starts = new int[R+1];
        int pos = 0;
        for (int i = 0; i < counts.length; i++) {
            starts[i] = pos;
            pos += counts[i];
        }

        String[] sorted = new String[asciis.length];
        for (int i = 0; i < asciis.length; i++) {
            String item = asciis[i];
            int ch = charAtLSD(item, index);
            int place = starts[ch];
            sorted[place] = item;
            starts[ch]++;
        }

        for (int i = 0; i < asciis.length; i++) {
            asciis[i] = sorted[i];
        }
    }

    private static int charAtLSD(String s, int index) {
        if (s.length() <= index) {
            return 0;
        }
        return s.charAt(index) + 1;
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }

    public static void main(String[] args) {
        String[] asciis = new String[] { "56", "112", "94", "4", "9", "82", "394", "80" };
        String[] res = RadixSort.sort(asciis);
        for (String s : res) {
            System.out.print(s + " ");
        }

        System.out.println();

        String[] asciis2 = new String[] {"  ", "      ", "    ", " "};
        String[] res2 = RadixSort.sort(asciis2);
        for (String s : res2) {
            System.out.print(s + ",");
        }

        System.out.println();

        String[] asciis3 = new String[] {"ñ", "MGTn²"};
        String[] res3 = RadixSort.sort(asciis3);
        for (String s : res3) {
            System.out.print(s + ",");
        }
    }
}

