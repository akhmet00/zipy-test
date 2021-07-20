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

    private static final FileWriter fileWriter = new FileWriterImpl();
    private static final RequestResult requestResult = new RequestResultImpl();

    public static void main(String[] args) {
        try {

            List<Result> resultList = requestResult.getResultList(); //requesting results from flashdeals

            fileWriter.writeToFile(resultList); //writing results to file

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
