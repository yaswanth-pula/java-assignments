import java.util.TreeSet;

public class DictionaryBST {
    private TreeSet<String> dictionary;

    // TODO: Implement the dictionary interface using a TreeSet.
    // You'll need a constructor here
    public DictionaryBST() {
        this.dictionary = new TreeSet<String>();
    }

    /**
     * Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     *
     * @param word The word to add
     * @return true if the word was added to the dictionary
     * (it wasn't already there).
     */
    public boolean addWord(String word) {
        // TODO: Implement this method]
        word = word.toLowerCase();
        if (dictionary.contains(word))
            return false;

        return dictionary.add(word);
    }


    /**
     * Return the number of words in the dictionary
     */
    public int size() {
        // TODO: Implement this method
        return dictionary.size();
    }

    /**
     * Is this a word according to this dictionary?
     */
    public boolean isWord(String word) {
        //TODO: Implement this method
        word = word.toLowerCase();
        return dictionary.contains(word);
    }
}