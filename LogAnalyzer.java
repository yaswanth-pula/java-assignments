
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

     public HashMap<String,Integer> countVisitsPerIP(){
         HashMap<String,Integer> ipVisitMap = new HashMap<>();
         for(LogEntry logEntry : records){
             String logIp = logEntry.getIpAddress();
             if(ipVisitMap.containsKey(logIp)){
                 int ipCount = ipVisitMap.get(logIp);
                 ipVisitMap.put(logIp,ipCount+1);
             }
             else {
                 ipVisitMap.put(logIp, 1);
             }
         }
         return ipVisitMap;
     }

     public int mostVisitsCountByIP(HashMap<String,Integer> ipMap){
         int maxVisit = 0;
         for(Integer visitCount: ipMap.values()){
             if(visitCount > maxVisit)
                    maxVisit = visitCount;
         }
         return maxVisit;
     }

     public ArrayList<String> mostVisitedIps(HashMap<String,Integer> ipMap){
         ArrayList<String> mostVisitedIpList = new ArrayList<>();
         int maxVisitCount = mostVisitsCountByIP(ipMap);
         for(String currentIp : ipMap.keySet()){
             if(ipMap.get(currentIp) == maxVisitCount){
                    mostVisitedIpList.add(currentIp);
             }
         }
         return mostVisitedIpList;
     }

     public HashMap<String,ArrayList<String>> ipListForDaysMap(){
         HashMap<String,ArrayList<String>> ipListDayMap = new HashMap<>();
         for(LogEntry logEntry : records){
                String logDay = logEntry.getAccessTime().toString().substring(4,10);
                if(ipListDayMap.containsKey(logDay)){
                        ArrayList<String> ipList = ipListDayMap.get(logDay);
                        String logIp = logEntry.getIpAddress();
                        if(!ipList.contains(logIp))
                                ipList.add(logIp);
                        ipListDayMap.put(logDay,ipList);
                }
                else{
                    ArrayList<String> ipList = new ArrayList<>();
                    String logIp = logEntry.getIpAddress();
                    ipList.add(logIp);
                    ipListDayMap.put(logDay,ipList);
                }

            }
         return ipListDayMap;
     }

     public String dayWithMostIpVisit(HashMap<String,ArrayList<String>> ipListForDays){
         int maxIpVisitCount = 0;
         String maxVisitDay = "";
            for(String day: ipListForDays.keySet()){
                int currentIpVisitCount = ipListForDays.get(day).size();
                if(maxIpVisitCount <= currentIpVisitCount){
                        maxIpVisitCount = currentIpVisitCount;
                        maxVisitDay = day;
                }
            }

         return maxVisitDay;
     }

     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> ipAddressOnDayMap,
                                                     String day){
            HashMap<String,Integer> ipMap = new HashMap<>();
            for(String ip : ipAddressOnDayMap.get(day)){
                if(!ipMap.containsKey(ip)){
                    ipMap.put(ip,1);
                }
                else{
                    int ipCount = ipMap.get(ip);
                    ipMap.put(ip,ipCount+1);
                }
            }
            return mostVisitedIps(ipMap);
     }
}
