package by.home.fileSorter.service.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Class realise method whom read file by input path
 */
public class FileReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileReader.class);

    /**
     * Method read file from input path
     *
     * @param filePath file input path
     * @return all lines from file in List
     */
    public List<String> readFile(String filePath) {
        LOGGER.info("Try to read file");
        try {
            LOGGER.debug("Try to read file from path {}", filePath);
            return Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            LOGGER.error("Cant read file from path {}, get exception []", filePath, e.getMessage());
            System.out.println("Ops" + e.getMessage());
        }
        return null;
    }
}