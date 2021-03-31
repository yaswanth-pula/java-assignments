import java.io.IOException;
import java.util.*;

/**
 * Write a java function that will ping any host ( given as input )
 * and computes the median of the time taken to ping.
 * <p>
 * Use the system ping utility, do not use any deprecated methods.
 */

class PingMedian {

    public void printPingMedian(String host, int pingCount) {
        if (host == null) return;

        double[] pingTimings = new double[pingCount];
        try {
            pingTimings = getPingTime(host, pingCount);
        } catch (IOException exception) {
            System.out.println("0.0");
        }

        Arrays.sort(pingTimings);
        System.out.print("Ping Median for " + host + " is : ");

        if (pingCount % 2 != 0) {
            System.out.println(pingTimings[(pingCount / 2)]);
        } else {
            double mid1 = pingTimings[((pingCount - 1) / 2)];
            double mid2 = pingTimings[((pingCount + 1) / 2)];
            System.out.println((mid1 + mid2) / 2.0);
        }
    }

    private double[] getPingTime(String host, int pingCount) throws IOException {
        double[] pingTimings = new double[pingCount];
        String[] commandList = {"ping", host, "-c", String.valueOf(pingCount)};
        ProcessBuilder processBuilder = new ProcessBuilder(commandList);
        Process process = processBuilder.start();

        ArrayList<String> pingResult = new ArrayList<>();
        Scanner inputScanner = new Scanner(process.getInputStream());
        Scanner outputScanner = new Scanner(process.getErrorStream());

        while (outputScanner.hasNextLine())
            System.out.println(outputScanner.nextLine());

        while (inputScanner.hasNextLine()) {
            String resultLine = inputScanner.nextLine();
            int timeStringIndex = resultLine.indexOf("time=");
            if (timeStringIndex != -1) {
                pingResult.add(resultLine.substring(timeStringIndex + 5));
            }
        }

        for (int index = 0; index < pingResult.size(); index++) {
            String currentTimeString = pingResult.get(index);
            double currentPingValue = Double.parseDouble(currentTimeString.split(" ")[0]);

            pingTimings[index] = currentPingValue;
        }

        return pingTimings;
    }

}

public class Assignment3 {
    public static void main(String[] args) {
        PingMedian median = new PingMedian();
        median.printPingMedian("not a host", 3);
        median.printPingMedian("8.8.8.8", 5);
        median.printPingMedian("www.youtube.com", 8);
    }
}
