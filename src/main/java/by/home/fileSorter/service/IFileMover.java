package by.home.fileSorter.service;

import java.io.File;
import java.util.List;

/**
 * Interface declare methods to moving some files
 */
public interface IFileMover<T> {

    /**
     * Method move sorted files in local or remote storage
     *
     * @param validObjectsList list of valid objects
     * @param notValidFileList list of not valid files
     */
    void moveFiles(T validObjectsList, List<File> notValidFileList);
}
