package by.home.fileSorter.service.impl.txt;

import by.home.fileSorter.service.IFileParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TXT file parser class
 */
@Slf4j
@Service
public class TxtFileParser implements IFileParser {

    @Override
    public String getMessage(List<String> linesList) {
        log.info("Start parsing txt file");
        StringBuilder fullTxtStringBuilder = new StringBuilder("");
        linesList.forEach(fullTxtStringBuilder::append);
        String fullStringOfTxtFile = fullTxtStringBuilder.toString();
        log.debug("Parse txt file {}", fullStringOfTxtFile);
        return fullStringOfTxtFile;
    }
}
