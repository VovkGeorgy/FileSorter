package by.home.fileSorter.service.impl.txt;

import by.home.fileSorter.service.IFileParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TXT file parser class
 */
@Service
public class TxtFileParser implements IFileParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(TxtFileParser.class);

    @Override
    public String getMessage(List<String> linesList) {
        LOGGER.info("Start parsing txt file");
        StringBuilder fullTxtStringBuilder = new StringBuilder("");
        linesList.forEach(fullTxtStringBuilder::append);
        String fullStringOfTxtFile = fullTxtStringBuilder.toString();
        LOGGER.debug("Parse txt file {}", fullStringOfTxtFile);
        return fullStringOfTxtFile;
    }
}
