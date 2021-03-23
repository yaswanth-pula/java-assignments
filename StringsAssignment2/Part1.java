package StringsAssignment2;

public class Part1 {

    // --- TASk 1 ---
    public static int findStopCodonIndex(String dna,int startCodonIndex, String stopCodon){
        int currentStopCodonIndex = getCodonIndex(dna, startCodonIndex + 3, stopCodon);

        while(currentStopCodonIndex != -1){
            int currentGeneLength = currentStopCodonIndex - startCodonIndex;

            if(isMultipleOfThree(currentGeneLength))
                return currentStopCodonIndex;
            else{
                currentStopCodonIndex = getCodonIndex(dna,currentStopCodonIndex + 1 ,stopCodon);
            }
        }
        return dna.length();
    }
    // -- TASK 1 --
    public static void testFindStopCodonIndex(){
        String[] testDna = {"CCATGCGCTTAATGATAGATTAA","ATGATCTAATTTATGCTGCAACGGTGAAGA","","ATGCC","TGACCC","ATGTGA","ATGTAG"};
        for(String dna : testDna){
            System.out.println(dna);
            int taaIndex = findStopCodonIndex(dna,0,"TAA");
            System.out.println("With TAA Stop Codon "+ ((taaIndex == dna.length())?"Not Found":taaIndex));
            int tgaIndex = findStopCodonIndex(dna,0,"TGA");
            System.out.println("With TGA Stop Codon "+ ((tgaIndex == dna.length())?"Not Found":tgaIndex));
            int tagIndex = findStopCodonIndex(dna,0,"TAG");
            System.out.println("With TAG Stop Codon "+((tagIndex == dna.length())?"Not Found":tagIndex));
        }

    }

    // -- TASK 2 --
    public static String findGeneMultipleStopCodons(String dna){
        int startCodonIndex = dna.indexOf("ATG");
        if (startCodonIndex==-1) return "";

        int taaStopCodonIndex = findStopCodonIndex(dna,startCodonIndex,"TAA");
        int tagStopCodonIndex = findStopCodonIndex(dna,startCodonIndex,"TAG");
        int tgaStopCodonIndex = findStopCodonIndex(dna,startCodonIndex,"TGA");

        int minStopCodonIndex = minimumOfThree(taaStopCodonIndex,tagStopCodonIndex,tgaStopCodonIndex);

        if(minStopCodonIndex == dna.length())
            return "";

        return dna.substring(startCodonIndex,minStopCodonIndex+3);

    }
    // -- TASK 2 --
    public static void testFindGeneMultipleCodon(){
        String[] testDna = {"CCATGCGCTTAATGATAGATTAA","ATGATCTAATTTATGCTGCAACGGTGAAGA","",
                            "ATGCC","TGACCC","ATGTGA","ATGTAG","AATGCTAACTAGCTGACTAAT"};
        for(String dna : testDna){
            System.out.println("DNA : "+dna);
            System.out.println("Gene : "+findGeneMultipleStopCodons(dna));
        }
    }

    // -- TASK 3 --
    public static void printAllGenes(String dna){
        int startIndex = 0;
        while(true){
            String currentGene = findGeneMultipleStopCodons(dna.substring(startIndex));
            if(currentGene.isEmpty())
                break;
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene,startIndex) + currentGene.length();
        }

    }

    // utils
    private static int getCodonIndex(String dna, int startIndex, String codon) {
        return dna.indexOf(codon, startIndex);
    }

    private static boolean isMultipleOfThree(int number){
        return number % 3 == 0;
    }

    private static int minimumOfThree(int number1, int number2, int  number3){
        return Math.min( Math.min(number1,number2),number3);
    }

    public static void main(String[] args) {
        // -- TASK 1 --
        System.out.println("Testing for codon index");
        testFindStopCodonIndex();
        // -- TASK 2 --
        System.out.println("Testing shortest codon gene");
        testFindGeneMultipleCodon();
        // -- TASK 3 --
        System.out.println("Testing the Printing all genes");
        String testDna = "ATGATCTAATTTATGCTGCAACGGTGAAGA";
        printAllGenes(testDna);

    }
}
