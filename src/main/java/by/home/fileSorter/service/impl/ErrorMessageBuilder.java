package by.home.fileSorter.service.impl;

import by.home.fileSorter.entity.ErrorMessage;
import by.home.fileSorter.service.IMessageBuilder;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Class for Error Message instance building
 */
@Service
public class ErrorMessageBuilder implements IMessageBuilder<ErrorMessage> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorMessageBuilder.class);

    @Override
    public ErrorMessage build(String message) {
        LOGGER.info("Try to build a Error Message entity, from JSON file");
        ObjectMapper mapper = new ObjectMapper();
        try {
            return (mapper.readValue(message, new TypeReference<ErrorMessage>() {
            }));
        } catch (IOException e) {
            LOGGER.error("Get not valid JSON string {}, error {}", message, e.getMessage());
            return null;
        }
    }
}
