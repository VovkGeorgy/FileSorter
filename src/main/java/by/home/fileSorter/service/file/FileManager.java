package by.home.fileSorter.service.file;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Class hold methods with primitive operations with files
 */
@Slf4j
@Service
public class FileManager {

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
    public List<File> getFilesByExtensions() {
        Iterator<File> it = FileUtils.iterateFiles(new File(fromFolder), filesExtensions, false);
        log.debug("Get files from folder", fromFolder);
        ArrayList<File> receivedFiles = new ArrayList<>();
        try {
            for (int filesCount = 0; filesCount < maxReadFiles; filesCount++) {
                if (it.hasNext()) receivedFiles.add(it.next());
                else break;
            }
        } catch (NoSuchElementException ex) {
            log.error("There is {}, no file with extensions {}", fromFolder, filesExtensions);
        }
        return receivedFiles;
    }

    /**
     * Method read input file
     *
     * @param file input file
     * @return all lines from file in List
     */
    public List<String> readFile(File file) {
        log.debug("Try to read file {}", file.getName());
        try {
            log.debug("Try to read file from path {}", file.getPath());
            return Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.error("Cant read file from path {}, get exception []", file.getPath(), e.getMessage());
        }
        return new ArrayList<>();
    }
}
