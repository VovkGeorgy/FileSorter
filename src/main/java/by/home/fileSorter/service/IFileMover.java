package by.home.fileSorter.service;

import java.io.File;

/**
 * Interface declare methods to moving some files
 */
public interface IFileMover {

    /**
     * Method move sorted files in local or remote storage
     *
     * @param isValid
     * @param file
     */
    boolean moveFile(boolean isValid, File file);
}
