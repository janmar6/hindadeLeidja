import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
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

        if(resStatus == 200){
            Scanner scanner = new Scanner(connection.getInputStream());
            String regex = "\"impressions\": \\[.*?]";
            Pattern pattern = Pattern.compile(regex);

            scanner.useDelimiter("\\Z");
            String content = scanner.next();
            System.out.println(content);

//            Matcher matcher = pattern.matcher(content);
//
//            boolean matchFound = matcher.find();
//
//            if(matchFound) {
//                System.out.println(matcher.group());
//            } else {
//                System.out.println("Match not found");
//            }

            scanner.close();



        } else {
            System.out.println("error fetching");
        }


        return "";
    }

    public static void main(String[] args) throws IOException {
        getBaseAndmed("https://www.rimi.ee/epood/ee/otsing?currentPage=1&pageSize=60&query=kinder");
    }
}
