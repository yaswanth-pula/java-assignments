package csv.assignments;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

import static java.lang.Float.NaN;

class ParsingWeatherData{
    public CSVRecord coldestHourInFile(CSVParser csvParser){
        CSVRecord lowestRecord = null;
        for(CSVRecord currentRecord : csvParser){
            lowestRecord = getLowestTemperatureOfTwoRecords(currentRecord,lowestRecord);
        }

        return lowestRecord;
    }
    public void testColdestHourInFile(){
        FileResource fileResource = new FileResource();
        CSVParser csvParser = fileResource.getCSVParser();
        CSVRecord coldestRecord = coldestHourInFile(csvParser);
        System.out.println(coldestRecord.get("TemperatureF")+"   "+coldestRecord.get("DateUTC"));
    }

    public String fileWithColdestTemperature(){
        String coldestTemperatureFileName = "";
        DirectoryResource dirResource = new DirectoryResource();
        CSVRecord coldestRecord = null;
        for(File currentFile: dirResource.selectedFiles()){
            FileResource fileResource = new FileResource(currentFile);
            CSVRecord currentColdestRecord = coldestHourInFile(fileResource.getCSVParser());
            coldestRecord = getLowestTemperatureOfTwoRecords(currentColdestRecord,coldestRecord);
            if(currentColdestRecord == coldestRecord)
                    coldestTemperatureFileName = currentFile.getAbsolutePath();
        }
        return coldestTemperatureFileName;
    }
    public void testFileWithColdestTemperature(){
        String coldestFilePath = fileWithColdestTemperature();
        File file = new File(coldestFilePath);
        System.out.println("Coldest day was in file "+file.getName());
        FileResource fileResource = new FileResource(coldestFilePath);
        CSVRecord coldestRecord = coldestHourInFile(fileResource.getCSVParser());
        System.out.println("Coldest Temperature of the day is : "+coldestRecord.get("TemperatureF"));
        System.out.println("All the Temperature on the coldest day were:");
        for(CSVRecord record: fileResource.getCSVParser()){
            System.out.println(record.get("DateUTC")+" "+record.get("TemperatureF"));
        }
    }

    public CSVRecord lowestHumidityInFile(CSVParser csvParser){
        CSVRecord lowestRecord  = null;
        for(CSVRecord currentRecord: csvParser){
            int lowestHumidity = getHumidityFromRecord(lowestRecord);
            int currentHumidity = getHumidityFromRecord(currentRecord);
            if(lowestHumidity > currentHumidity)
                    lowestRecord = currentRecord;
        }
        return lowestRecord;
    }
    public void testLowestHumidityInFile(){
        FileResource fileResource = new FileResource();
        CSVParser csvparser = fileResource.getCSVParser();
        CSVRecord lowHumidityRecord = lowestHumidityInFile(csvparser);
        System.out.println("Lowest Humidity was "+lowHumidityRecord.get("Humidity")
                            +" at "+lowHumidityRecord.get("DateUTC"));
    }

    public CSVRecord lowestHumidityInManyFiles(){
        DirectoryResource dirResource = new DirectoryResource();
        CSVRecord lowHumidityRecord = null;
        for(File currentFile: dirResource.selectedFiles()){
            FileResource fileResource = new FileResource(currentFile);
            CSVRecord currentRecord = lowestHumidityInFile(fileResource.getCSVParser());
            int lowestHumidity = getHumidityFromRecord(lowHumidityRecord);
            int currentHumidity = getHumidityFromRecord(currentRecord);
            if(lowestHumidity > currentHumidity)
                lowHumidityRecord = currentRecord;
        }
        return lowHumidityRecord;
    }
    public void testLowestHumidityInManyFiles(){
        CSVRecord lowHumidityRecord = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was "+lowHumidityRecord.get("Humidity")
                +" at "+lowHumidityRecord.get("DateUTC"));
    }

    public double averageTemperatureInFile(CSVParser csvParser){
        double averageTemperature = 0.0;
        double totalTemperature = 0.0;
        int record_count = 0;
        for(CSVRecord currentRecord : csvParser){
            totalTemperature += Double.parseDouble(currentRecord.get("TemperatureF"));
            record_count ++;
        }
        averageTemperature = totalTemperature / record_count;
        return averageTemperature;
    }
    public void testAverageTemperatureInFile(){
        FileResource fileResource = new FileResource();
        CSVParser csvParser = fileResource.getCSVParser();
        System.out.println("Average temperature in file is "+averageTemperatureInFile(csvParser));
    }

    public double averageTemperatureWithHighHumidityInFile(CSVParser csvParser, int humidityValue){
        double averageTemperature = 0.0;
        double totalTemperature = 0.0;
        int record_count = 0;
        for(CSVRecord currentRecord : csvParser){
            double currentTemperature= Double.parseDouble(currentRecord.get("TemperatureF"));
            int currentHumidity = getHumidityFromRecord(currentRecord);
            if(currentHumidity >=humidityValue) {
                totalTemperature+=currentTemperature;
                record_count++;
            }
        }
        if(record_count == 0)
            return averageTemperature;
        averageTemperature = totalTemperature / record_count;
        return averageTemperature;
    }
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fileResource = new FileResource();
        CSVParser csvparser = fileResource.getCSVParser();
        double averageTemperature = averageTemperatureWithHighHumidityInFile(csvparser,80);
        if(averageTemperature == 0.0)
            System.out.println("No temperatures with that humidity");
        else
            System.out.println("Average Temp when high Humidity is "+averageTemperature);
    }

    private int getHumidityFromRecord(CSVRecord record){
        int humidity = Integer.MAX_VALUE;
        if(record==null) return humidity;
        String humidityString = record.get("Humidity");
        if(humidityString.equals("N/A"))
            return humidity;
        else
            return Integer.parseInt(humidityString);
    }
    private CSVRecord getLowestTemperatureOfTwoRecords(CSVRecord currentRecord, CSVRecord lowestRecord){
        if(lowestRecord == null)
            return currentRecord;

        double currentRecordTemperature = Double.parseDouble(currentRecord.get("TemperatureF"));
        double lowestRecordTemperature = Double.parseDouble(lowestRecord.get("TemperatureF"));
        if( currentRecordTemperature!= -9999 &&( lowestRecordTemperature >= currentRecordTemperature) ) {
            return currentRecord;
        }
        else
            return lowestRecord;
    }


}

public class AssignmentRunner2 {
    public static void main(String[] args) {
        ParsingWeatherData parsingWeatherData = new ParsingWeatherData();
        parsingWeatherData.testColdestHourInFile();
        parsingWeatherData.testFileWithColdestTemperature();
        parsingWeatherData.testLowestHumidityInFile();
        parsingWeatherData.testLowestHumidityInManyFiles();
        parsingWeatherData.testAverageTemperatureInFile();
        parsingWeatherData.testAverageTemperatureWithHighHumidityInFile();
    }

}
