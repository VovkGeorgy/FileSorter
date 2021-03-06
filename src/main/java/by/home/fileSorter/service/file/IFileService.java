package by.home.fileSorter.service.file;

import java.io.File;

/**
 * Interface declare method to moving file
 */
public interface IFileService {

    /**
     * Method move sorted file in local or remote storage
     *
     * @param file             file which move
     * @param outputFolderPath output folder path
     */
    boolean moveFile(File file, String outputFolderPath);
}
