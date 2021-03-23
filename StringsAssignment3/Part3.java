package StringsAssignment3;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import edu.duke.StorageResource;

import java.io.File;
import java.util.Locale;

import static StringsAssignment3.Part1.getAllGenes;
import static StringsAssignment3.Part2.cgRatio;

public class Part3 {
    public static void processGenes(StorageResource resource){

        StorageResource longerThanSixtyGeneResource = new StorageResource();
        StorageResource higherCGRatioGeneResource = new StorageResource();
        int longestGeneLength = 0 ;
        String longestGene = "";
        for(String currentGene : resource.data()){
            if(isLongerThanSixty(currentGene))
                longerThanSixtyGeneResource.add(currentGene);

            if(cgRatio(currentGene) > 0.35)
                higherCGRatioGeneResource.add(currentGene);

            if(currentGene.length() > longestGeneLength) {
                longestGene = currentGene;
                longestGeneLength = currentGene.length();

            }

        }

        System.out.println("Genes That Are Longer than 60 characters");
        for(String gene:longerThanSixtyGeneResource.data())
            System.out.println(gene);

        int notLongSixtyGeneCount = resource.size() -longerThanSixtyGeneResource.size();
        System.out.println("Number of Genes That Are Not Longer Than 60 Characters: "+notLongSixtyGeneCount);

        System.out.println("Genes whose CG Ratio is higher than 0.35");
        for(String gene:higherCGRatioGeneResource.data())
            System.out.println(gene);
        int higherCGRatioGeneCount = higherCGRatioGeneResource.size();
        System.out.println("Number of Genes whose CG Ratio is higher than 0.35: "+higherCGRatioGeneCount);

        System.out.println("Longest Gene in the Gene List is "+longestGene);

    }

    private static boolean isLongerThanSixty(String string){
        return string.length() > 60;
    }

    public static void testProcessGenes(){
        String exampleDna = "";
        DirectoryResource dr = new DirectoryResource();
        for (File currentFile : dr.selectedFiles()) {
            FileResource currentFileResource = new FileResource(currentFile);
                exampleDna = currentFileResource.asString().toUpperCase();
        }
        StorageResource geneListResource = getAllGenes(exampleDna);
        processGenes(geneListResource);
    }

    public static void main(String[] args) {
        testProcessGenes();
    }
}
