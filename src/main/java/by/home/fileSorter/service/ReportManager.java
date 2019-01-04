package by.home.fileSorter.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class hold methods with primitive operations with files
 */
@Slf4j
@Service
public class ReportManager {

    @Value("${not.sorted.folder.path}")
    private String fromFolder;

    @Value("${max.read.files}")
    private int maxReadFiles;

    @Value("${array.of.extensions}")
    private String[] filesExtensions;

    /**
     * Method get list of Files By Extensions
     *
     * @return files
     */
    List<File> getFilesByExtensions() {
        Iterator<File> it = FileUtils.iterateFiles(new File(fromFolder), filesExtensions, false);
        log.debug("Get files from folder", fromFolder);
        ArrayList<File> receivedFiles = new ArrayList<>();
        for (int filesCount = 0; filesCount < maxReadFiles; filesCount++) {
            if (it.hasNext()) receivedFiles.add(it.next());
            else break;
        }
        return receivedFiles;
    }
}
