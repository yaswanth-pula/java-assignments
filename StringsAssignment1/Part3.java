package StringsAssignment1;

public class Part3 {
    public static boolean twoOccurrences(String stringA,String stringB){
            int firstOccurrenceIndex = stringB.indexOf(stringA);
            if(firstOccurrenceIndex == -1) return false;

            int secondOccurrence = stringB.indexOf(stringA,firstOccurrenceIndex+1);
            if(secondOccurrence == -1) return false;

            return true;
    }

    public static String lastPart(String stringA,String stringB){
        int firstOccurrenceIndex = stringB.indexOf(stringA);

        if(firstOccurrenceIndex == -1 ) return stringB;

        return stringB.substring(firstOccurrenceIndex+stringA.length());
    }


    public static void testing(){
        System.out.println(twoOccurrences("by", "A story by Abby Long"));
        System.out.println(twoOccurrences("a", "banana"));
        System.out.println(twoOccurrences("atg", "ctgtatgta"));

        System.out.println(lastPart("an", "banana"));
        System.out.println(lastPart("zoo","forest"));
    }



    public static void main(String[] args) {
        testing();
    }
}
