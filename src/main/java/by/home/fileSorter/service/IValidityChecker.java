package by.home.fileSorter.service;

import java.io.File;

/**
 * Interface declare methods for file validity checkers
 */
public interface IValidityChecker {

    /**
     * Method get parsed message, and file for checking validity
     *
     * @param message     string message
     * @param messageFile file of message
     * @return result of validity checking
     */
    boolean isValid(String message, File messageFile);
}
