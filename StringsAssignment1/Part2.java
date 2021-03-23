package StringsAssignment1;

public class Part2 {
    public static String findSimpleGene(String dna, String startCodon, String stopCodon){
        String simpleGene = "";

        int startCodonIndex = dna.indexOf(startCodon);
        if(startCodonIndex == -1) return simpleGene;

        int stopCodonIndex = dna.indexOf(stopCodon,startCodonIndex+3);
        if(stopCodonIndex == -1) return simpleGene;

        int simpleGeneLength = stopCodonIndex - startCodonIndex;
        if((simpleGeneLength % 3 ) == 0)
            simpleGene = dna.substring(startCodonIndex, stopCodonIndex + 3);
        return simpleGene;
    }
    private static boolean isUpperCase(String dna){
        for(Character character :dna.toCharArray())
                if(Character.isLowerCase(character))
                    return false;
        return true;
    }

    public static void testSimpleGene(){
        String [] testGeneStrings = { "TGCTCTAC","ATGTCCAG","GGGGCCCTTT","GGGATGTCCTAACC", "GGGATGTCTAACC","atgtaa" };
        String upperCaseStartCodon = "ATG";
        String upperCaseStopCodon = "TAA";
        String lowerCaseStartCodon = "atg";
        String lowerCaseStopCodon = "taa";

       for(String testGene : testGeneStrings) {
           String simpleGene = "";
           if(isUpperCase(testGene))
                simpleGene = findSimpleGene(testGene, upperCaseStartCodon,upperCaseStopCodon);
           else
               simpleGene = findSimpleGene(testGene,lowerCaseStartCodon,lowerCaseStopCodon);

           System.out.println(simpleGene);
       }

    }

    public static void main(String[] args) {
        testSimpleGene();
    }

}
