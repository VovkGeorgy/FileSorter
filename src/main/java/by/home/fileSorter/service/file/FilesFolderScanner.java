package by.home.fileSorter.service.file;

import by.home.fileSorter.utils.PropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Class realise method which scan folder, and get all files
 */
public class FilesFolderScanner {

    @Autowired
    private PropertiesUtils propertiesUtils;

    private static final Logger LOGGER = LoggerFactory.getLogger(FilesFolderScanner.class);

    /**
     * Method scanning folder for files
     *
     * @return all files in folder path
     */
    public List<File> scan() {
        LOGGER.info("Scan folder for files");
        File folder = new File(propertiesUtils.getNotSortedFolderPath());
        List<File> notSortedFiles = Arrays.asList(folder.listFiles());
        LOGGER.debug("Scan folder {} for files {}", folder.getPath(), notSortedFiles);
        if (notSortedFiles.isEmpty()) return null;
        return notSortedFiles;
    }
}
