package by.home.fileSorter.service.impl.json;

import by.home.fileSorter.entity.ErrorMessage;
import by.home.fileSorter.service.IFIleValidator;
import by.home.fileSorter.service.impl.ErrorMessageBuilder;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class validate JSON file, and save it in List
 */
@Service
public class JsonFileValidator implements IFIleValidator {

    private ArrayList<File> notValidMessageFileList = new ArrayList<>();
    private ArrayList<ErrorMessage> validMessageList = new ArrayList<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonFileValidator.class);

    @Autowired
    private ErrorMessageBuilder errorMessageBuilder;

    @Override
    public void valid(String message, File messageFile) {
        if (isValidFile(message, messageFile)) {
            ErrorMessage errorMessage = errorMessageBuilder.build(message);
            LOGGER.info("Try to check a object valid");
            if (isValidObject(errorMessage)) {
                errorMessage.setFileName(messageFile.getName());
                validMessageList.add(errorMessage);
                LOGGER.debug("Object {}, is valid", errorMessage);
            } else notValidMessageFileList.add(messageFile);
        } else notValidMessageFileList.add(messageFile);
    }

    private boolean isValidFile(String message, File messageFile) {
        try {
            LOGGER.info("Try to check a file valid");
            ObjectMapper mapper = new ObjectMapper();
            ErrorMessage err = mapper.readValue(message, ErrorMessage.class);
            LOGGER.debug("File {}, is valid", messageFile.getPath());
            return true;
        } catch (IOException e) {
            LOGGER.error("Get not valid JSON {}", messageFile.getPath());
            return false;
        }
    }

    private boolean isValidObject(ErrorMessage errorMessage) {
        if (errorMessage == null || errorMessage.getMessageType().isEmpty() || errorMessage.getId() == 0) return false;
        else return true;
    }

    public ArrayList<File> getNotValidMessageFileList() {
        return notValidMessageFileList;
    }

    public ArrayList<ErrorMessage> getValidMessageList() {
        return validMessageList;
    }
}
