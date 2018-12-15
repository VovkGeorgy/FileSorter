package by.home.fileSorter.service.impl;

import by.home.fileSorter.service.ApplicationContextProvider;
import by.home.fileSorter.entity.ExceptionMessage;
import by.home.fileSorter.service.IMessageBuilder;
import by.home.fileSorter.service.impl.txt.TxtFileValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for Exception Message instance building
 */
public class ExceptionMessageBuilder implements IMessageBuilder<List<ExceptionMessage>> {

    @Autowired
    private ApplicationContextProvider applicationContextProvider;

    @Autowired
    private TxtFileValidator txtFileValidator;

    private List<ExceptionMessage> exceptionMessagesList = new ArrayList<>();
    private List<List<ExceptionMessage>> exceptionMessagesFullList = new ArrayList<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionMessageBuilder.class);

    @Override
    public List<List<ExceptionMessage>> build() {
        LOGGER.info("Try to build a Error Message entity, from TXT file");
        ArrayList<String[]> listOfMessages = txtFileValidator.getValidMessages();
        listOfMessages.forEach(line -> {
                    ExceptionMessage exceptionMessage = applicationContextProvider.getApplicationContext().getBean(ExceptionMessage.class);
                    exceptionMessage.setId(Long.parseLong(line[0]));
                    exceptionMessage.setMessage(line[1]);
                    exceptionMessage.setTypeOfException(line[2]);
                    exceptionMessage.setThrownClass(line[3]);
                    exceptionMessage.setThrownMethod(line[4]);
                    exceptionMessage.setThrownLine(Integer.parseInt(line[5]));
                    exceptionMessagesList.add(exceptionMessage);
                    LOGGER.debug("Build exception message entity {}", exceptionMessage);
                }
        );
        exceptionMessagesFullList.add(exceptionMessagesList);
        return exceptionMessagesFullList;
    }
}
