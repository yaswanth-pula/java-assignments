import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

class ValidKYCDates {

    public void printKYCRange(String input) {
        String signupDateString = getDateFromInput(input, 0);
        String currentDateString = getDateFromInput(input, 1);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        Calendar signupDate = Calendar.getInstance();
        Calendar currentDate = Calendar.getInstance();
        try {
            signupDate.setTime(dateFormat.parse(signupDateString));
            currentDate.setTime(dateFormat.parse(currentDateString));
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Invalid inputs");
        }

        System.out.println("\n" + getRangeOfKYC(signupDate, currentDate));

    }

    private String getRangeOfKYC(Calendar signupDate, Calendar currentDate) {
        Calendar anniversaryDate = Calendar.getInstance();
        anniversaryDate.setTime(signupDate.getTime());
        anniversaryDate.add(Calendar.YEAR, 1);
        anniversaryDate.add(Calendar.DATE, -30);

        if (currentDate.before(anniversaryDate)) {
            return "\nNo Range\n";
        }

        int currentYEAR = currentDate.get(Calendar.YEAR);
        signupDate.set(Calendar.YEAR, currentYEAR);

        Calendar rangeStartDate = Calendar.getInstance();
        rangeStartDate.setTime(signupDate.getTime());
        rangeStartDate.add(Calendar.DATE, -30);

        Calendar rangeEndDate = Calendar.getInstance();
        rangeEndDate.setTime(signupDate.getTime());
        rangeEndDate.add(Calendar.DATE, 30);


        if (rangeEndDate.after(currentDate)) {
            rangeEndDate = currentDate;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        return dateFormat.format(rangeStartDate.getTime())
                + " " +
                dateFormat.format(rangeEndDate.getTime());

    }

    private String getDateFromInput(String input, int position) {
        return input.trim().split(" ")[position];
    }

}

public class Assignment4 {
    public static void main(String[] args) {
        ValidKYCDates validator = new ValidKYCDates();
        Scanner scanner = new Scanner(System.in);
        int numberOfQueries = scanner.nextInt();
        scanner.nextLine();

        while (numberOfQueries > 0) {
            String input = scanner.nextLine();
            validator.printKYCRange(input);
            numberOfQueries--;
        }
    }
}