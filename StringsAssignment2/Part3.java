package StringsAssignment2;

import static StringsAssignment2.Part1.findGeneMultipleStopCodons;

public class Part3 {
    public static int countAllGenes(String dna){
        int startIndex = 0;
        int counter = 0 ;
        while(true){
            String currentGene = findGeneMultipleStopCodons(dna.substring(startIndex));
            if(currentGene.isEmpty())
                break;
            counter ++;
            startIndex = dna.indexOf(currentGene,startIndex) + currentGene.length();
        }
        return counter;
    }

    public static void testCountAllGenes(){
        System.out.println(countAllGenes("ATGTAAGATGCCCTAGT"));
        System.out.println(countAllGenes("ATGCCCTAG"));
        System.out.println(countAllGenes("ATGCCC"));
    }

    public static void main(String[] args) {
        testCountAllGenes();
    }
}
