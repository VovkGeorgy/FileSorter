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
     * Method get Files By Extensions
     *
     * @return files
     */
    public List<File> getFilesByExtension() {
        Iterator<File> it = FileUtils.iterateFiles(new File(fromFolder), filesExtensions, false);
        try {
            ArrayList<File> receivedFiles = new ArrayList<>();
            for (int files = 0; files < maxReadFiles; files++) {
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

    /**
     * Method read input file
     *
     * @param file input file
     * @return all lines from file in List
     */
    public List<String> readFile(File file) {
        log.info("Try to read file {}", file.getName());
        try {
            log.debug("Try to read file from path {}", file.getPath());
            return Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.error("Cant read file from path {}, get exception []", file.getPath(), e.getMessage());
        }
        return new ArrayList<>();
    }
}
