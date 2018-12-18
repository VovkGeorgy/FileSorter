package by.home.fileSorter.service.file;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Class realise method whom get files by extension
 */
@Service
public class FileSeparator {

    @Value("${not.sorted.folder.path}")
    private String notSortedFolderPath;

    @Value("${max.read.words}")
    private int maxReadWords;

    private static final Logger LOGGER = LoggerFactory.getLogger(FileSeparator.class);

    /**
     * Method getFilesByExtension input files with input extension
     *
     * @param extension extension for separating
     * @return sorted with extension files
     */
    public List<File> getFilesByExtension(String extension) {
        LOGGER.info("Separating not sorted files with extension");
        Iterator<File> it = FileUtils.iterateFiles(new File(notSortedFolderPath), new String[]{extension}, false);
        try {
//            ArrayList<File> receivedFiles = IntStream.range(0, maxReadWords).mapToObj(words -> it.next()).collect(Collectors
//                    .toCollection(ArrayList::new));
            ArrayList<File> receivedFiles = new ArrayList<>();
            for (int words = 0; words < maxReadWords; words++) {
                if (it.hasNext()) receivedFiles.add(it.next());
                else break;
            }
            LOGGER.debug("Separated files {} with extension {}", receivedFiles, extension);
            return receivedFiles;
        } catch (NoSuchElementException ex) {
            LOGGER.error("There is no file with extension {}", extension);
            return new ArrayList<>();
        }
    }
}
