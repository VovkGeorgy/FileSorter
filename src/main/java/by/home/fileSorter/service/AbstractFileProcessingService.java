package by.home.fileSorter.service;

import java.io.File;

/**
 * Abstract file processing service for extending by others file processing services
 */
public abstract class AbstractFileProcessingService {
    /**
     * Method processing handling of json file
     *
     * @param file input json file
     */
    public abstract void process(File file);
}
