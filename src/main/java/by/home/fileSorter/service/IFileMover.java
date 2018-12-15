package by.home.fileSorter.service;

import java.io.File;
import java.util.List;

/**
 * Interface declare methods to moving some files
 */
public interface IFileMover {

    /**
     * Method move sorted files in local or remote storage
     *
     * @param validFileList    list of valid files
     * @param notValidFileList list of not valid files
     */
    void moveFiles(List<File> validFileList, List<File> notValidFileList);
}
