import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ZipyApplication {

    public static void main(String[] args) {
        try {
            run();
        } catch (Exception e){
            System.err.println(e.getLocalizedMessage());
        }
    }

    public static void run() throws Exception {

        int offset = 0;

        List<Result> resultList = new ArrayList<>();

        String postback = "";

        while (resultList.size() <= 100) {

            String url = "https://gpsfront.aliexpress.com/getRecommendingResults.do?widget_id=5547572&limit=12&offset=" +
                    12 * offset +
                    "&phase=1" +
                    "&postback=" + postback;


            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject jsonObject = new JSONObject(response.toString());

            ObjectMapper objectMapper = new ObjectMapper();
            List<Result> result = objectMapper.readValue(jsonObject.get("results").toString(),
                    new TypeReference<List<Result>>() {
                    });
            resultList.addAll(result);

            postback = jsonObject.get("postback").toString();

            if (offset == 1) {
                offset = 2;
            } else {
                offset++;
            }

        }

        FileWriter fileWriter = new FileWriter();

        fileWriter.writeToFile(resultList);

    }

}
