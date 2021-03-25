package gladlibsImprovement;

import edu.duke.FileResource;
import edu.duke.URLResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

class GladLibMapVersion{
    private HashMap<String, ArrayList<String>> labelsMap;

    private ArrayList<String> usedWordsList;
    private ArrayList<String> usedLabelList;


    private Random myRandom;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";

    public GladLibMapVersion(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }

    public GladLibMapVersion(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }

    private void initializeFromSource(String source) {
        labelsMap = new HashMap<>();
        String[] labels ={ "adjective","noun","color","country",
                "name","animal","timeframe","verb","fruit"};
        for(String label : labels){
            ArrayList<String> wordsList = readIt(source+"/"+label+".txt");
            labelsMap.put(label,wordsList);
        }
        usedWordsList = new ArrayList<>();
        usedLabelList = new ArrayList<>();
    }

    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {
        if(label.equals("number"))
            return "" + myRandom.nextInt(50) + 5;
        if(!usedLabelList.contains(label))
            usedLabelList.add(label);
        return randomFrom(labelsMap.get(label));
    }

    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String label = w.substring(first+1,last);
        String sub = getSubstitute(label);
        while(usedWordsList.contains(sub)){
            sub = getSubstitute(w.substring(first+1,last));
        }
            usedWordsList.add(sub);
        return prefix + sub + suffix;

    }

    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }

    private int totalWordsInMap(){
        int totalWords = 0;
        for(String label: labelsMap.keySet()){
            totalWords += labelsMap.get(label).size();
        }
        return totalWords;
    }

    private int totalWordsConsidered(){
        int totalWords = 0;
        for (String label : usedLabelList) {
            totalWords += labelsMap.get(label).size();
        }
        return totalWords;
    }

    public void makeStory(){
        usedWordsList.clear();
        System.out.println("\n");
        String story = fromTemplate("datalong/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\nNumber Of Words Replaced are : "+usedWordsList.size());
        System.out.println("words used for replacing:\n"+usedWordsList);
        System.out.println("Total Words In the Map: "+totalWordsInMap());
        System.out.println("Total Words Considered for GladLib: "+totalWordsConsidered());

    }
}

public class GladLibMapVersionRunner {
    public static void main(String[] args) {
        GladLibMapVersion mapVersion = new GladLibMapVersion();
        mapVersion.makeStory();
    }
}
