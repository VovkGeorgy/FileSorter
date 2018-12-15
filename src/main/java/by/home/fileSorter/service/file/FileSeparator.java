package by.home.fileSorter.service.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class realise method whom separate input files with input template
 */
public class FileSeparator {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileSeparator.class);

    /**
     * Method separate input files with input template
     *
     * @param notSortedFiles list of not sorted files
     * @param template       template for separating
     * @return sorted with template files
     */
    public List<File> separate(List<File> notSortedFiles, String template) {
        LOGGER.info("Separating not sorted files with template");
        notSortedFiles = notSortedFiles.stream().filter(file -> file.getName().endsWith(template)).collect(Collectors.toList());
        LOGGER.debug("Separated files {} with template {}", notSortedFiles, template);
        if (notSortedFiles.isEmpty()) return null;
        return notSortedFiles;
    }
}
