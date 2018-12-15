package by.home.fileSorter.service.impl;

import by.home.fileSorter.entity.ErrorMessage;
import by.home.fileSorter.service.IMessageBuilder;
import by.home.fileSorter.service.impl.json.JsonFileParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for Error Message instance building
 */
public class ErrorMessageBuilder implements IMessageBuilder<ArrayList<ErrorMessage>> {

    @Autowired
    private JsonFileParser jsonFileParser;

    private List<ArrayList<ErrorMessage>> listOfAllErrorMessages = new ArrayList<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorMessageBuilder.class);

    @Override
    public List<ArrayList<ErrorMessage>> build() {
        LOGGER.info("Try to build a Error Message entity, from JSON file");
        String fullStringOfJsonFile = jsonFileParser.getFullStringOfJsonFile();
        ObjectMapper mapper = new ObjectMapper();
        try {
            listOfAllErrorMessages.add(mapper.readValue(fullStringOfJsonFile, new TypeReference<List<ErrorMessage>>() {
            }));
        } catch (IOException e) {
            LOGGER.error("Get not valid JSON string {}", fullStringOfJsonFile);
        }
        return null;
    }
}
