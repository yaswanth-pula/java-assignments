package miniproject.babynames;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

class BabyNamesProcessor{
    public void printTotalBirthsOfBoysAndGirls(){
        FileResource fileResource = new FileResource();
        CSVParser csvParser = fileResource.getCSVParser(false);
        int numberOfBoys  = 0;
        int numberOfGirls = 0;
        for(CSVRecord record :csvParser){
            String gender = record.get(1);
            int namesCount = Integer.parseInt(record.get(2));
                if(gender.equals("M"))
                    numberOfBoys += namesCount;
                else
                    numberOfGirls += namesCount;
        }
        int totalNames = numberOfBoys+numberOfGirls;
        System.out.println("Total Names in the year are "+totalNames);
        System.out.println("Number of Boys names are "+numberOfBoys);
        System.out.println("Number of Girls names are "+numberOfGirls);
    }

    public int getRankByName(int queryYear,String queryName,String queryGender){
        int rank = 1;
        boolean isRankResetNeeded = true;

        FileResource fileResource = new FileResource(getFilePathByYear(queryYear));
        CSVParser csvParser = fileResource.getCSVParser(false);

        for(CSVRecord record : csvParser){
            String currentRecordName = record.get(0);
            String currentRecordGender = record.get(1);

            if(isRankResetNeeded && isMale(currentRecordGender)) {
                rank = 1;
                isRankResetNeeded = false;
            }
            if(queryGender.equals(currentRecordGender) && currentRecordName.equals(queryName))
                return rank;
            rank++;
        }
        return -1;
    }

    public String getNameByRank(int queryYear,int queryRank,String queryGender){
        String nameWithRank = "NO NAME";

        FileResource fileResource = new FileResource(getFilePathByYear(queryYear));
        CSVParser csvParser = fileResource.getCSVParser(false);

        for(CSVRecord currentRecord : csvParser){
            String currentRecordGender = currentRecord.get(1);

            if(currentRecordGender.equals(queryGender)){

                String currentRecordName = currentRecord.get(0);

                int currentRecordRank = getRankByName(queryYear,currentRecordName,queryGender);

                if(currentRecordRank == queryRank)
                    return currentRecordName;
            }

        }

        return nameWithRank;
    }

    public String whatIsNameInYear(String queryName,int queryOldYear, int queryNewYear,String queryGender ){
            int oldYearRank = getRankByName(queryOldYear,queryName,queryGender);
            return getNameByRank(queryNewYear,oldYearRank,queryGender);
    }

    public int yearOfHighestRank(String queryName,String queryGender){
        DirectoryResource directoryResource = new DirectoryResource();

        int highestYear = -1;
        int highestRank = Integer.MAX_VALUE;
        for(File currentFile: directoryResource.selectedFiles()){
            int currentFileYear = turnFileNameToYear(currentFile.getName());
            int currentRankOfName = getRankByName(currentFileYear,queryName,queryGender);

            if( currentRankOfName != -1 && currentRankOfName < highestRank) {
                    highestYear = currentFileYear;
                    highestRank = currentRankOfName;
            }
        }
        return highestYear;
    }

    public double getAverageRank(String queryName,String queryGender){
        DirectoryResource directoryResource = new DirectoryResource();

        double averageRank = -1.0;
        int totalRankSum = 0;
        int totalYears = 0;

        for(File currentFile: directoryResource.selectedFiles()){
            int currentFileYear = turnFileNameToYear(currentFile.getName());
            int currentRankOfName = getRankByName(currentFileYear,queryName,queryGender);
            totalYears++;
            if(currentRankOfName != -1) {
               totalRankSum += currentRankOfName;
            }
        }
        if(totalRankSum == 0.0)
                return averageRank;

        averageRank = ( (double)totalRankSum )/ totalYears;
        return averageRank;

    }

    public int getTotalBirthsRankedHigher(int queryYear,String queryName,String queryGender){

        int currentRankOfName = getRankByName(queryYear,queryName,queryGender);

        int totalBirthSum = 0;
        int recordCount = 0;
        FileResource fileResource = new FileResource(getFilePathByYear(queryYear));
        CSVParser csvParser = fileResource.getCSVParser(false);
        for(CSVRecord currentRecord : csvParser){

            recordCount++;
            if(recordCount == currentRankOfName)
                break;
            String currentRecordGender = currentRecord.get(1);
            if(currentRecordGender.equals(queryGender)){
                int currentBirths = Integer.parseInt(currentRecord.get(2));
                totalBirthSum += currentBirths;
            }
            else
                recordCount = 0;

        }
        return totalBirthSum;
    }

    // utils
    private boolean isMale(String gender){
        return gender.equals("M");
    }
    private String getFilePathByYear(int year){
        return getFilesPath()+"us_babynames_by_year/yob"+year+".csv";
    }
    private String getFilesPath(){
        return "us_babynames/";
    }
    private int turnFileNameToYear(String fileName){
        String yearString = fileName.substring(3,7);
        return Integer.parseInt(yearString);
    }
}



public class BabyNamesAssignmentRunner {
    public static void main(String[] args) {
        BabyNamesProcessor processor  = new BabyNamesProcessor();
        processor.printTotalBirthsOfBoysAndGirls();
        int rank  = processor.getRankByName(2012,"Mason","M");
        System.out.println(rank);
        String name = processor.getNameByRank(2014,3,"M");
        System.out.println(name);
        String nameInYear = processor.whatIsNameInYear("Chloe",2012,2014,"F");
        System.out.println("Chole born in 2012 would be "+nameInYear+" if she was born in 2014.");
        int year = processor.yearOfHighestRank("Olivia","F");
        System.out.println("Highest Rank in the year "+year);
        System.out.println(processor.getAverageRank("Emma","F"));
        System.out.println(processor.getTotalBirthsRankedHigher(2014,"Ava","F"));

    }

}
