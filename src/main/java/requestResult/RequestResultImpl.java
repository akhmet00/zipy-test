package requestResult;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Result;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RequestResultImpl implements RequestResult {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String URL = "https://gpsfront.aliexpress.com/getRecommendingResults.do?widget_id=5547572&limit=12&offset=";


    @Override
    public List<Result> getResultList() throws Exception {

        int offset = 0;  //moving by pages. 0 - first page, 2 - second, 3 - third etc.
        String postback = ""; //postback is required for pagination. Every page response has postback to use.

        List<Result> resultList = new ArrayList<>(); //global result list

        while (resultList.size() <= 100) { //send requests while list of results (goods) will not be less than 100

            String request = URL + //URL of API
                    12 * offset + //Moving by pagination. 12 - default number of elements on page.
                    "&phase=1" + //phase - type of goods (results). 1 - now, 2 - upcoming.
                    "&postback=" + postback;  //postback is required for pagination. Every page response has postback to use.

            URL url = new URL(request); //Object class of URL

            HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //proceeding connection with API

            connection.setRequestMethod("GET"); //Method of connection

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream())); //Reading response
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close(); //closing stream

            JSONObject jsonObject = new JSONObject(response.toString()); // converting string response to JSON

            List<Result> result = objectMapper.readValue(jsonObject.get("results").toString(), new TypeReference<List<Result>>() {
            }); //Mapping JSON to DTO

            resultList.addAll(result); //Merging results to global list

            postback = jsonObject.get("postback").toString(); //Getting postback to proceed next page.

            if (offset == 1)  //this if required for correct offset coefficient.
                offset = 2;
            else
                offset++;

        }
        return resultList;
    }
}
