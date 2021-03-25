package gladlibsImprovement;

import edu.duke.FileResource;

import java.util.HashMap;

class CodonCount{
     private HashMap<String,Integer> codonMap;

    CodonCount(){
        codonMap = new HashMap<>();
    }

    private void buildCodonMap(int readingFrameStart,String dna){
        codonMap.clear();
        for(int index = readingFrameStart; index<dna.length(); index+=3){
            String codon;
            if(index+3 <= dna.length())
                    codon = dna.substring(index,index+3);
            else
                break;
            if(codonMap.containsKey(codon)){
                int codonCount = codonMap.get(codon);
                codonMap.put(codon,codonCount+1);
            }
            else{
                codonMap.put(codon,1);
            }
        }
    }

    private String getMostCommonCodon(){
        int maxCodonCount = 0;
        String mostOccurredCodon = "";
        for(String currentCodon : codonMap.keySet()){
            int currentCodonCount = codonMap.get(currentCodon);
            if(currentCodonCount > maxCodonCount){
                mostOccurredCodon = currentCodon;
                maxCodonCount = currentCodonCount;
            }
        }
        return mostOccurredCodon;
    }

    private void printCodonCounts(int minCount,int maxCount){
        System.out.print("Count of codons between "+minCount+" and ");
        System.out.println(maxCount+" inclusive are:");
        for(String currentCodon : codonMap.keySet()){
            int currentCodonCount = codonMap.get(currentCodon);
            if(currentCodonCount >= minCount && currentCodonCount <= maxCount)
                System.out.println(currentCodon+"\t"+currentCodonCount);
        }
    }

    public void tester(){
        FileResource fileResource = new FileResource();
        String dnaInFile = fileResource.asString();
        dnaInFile = removeExtraSpaces(dnaInFile);

        for(int frameStart = 0; frameStart < 3 ; frameStart++) {
            buildCodonMap(frameStart, dnaInFile);
            printFormattedResult(frameStart);
            printCodonCounts(1, 5);
        }

    }
    private void printFormattedResult(int readingFrameStart){
        System.out.print("Reading frame starting with "+readingFrameStart);
        System.out.println(" results in "+codonMap.size()+" unique codons");

        String mostCommonCodon = getMostCommonCodon();
        System.out.print(" and Most common codon is "+mostCommonCodon);
        System.out.println(" with a count "+codonMap.get(mostCommonCodon));

    }

    private String removeExtraSpaces(String dnaInFile){
        return dnaInFile.trim();
    }

}

public class CodonCountRunner {
    public static void main(String[] args) {
        CodonCount codonCount = new CodonCount();
        codonCount.tester();
    }
}
