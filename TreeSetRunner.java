import java.util.Arrays;
import java.util.TreeSet;

public class TreeSetRunner {
    private static void addInputs(TreeSet<Integer> treeSet){
        Integer[] inputs1 = {100,20,43,23};
        treeSet.addAll(Arrays.asList(inputs1));
    }
    public static void main(String[] args) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        addInputs(treeSet);
        System.out.println("Initial set :"+treeSet);

        treeSet.add(21);
        System.out.println("After Insert 21: "+treeSet);

        System.out.println("Does Set consist 43 ?: "+treeSet.contains(43));
        System.out.println("Does Set consist 111 ?: "+treeSet.contains(111));

        treeSet.remove(43);
        System.out.println("After Removal 43 : "+treeSet);

        System.out.println("Get first element just higher than 21: "+treeSet.higher(21));
        System.out.println("Get first element just lower than 100: "+treeSet.lower(100));

        System.out.println("Head set of 23: "+treeSet.headSet(23));
        System.out.println("Tail set of 21: "+treeSet.tailSet(21));


    }
}
