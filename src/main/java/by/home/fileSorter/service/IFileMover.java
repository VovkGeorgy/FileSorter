package by.home.fileSorter.service;

import java.io.File;

/**
 * Interface declare method to moving file
 */
public interface IFileMover {

    /**
     * Method move sorted file in local or remote storage
     *
     * @param file    file which move
     * @param isValid file validity
     */
    boolean moveFile(File file, boolean isValid);
}
