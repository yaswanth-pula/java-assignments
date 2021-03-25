
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }

    public void testLogAnalyzer() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("src/LogFiles/short-test_log");
        logAnalyzer.printAll();
    }
    public void testUniqueIP(){
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("src/LogFiles/short-test_log");
        System.out.println(logAnalyzer.countUniqueIPs());
    }
    public void testHigherThanNum(){
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("src/LogFiles/short-test_log");
        logAnalyzer.printAllHigherThanNum(200);
    }
    public void testUniqueIPOnDay(){
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("src/LogFiles/weblog-short_log");
        ArrayList<String> uniqueIPVisitList = logAnalyzer.uniqueIPVisitsOnDay("Sep 30");
        for(String ip : uniqueIPVisitList){
            System.out.println(ip);
        }

    }
    public void testUniqueCountRange(){
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("src/LogFiles/short-test_log");
        System.out.println(logAnalyzer.countUniqueIPsInRange(200,299));
        System.out.println(logAnalyzer.countUniqueIPsInRange(300,399));
    }

    public static void main(String[] args) {
        Tester tester = new Tester();
//        tester.testLogAnalyzer();
//        tester.testUniqueIP();
//        tester.testHigherThanNum();
//        tester.testUniqueIPOnDay();
//        tester.testUniqueCountRange();
    }
}
