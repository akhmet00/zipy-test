import fileWriter.FileWriter;
import fileWriter.FileWriterImpl;
import model.Result;
import requestResult.RequestResult;
import requestResult.RequestResultImpl;

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
