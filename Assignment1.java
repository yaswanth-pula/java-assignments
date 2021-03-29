import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class FileFinder{
    public void printMatchedFiles(String pattern) {
        System.out.println(pattern);
        String projectHomeDirectoryPath = "./";
        File[] files = new File(projectHomeDirectoryPath).listFiles();
        if(files == null)
            return;
        for(File currentFile : files){
            printFilePathInDirectory(currentFile.listFiles(),pattern);
        }
    }

    private  void printFilePathInDirectory(File[] directoryFiles,String filePattern) {
        if(directoryFiles == null)
            return;
        for(File currentFile: directoryFiles){
            if(currentFile.isDirectory()){
                File [] filesInDirectory = currentFile.listFiles();
                printFilePathInDirectory(filesInDirectory,filePattern);
            }
            else{
                Pattern pattern = Pattern.compile(filePattern);
                Matcher patternMatcher = pattern.matcher(currentFile.getName());
                if(patternMatcher.matches())
                    System.out.println(currentFile.getAbsolutePath());
            }
        }

    }
}
public class Assignment1 {
    public static void main(String[] args){
        Scanner inputScanner = new Scanner(System.in);
        FileFinder fileFinder = new FileFinder();
        String inputPattern = "";

        while(! inputPattern.equalsIgnoreCase("quit")){
            System.out.println("Enter Enter Pattern to Search or ` quit ` for exit ");
            inputPattern = inputScanner.nextLine();

            if(inputPattern.equalsIgnoreCase("quit")){
                System.exit(0);
            }

            fileFinder.printMatchedFiles(inputPattern);
        }
    }
}
