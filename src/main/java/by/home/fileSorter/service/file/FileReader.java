package by.home.fileSorter.service.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Class realise method whom read input file
 */
@Service
@Slf4j
public class FileReader {

    /**
     * Method read input file
     *
     * @param file input file
     * @return all lines from file in List
     */
    public List<String> readFile(File file) {
        log.info("Try to read file");
        try {
            log.debug("Try to read file from path {}", file.getPath());
            return Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.error("Cant read file from path {}, get exception []", file.getPath(), e.getMessage());
        }
        return new ArrayList<>();
    }
}