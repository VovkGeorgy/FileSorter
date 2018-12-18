package by.home.fileSorter.service;

import java.io.File;
import java.util.ArrayList;

/**
 * Interface declare methods for file validators
 */
public interface IFIleValidator<T> {

    /**
     * Method get parsed list of messages, and file for it validation and save
     *
     * @param message     list of parsed messages
     * @param messageFile file of messages
     */
    void valid(String message, File messageFile);

    ArrayList<T> getValidMessageList();

    ArrayList<File> getNotValidMessageFileList();
}
