package by.home.fileSorter.service;

import java.io.File;

/**
 * Interface declare methods to moving some files
 */
public interface IFileMover {

    /**
     * Method move sorted files in local or remote storage
     *
     * @param file file which move
     * @param isValid file validity
     */
    boolean moveFile(File file, boolean isValid);
}
