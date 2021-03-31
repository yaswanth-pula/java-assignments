import java.util.*;

class TrieDictionary {
    private TrieNode root;
    private int size;

    public TrieDictionary(){
        root = new TrieNode();
    }
    public boolean addWord(String word) {
        word = word.toLowerCase();

        TrieNode currentNode = root;
        for (Character currentCharacter : word.toCharArray()) {
            if (currentNode.getValidNextCharacters().contains(currentCharacter))
                currentNode = currentNode.getChild(currentCharacter);
            else
                currentNode = currentNode.insert(currentCharacter);
        }

        if (!currentNode.endsWord()) {
            currentNode.setEndsWord(true);
            this.size++;
            return true;
        }

        return false;
    }

    public int size() {
        return this.size;
    }

    public boolean contains(String word) {

        word = word.toLowerCase();
        TrieNode currentNode = root;

        for(Character currentCharacter : word.toCharArray()){
            if(currentNode.getValidNextCharacters().contains(currentCharacter))
                currentNode = currentNode.getChild(currentCharacter);
            else
                return false;
        }

        return currentNode.endsWord();
    }

    public List<String> findRelatedWords(String prefix, int numberOfWords) {
        List<String> predictionsList = new LinkedList<>();

        TrieNode currentNode = root;
        for(Character character: prefix.toCharArray()){
            if(currentNode.getValidNextCharacters().contains(character))
                currentNode = currentNode.getChild(character);
            else
                return predictionsList;
        }

        Deque<TrieNode> stemNodeQueue = new LinkedList<>();

        Set<Character> childNodeSet = new HashSet<>(currentNode.getValidNextCharacters());
        for(Character character : childNodeSet){
            stemNodeQueue.addLast(currentNode.getChild(character));
        }

        while(!stemNodeQueue.isEmpty() && predictionsList.size() < numberOfWords){
            TrieNode frontNode = stemNodeQueue.removeFirst();

            if(frontNode.endsWord())
                predictionsList.add(frontNode.getText());

            Set<Character> frontNodeChildren = new HashSet<>(frontNode.getValidNextCharacters());
            for(Character character : frontNodeChildren){
                stemNodeQueue.add(frontNode.getChild(character));
            }
        }

        return predictionsList;
    }

    public void printTire(){
        printAllTrieNodes(root);
    }
    private void printAllTrieNodes(TrieNode currentNode) {
        if (currentNode == null)
            return;
        if(currentNode.endsWord())
            System.out.println(currentNode);

        TrieNode next;
        for (Character character : currentNode.getValidNextCharacters()) {
            next = currentNode.getChild(character);
            printAllTrieNodes(next);
        }
    }
}
public class TrieRunner{
    private static void addInputWords(TrieDictionary dictionary){
        dictionary.addWord("a");
        dictionary.addWord("an");
        dictionary.addWord("ant");
        dictionary.addWord("anti");
        dictionary.addWord("animal");
        dictionary.addWord("b");
        dictionary.addWord("by");
        dictionary.addWord("bye");
    }
    public static void main(String[] args) {
        TrieDictionary dictionary = new TrieDictionary();
        addInputWords(dictionary);
        dictionary.printTire();
        System.out.println("-------Dictionary Contains bye and animals-----------");
        System.out.println(dictionary.contains("bye"));
        System.out.println(dictionary.contains("animals"));
        System.out.println("------Size of Dictionary----------------");
        System.out.println(dictionary.size());
        System.out.println("--------Related Words Of a -------");
        for(String relatedWord : dictionary.findRelatedWords("a",5)){
            System.out.println(relatedWord);
        }

    }
}
