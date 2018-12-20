package by.home.fileSorter.service.impl;

import by.home.fileSorter.entity.ErrorMessage;
import by.home.fileSorter.service.IBuilder;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Class for Error Message instance building
 */
@Slf4j
@Service
public class ErrorMessageBuilder implements IBuilder<ErrorMessage, String> {

    @Override
    public ErrorMessage build(String message) {
        log.info("Try to build a Error Message entity, from JSON file");
        ObjectMapper mapper = new ObjectMapper();
        try {
            return (mapper.readValue(message, new TypeReference<ErrorMessage>() {
            }));
        } catch (IOException e) {
            log.error("Get not isValid JSON string {}, error {}", message, e.getMessage());
            return null;
        }
    }
}
