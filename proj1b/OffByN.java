public class OffByN implements CharacterComparator {
    private int offset;
    public OffByN(int N) {
        offset = N;
    }

    /**
     * Returns true if characters are equal by the rules of the implementing class.
     *
     * @param x
     * @param y
     */
    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == offset;
    }
}
