public class Palindrome {
    /** Given a String, it should return a Deque where
     *  the characters appear in the same order as in the String.
     */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> returnedItem = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            returnedItem.addLast(word.charAt(i));
        }
        return returnedItem;
    }

    /*
    public boolean isPalindrome(String word) {
        if (word == null) {
            return true;
        }
        Deque<Character> forwardDeque = wordToDeque(word);
        String backward = "";
        int size = forwardDeque.size();
        for (int i = 0; i < size; i++) {
            backward += forwardDeque.removeLast();
        }
        return backward.equals(word);
    }
    */
    /** recursion version */
    public boolean isPalindrome(String word) {
        if (word == null || word.length() <= 1) {
            return true;
        } else {
            if (word.charAt(0) == word.charAt(word.length() - 1)) {
                return isPalindrome(word.substring(1, word.length() - 1));  // [ ) interval
            }
            return false;
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == null || word.length() <= 1) {
            return true;
        } else {
            if (cc.equalChars(word.charAt(0), word.charAt(word.length() - 1))) {
                return isPalindrome(word.substring(1, word.length() - 1), cc);  // [ ) interval
            }
            return false;
        }
    }
}
