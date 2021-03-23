package StringsAssignment1;

public class Part1 {
    public static String findSimpleGene(String dna){
        String simpleGene = "";

        int startCodonIndex = dna.indexOf("ATG");
        if(startCodonIndex == -1) return simpleGene;

        int stopCodonIndex = dna.indexOf("TAA",startCodonIndex+3);
        if(stopCodonIndex == -1) return simpleGene;

        int simpleGeneLength = stopCodonIndex - startCodonIndex;
        if((simpleGeneLength % 3 ) == 0)
                simpleGene = dna.substring(startCodonIndex, stopCodonIndex+3);

        return simpleGene;
    }

    public static void testSimpleGene(){
        String [] testGeneStrings = { "TGCTCTAC","ATGTCCAG","GGGGCCCTTT","GGGATGTCCTAACC", "GGGATGTCTAACC" };

        for(String testGene : testGeneStrings)
            System.out.println(findSimpleGene(testGene));

    }

    public static void main(String[] args) {
        testSimpleGene();
    }
}
