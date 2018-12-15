package by.home.fileSorter.service;

import java.io.File;
import java.util.ArrayList;

/**
 * Interface declare methods for file validators
 */
public interface IFIleValidator {

    /**
     * Method get parsed list of messages, and file for it validation and save
     *
     * @param listOfMessages list of parsed messages
     * @param messageFile    file of messages
     */
    void valid(ArrayList<String[]> listOfMessages, File messageFile);

    ArrayList<File> getValidMessageFileList();

    ArrayList<File> getNotValidMessageFileList();
}
