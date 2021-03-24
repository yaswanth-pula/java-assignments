package csv.assignments;

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

class ParsingExportData{
    public void tester(){
        FileResource fileResource = new FileResource();
        CSVParser csvParser = fileResource.getCSVParser();
        System.out.println(countryInfo(csvParser,"Nauru"));
        listExportersTwoProducts(fileResource.getCSVParser(),"gold","diamonds");
        System.out.println(numberOfExporters(fileResource.getCSVParser(),"sugar"));
        bigExporters(fileResource.getCSVParser(),"$999,999,999,999");
    }
    public String countryInfo(CSVParser csvParser,String queryCountry){
        String countryInfo = "NOT_FOUND";
        for(CSVRecord record : csvParser){
            String countryName = record.get("Country");
            if(countryName.equalsIgnoreCase(queryCountry)) {
                countryInfo = record.get("Country") + ": ";
                countryInfo = countryInfo + record.get("Exports")+ ": ";
                countryInfo = countryInfo + record.get("Value (dollars)");
                break;
            }
        }
        return countryInfo;
    }
    public void listExportersTwoProducts(CSVParser csvParser, String queryExportItem1, String queryExportItem2){
        for(CSVRecord record: csvParser){
            String currentExportProducts = record.get("Exports");
            if( currentExportProducts.contains(queryExportItem1) && currentExportProducts.contains(queryExportItem2)){
                String countryName = record.get("Country");
                System.out.println(countryName);
            }
        }
    }
    public int numberOfExporters(CSVParser csvParser,String queryExportItem){
        int numberOfExporters = 0;
        for(CSVRecord record: csvParser){
            String currentExportProducts = record.get("Exports");
            if( currentExportProducts.contains(queryExportItem) ){
                numberOfExporters++;
            }
        }
        return numberOfExporters;
    }
    public void bigExporters(CSVParser csvParser,String queryAmount){
        for(CSVRecord record: csvParser){
            String currentRecordAmount = record.get("Value (dollars)");
            if(currentRecordAmount.length() > queryAmount.length()){
                String currentCountry = record.get("Country");
                System.out.println(currentCountry+" "+currentRecordAmount);
            }
        }
    }
}
public class Assignment1Runner {
    public static void main(String[] args) {
        ParsingExportData exportData = new ParsingExportData();
        exportData.tester();
    }
}
