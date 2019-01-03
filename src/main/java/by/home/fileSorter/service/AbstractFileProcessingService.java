package by.home.fileSorter.service;

import java.io.File;

/**
 * Abstract file processing service for extending by others file processing services
 */
public abstract class AbstractFileProcessingService {
    /**
     * Method processing handling of file
     *
     * @param file input file
     */
    public abstract void process(File file);
}
