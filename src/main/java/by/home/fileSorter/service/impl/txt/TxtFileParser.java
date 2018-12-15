package by.home.fileSorter.service.impl.txt;

import by.home.fileSorter.service.IFileParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * TXT file parser class
 */
public class TxtFileParser implements IFileParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(TxtFileParser.class);

    @Override
    public ArrayList<String[]> getMessages(List<String> linesList) {
        LOGGER.info("Start parsing txt file");
        ArrayList<String[]> listOfMessages = new ArrayList<>();
        for (String line : linesList) {
            if (line.equals("")) continue;
            listOfMessages.add(line.split(" - "));
        }
        LOGGER.debug("Parse txt file to list {}", listOfMessages);
        return listOfMessages;
    }
}
