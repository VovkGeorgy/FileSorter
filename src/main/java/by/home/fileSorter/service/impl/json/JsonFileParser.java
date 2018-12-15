package by.home.fileSorter.service.impl.json;

import by.home.fileSorter.service.IFileParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Json file parser class
 */
public class JsonFileParser implements IFileParser {

    private String fullStringOfJsonFile = "";

    public String getFullStringOfJsonFile() {
        return fullStringOfJsonFile;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonFileParser.class);

    @Override
    public ArrayList<String[]> getMessages(List<String> linesList) {
        LOGGER.info("Parse input file lines");
        StringBuilder fullJsonStringBuilder = new StringBuilder("");
        linesList.forEach(fullJsonStringBuilder::append);
        fullStringOfJsonFile = fullJsonStringBuilder.toString();
        LOGGER.debug("Get full string from file string list {}", fullStringOfJsonFile);
        return null;
    }
}
