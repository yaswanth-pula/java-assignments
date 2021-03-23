package StringsAssignment3;

public class Part2 {
    public static float cgRatio(String dna){
        int totalChars = dna.length();
        int count  = countCGChars(dna,totalChars);
        return (float)count / totalChars;

    }
    private static int countCGChars(String dna,int lengthOfDna){
        int counter = 0;
        for(int i = 0;i<lengthOfDna;i++){
            if(dna.charAt(i) == 'C' || dna.charAt(i) == 'G')
                counter ++;
        }

        return counter;
    }

    public static int countCTGChar(String dna){
        int counter = 0;
        int startIndex = 0;
        while(true){
            int cgtIndex = dna.indexOf("CTG",startIndex);
            if(cgtIndex == -1)
                break;
            counter ++;
            startIndex = cgtIndex + 3;
        }
        return counter;
    }

    public static void main(String[] args) {
        System.out.println(cgRatio("ATGCCATAG"));
        System.out.println(countCTGChar("CTGdCTGsCTsCTG"));
    }
}
