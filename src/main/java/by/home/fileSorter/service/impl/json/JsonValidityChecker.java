package by.home.fileSorter.service.impl.json;

import by.home.fileSorter.entity.ErrorMessage;
import by.home.fileSorter.service.IValidityChecker;
import by.home.fileSorter.service.impl.ErrorMessageBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * Class check validity of JSON file and object in it
 */
@Slf4j
@Service
public class JsonValidityChecker implements IValidityChecker {

    private final ErrorMessageBuilder errorMessageBuilder;

    @Autowired
    public JsonValidityChecker(ErrorMessageBuilder errorMessageBuilder) {
        this.errorMessageBuilder = errorMessageBuilder;
    }

    @Override
    public boolean isValid(String messageString, File messageFile) {
        if (isValidFile(messageString, messageFile)) {
            ErrorMessage errorMessage = errorMessageBuilder.build(messageString);
            if (isValidObject(errorMessage)) {
                errorMessage.setFileName(messageFile.getName());
                log.debug("Object {}, is isValid", errorMessage);
                return true;
            } else return false;
        } else return false;
    }

    private boolean isValidFile(String message, File messageFile) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.readValue(message, ErrorMessage.class);
            log.debug("File {}, is isValid", messageFile.getPath());
            return true;
        } catch (IOException e) {
            log.error("Get not isValid JSON {}", messageFile.getPath());
            return false;
        }
    }

    private boolean isValidObject(ErrorMessage errorMessage) {
        return errorMessage != null && !errorMessage.getMessageType().isEmpty() && errorMessage.getId() != 0;
    }
}
