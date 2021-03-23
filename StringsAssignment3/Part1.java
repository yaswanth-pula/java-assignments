package StringsAssignment3;

import edu.duke.StorageResource;

import static StringsAssignment2.Part1.findGeneMultipleStopCodons;

public class Part1 {
    public static StorageResource getAllGenes(String dna){
        StorageResource storageResource = new StorageResource();
        int startIndex = 0;
        while(true){
            String currentGene = findGeneMultipleStopCodons(dna.substring(startIndex));
            if(currentGene.isEmpty())
                break;

            storageResource.add(currentGene);

            startIndex = dna.indexOf(currentGene,startIndex) + currentGene.length();
        }
        return storageResource;
    }

    public static void testGetAllGenes(){
        String testDna = "ATGATCTAATTTATGCTGCAACGGTGAAGA";
        StorageResource testStorageResource = getAllGenes(testDna);
        // printing all genes
        for(String gene : testStorageResource.data())
            System.out.println(gene);
    }
    public static void main(String[] args) {
            testGetAllGenes();
    }
}
