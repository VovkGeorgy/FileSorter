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

    private final ExceptionMessageBuilder exceptionMessageBuilder;

    @Autowired
    public TxtValidityChecker(ExceptionMessageBuilder exceptionMessageBuilder) {
        this.exceptionMessageBuilder = exceptionMessageBuilder;
    }

    @Override
    public boolean isValid(String message, File messageFile) {
        log.info("Check txt file validity");
        List<String> fieldList = Arrays.asList(message.split(txtFieldsSplitter));
        if (isValidFile(fieldList)) {
            ExceptionMessage exceptionMessage = exceptionMessageBuilder.build(message);
            log.debug("File {} is isValid", messageFile.getName());
            if (isValidObject(exceptionMessage)) {
                exceptionMessage.setFileName(messageFile.getName());
                return true;
            } else return false;
        } else return false;
    }

    private boolean isValidObject(ExceptionMessage exceptionMessage) {
        return exceptionMessage != null && !exceptionMessage.getMessageType().isEmpty() && exceptionMessage.getId() != 0;
    }

    private boolean isValidFile(List<String> fieldList) {
        return fieldList.size() == 5;
    }
}
