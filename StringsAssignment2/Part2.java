package StringsAssignment2;

public class Part2 {
    public static int howMany(String stringA, String stringB){
        int counter = 0 ;
        int startIndex = 0;

        while(startIndex <= stringB.length()){
            stringB = stringB.substring(startIndex);

            int occurrenceIndex = foundAtIndex(stringA,stringB);
            if( occurrenceIndex == -1)
                    break;

            counter ++;
            startIndex = occurrenceIndex + stringA.length();
        }
        return counter;
    }

    private static int foundAtIndex(String stringA,String stringB){
        return stringB.indexOf(stringA);
    }
    public static void testHowMany(){
        System.out.println(howMany("GAA","ATGAACGAATTGAATC"));
        System.out.println(howMany("AA","ATAAAA"));
        System.out.println(howMany("A","AAAAA"));
        System.out.println(howMany("A","BBBBBB"));
    }

    public static void main(String[] args) {
        testHowMany();
    }
}

