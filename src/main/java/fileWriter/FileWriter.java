package fileWriter;

import model.Result;

import java.util.List;

public interface FileWriter {

    void writeToFile(List<Result> resultList) throws Exception;


}
