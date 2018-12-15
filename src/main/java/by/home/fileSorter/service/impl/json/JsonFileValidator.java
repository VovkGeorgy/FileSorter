package by.home.fileSorter.service.impl.json;

import by.home.fileSorter.service.IFIleValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class validate JSON file, and save it in List
 */
public class JsonFileValidator implements IFIleValidator {

    private ArrayList<File> validMessageFileList = new ArrayList<>();
    private ArrayList<File> notValidMessageFileList = new ArrayList<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonFileValidator.class);

    @Override
    public void valid(ArrayList<String[]> listOfMessages, File messageFile) {
        try {
            LOGGER.info("Try to check a file valid");
            final ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(new File(messageFile.getPath()));
            validMessageFileList.add(messageFile);
            LOGGER.debug("File {}, is valid", messageFile.getPath());
        } catch (IOException e) {
            notValidMessageFileList.add(messageFile);
            LOGGER.error("Get not valid JSON {}", messageFile.getPath());
        }
    }

    public ArrayList<File> getValidMessageFileList() {
        return validMessageFileList;
    }

    public ArrayList<File> getNotValidMessageFileList() {
        return notValidMessageFileList;
    }
}
