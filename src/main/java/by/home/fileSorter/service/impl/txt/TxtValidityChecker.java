package by.home.fileSorter.service.impl.txt;

import by.home.fileSorter.entity.ExceptionMessage;
import by.home.fileSorter.service.IValidityChecker;
import by.home.fileSorter.service.impl.ExceptionMessageBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Class check validity of txt file and object in it
 */
@Slf4j
@Service
public class TxtValidityChecker implements IValidityChecker {

    @Value("${txt.fields.splitter}")
    private String txtFieldsSplitter;

    @Value("${txt.fields.consist}")
    private int txtFieldsConsist;

    private final ExceptionMessageBuilder exceptionMessageBuilder;
    private ExceptionMessage exceptionMessage;

    @Autowired
    public TxtValidityChecker(ExceptionMessageBuilder exceptionMessageBuilder) {
        this.exceptionMessageBuilder = exceptionMessageBuilder;
    }

    @Override
    public boolean isValid(String message, File messageFile) {
        log.info("Check txt file validity");
        List<String> fieldList = Arrays.asList(message.split(txtFieldsSplitter));
        return (isValidFile(fieldList, message)) && isValidObject(exceptionMessage);
    }

    private boolean isValidObject(ExceptionMessage exceptionMessage) {
        return exceptionMessage != null && !exceptionMessage.getMessageType().isEmpty() && exceptionMessage.getId() != null;
    }

    private boolean isValidFile(List<String> fieldList, String message) {
        if (fieldList.size() == txtFieldsConsist) {
            exceptionMessage = exceptionMessageBuilder.build(message);
            return true;
        }
        return false;
    }
}

