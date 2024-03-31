import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AndmeteKaabitseja {
    public static String getBaseAndmed(String url) throws IOException {
        URL urlObject = new URL(url);
        HttpsURLConnection connection = (HttpsURLConnection) urlObject.openConnection();
        connection.setRequestMethod("GET");

        int resStatus = connection.getResponseCode();

        // Open a connection to the URL
        InputStream inputStream = urlObject.openStream();

        if(resStatus == 200){
            // Read the content of the HTML page
            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\Z");
            String htmlContent = scanner.next();

            // Close the scanner and input stream
            scanner.close();
            inputStream.close();

            // Define the regex pattern to match the JSON data
//            String regex = "\"impressions\": \\[.*?]";
            String regex = "\"impressions\"\\s*:\\s*\\[(?s)(.*?)]";
            Pattern pattern = Pattern.compile(regex);

            // Match the pattern against the HTML content
            Matcher matcher = pattern.matcher(htmlContent);

            // Extract and print the JSON data
            while (matcher.find()) {
                String jsonData = matcher.group();
                System.out.println(jsonData);
            }
            


        } else {
            System.out.println("error fetching");
        }


        return "";
    }

    public static void main(String[] args) throws IOException {
        getBaseAndmed("https://www.rimi.ee/epood/ee/otsing?currentPage=1&pageSize=60&query=kinder");
    }
}
