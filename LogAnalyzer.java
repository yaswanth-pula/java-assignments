
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<>();
     }
        
     public void readFile(String filename) {
         FileResource fileResource = new FileResource(filename);
         for(String currentLine : fileResource.lines()){
             LogEntry currentLogEntry = WebLogParser.parseEntry(currentLine);
             records.add(currentLogEntry);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }

     public int countUniqueIPs(){
         ArrayList<String> uniqueIpList = new ArrayList<>();
         for(LogEntry logEntry : records){
             String logIp = logEntry.getIpAddress();
             if(! uniqueIpList.contains(logIp))
                    uniqueIpList.add(logIp);
         }
         return uniqueIpList.size();
     }

     public void printAllHigherThanNum(int minStatusCode){
            for(LogEntry logEntry : records){
                int currentLogStatusCode = logEntry.getStatusCode();
                if(currentLogStatusCode > minStatusCode)
                    System.out.println(logEntry);
            }
     }

     public ArrayList<String> uniqueIPVisitsOnDay(String someDay){
         ArrayList<String> uniqueIpList = new ArrayList<>();

         for(LogEntry logEntry : records){
             String logIp = logEntry.getIpAddress();
             String logDay = logEntry.getAccessTime().toString();
             if( (! uniqueIpList.contains(logIp) ) && logDay.contains(someDay) )
                 uniqueIpList.add(logIp);
         }
         return uniqueIpList;
     }

     public int countUniqueIPsInRange(int minStatusCode, int maxStatusCode){
         ArrayList<String> uniqueIpList = new ArrayList<>();
         for(LogEntry logEntry : records){
             String logIp = logEntry.getIpAddress();
             int currentLogStatusCode = logEntry.getStatusCode();
             boolean isInRange = currentLogStatusCode >= minStatusCode
                                && currentLogStatusCode <= maxStatusCode;

             if(! uniqueIpList.contains(logIp) && isInRange) {
                    uniqueIpList.add(logIp);
             }
         }
         return uniqueIpList.size();
     }



}
