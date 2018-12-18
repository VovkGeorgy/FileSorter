package by.home.fileSorter.service.impl.txt;

import by.home.fileSorter.entity.ExceptionMessage;
import by.home.fileSorter.service.IFIleValidator;
import by.home.fileSorter.service.impl.ExceptionMessageBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * TXT file validator class
 */
@Service
public class TxtFileValidator implements IFIleValidator {

    @Value("${txt.fields.splitter}")
    private String txtFieldsSplitter;

    private ArrayList<File> notValidMessageFileList = new ArrayList<>();
    private ArrayList<ExceptionMessage> validMessageList = new ArrayList<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(TxtFileValidator.class);

    @Autowired
    private ExceptionMessageBuilder exceptionMessageBuilder;

    @Override
    public void valid(String message, File messageFile) {
        LOGGER.info("Check txt file validity");
        List<String> fieldList = Arrays.asList(message.split(txtFieldsSplitter));
        if (validateFile(fieldList)) {
            ExceptionMessage exceptionMessage = exceptionMessageBuilder.build(message);
            LOGGER.debug("File {} is valid", messageFile.getName());
            if (validateObject(exceptionMessage)) {
                exceptionMessage.setFileName(messageFile.getName());
                validMessageList.add(exceptionMessage);
            } else notValidMessageFileList.add(messageFile);
        } else notValidMessageFileList.add(messageFile);
    }

    private boolean validateObject(ExceptionMessage exceptionMessage) {
        if(exceptionMessage == null || exceptionMessage.getMessageType().isEmpty() || exceptionMessage.getId() == 0) return false;
        else return true;
    }

    private boolean validateFile(List<String> fieldList) {
        return fieldList.size() == 5;
    }

    @Override
    public ArrayList getValidMessageList() {
        return validMessageList;
    }

    @Override
    public ArrayList<File> getNotValidMessageFileList() {
        return notValidMessageFileList;
    }
}
