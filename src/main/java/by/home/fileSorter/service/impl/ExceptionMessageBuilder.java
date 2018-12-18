package by.home.fileSorter.service.impl;

import by.home.fileSorter.entity.ExceptionMessage;
import by.home.fileSorter.service.IMessageBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Class for Exception Message instance building
 */
@Service
public class ExceptionMessageBuilder implements IMessageBuilder<ExceptionMessage> {

    @Value("${txt.fields.splitter}")
    private String txtFieldsSplitter;

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionMessageBuilder.class);

    @Override
    public ExceptionMessage build(String message) {
        LOGGER.info("Try to build a Error Message entity, from TXT file");
        List<String> fieldList = Arrays.asList(message.split(txtFieldsSplitter));
        ExceptionMessage exceptionMessage = new ExceptionMessage();
        exceptionMessage.setMessageType(fieldList.get(0));
        exceptionMessage.setId(Long.parseLong(fieldList.get(1)));
        exceptionMessage.setMessage(fieldList.get(2));
        exceptionMessage.setTypeOfException(fieldList.get(3));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        exceptionMessage.setThrowingTime(LocalDate.parse(fieldList.get(4), formatter));
        LOGGER.debug("Build exception message entity {}", exceptionMessage);
        return exceptionMessage;
    }
}
