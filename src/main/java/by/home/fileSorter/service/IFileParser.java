package by.home.fileSorter.service;

import java.util.ArrayList;
import java.util.List;

/**
 * Declare method from parsers
 */
public interface IFileParser {

    /**
     * Method parse list of file lines
     *
     * @param linesList list of file lines
     * @return list of parsed lines
     */
    ArrayList<String[]> getMessages(List<String> linesList);
}
