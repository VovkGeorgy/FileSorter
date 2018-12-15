package by.home.fileSorter.service.impl.txt;

import by.home.fileSorter.service.IFIleValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

/**
 * TXT file validator class
 */
public class TxtFileValidator implements IFIleValidator {

    private ArrayList<String[]> validMessages = new ArrayList<>();
    private ArrayList<String[]> notValidMessages = new ArrayList<>();
    private ArrayList<File> validMessageFileList = new ArrayList<>();
    private ArrayList<File> notValidMessageFileList = new ArrayList<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(TxtFileValidator.class);


    @Override
    public void valid(ArrayList<String[]> listOfMessages, File messageFile) {
        LOGGER.info("Check txt file validity");
        listOfMessages.forEach(fieldArray -> {
            if (isIdNotNull(fieldArray) && isAllFieldsExist(fieldArray)) {
                validMessages.add(fieldArray);
                validMessageFileList.add(messageFile);
                LOGGER.debug("File {} is valid", messageFile.getName());
            } else {
                notValidMessages.add(fieldArray);
                notValidMessageFileList.add(messageFile);
                LOGGER.debug("File {} is NOT valid", messageFile.getName());
            }
        });
    }

    private boolean isIdNotNull(String[] fieldArray) {
        LOGGER.info("Check that is field ID in message is not NULL");
        return fieldArray[0] != null && !Objects.equals(fieldArray[0], "");
    }

    private boolean isAllFieldsExist(String[] fieldArray) {
        LOGGER.info("Check that is all field in file exist");
        return fieldArray.length == 6;
    }

    public ArrayList<String[]> getValidMessages() {
        return validMessages;
    }

    public ArrayList<String[]> getNotValidMessages() {
        return notValidMessages;
    }

    public ArrayList<File> getValidMessageFileList() {
        return validMessageFileList;
    }

    public ArrayList<File> getNotValidMessageFileList() {
        return notValidMessageFileList;
    }
}
