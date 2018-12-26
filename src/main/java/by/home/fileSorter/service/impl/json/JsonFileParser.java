package by.home.fileSorter.service.impl.json;

import by.home.fileSorter.service.IFileParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Json file parser class
 */
@Service
@Slf4j
public class JsonFileParser implements IFileParser {

    @Override
    public String getMessage(List<String> linesList) {
        log.debug("Parse input file lines {}", linesList);
        StringBuilder fullJsonStringBuilder = new StringBuilder("");
        linesList.forEach(fullJsonStringBuilder::append);
        String fullStringOfJsonFile = fullJsonStringBuilder.toString();
        log.debug("Get full string from file string list {}", fullStringOfJsonFile);
        return fullStringOfJsonFile;
    }
}
