package by.home.fileSorter.service.file;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Class realise method whom get files by extensions
 */
@Slf4j
@Service
public class FileGetter {

    @Value("${not.sorted.folder.path}")
    private String fromFolder;

    @Value("${max.read.words}")
    private int maxReadWords;

    @Value("${array.of.extensions}")
    private String[] filesExtensions;

    /**
     * Method get Files By Extensions
     *
     * @return files
     */
    public List<File> getFilesByExtension() {
        log.info("Separating not sorted files with extension");
        Iterator<File> it = FileUtils.iterateFiles(new File(fromFolder), filesExtensions, false);
        try {
            ArrayList<File> receivedFiles = new ArrayList<>();
            for (int words = 0; words < maxReadWords; words++) {
                if (it.hasNext()) receivedFiles.add(it.next());
                else break;
            }
            log.debug("Get files {} with extensions {}", receivedFiles, filesExtensions);
            return receivedFiles;
        } catch (NoSuchElementException ex) {
            log.error("There is {}, no file with extensions {}", fromFolder, filesExtensions);
            return new ArrayList<>();
        }
    }
}
