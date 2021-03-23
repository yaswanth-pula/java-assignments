package StringsAssignment1;

import edu.duke.URLResource;

public class Part4 {
    public static void printYoutubeURL() {
        String courseUrl = "https://www.dukelearntoprogram.com/course2/data/manylinks.html";
        URLResource urlResource = new URLResource(courseUrl);

        for (String word : urlResource.words()) {

            int urlIndex = word.toLowerCase().indexOf("www.youtube.com");

            if (urlIndex != -1) {
                int lastOccurrenceQuote = word.lastIndexOf("\"", urlIndex);
                int firstOccurrenceQuote = word.indexOf("\"", urlIndex);

                String extractedURL = word.substring(lastOccurrenceQuote + 1, firstOccurrenceQuote);
                System.out.println(extractedURL);
            }
        }
    }

    public static void main(String[] args) {
        printYoutubeURL();
    }
}
