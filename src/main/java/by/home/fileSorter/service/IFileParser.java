package by.home.fileSorter.service;

import java.util.List;

/**
 * Declare method for parsers
 */
public interface IFileParser {

    /**
     * Method parse list of file lines
     *
     * @param linesList list of file lines
     * @return parsed line
     */
    String getMessage(List<String> linesList);
}
