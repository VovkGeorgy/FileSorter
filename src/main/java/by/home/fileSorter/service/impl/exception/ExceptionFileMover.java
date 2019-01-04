package by.home.fileSorter.service.impl.exception;

import by.home.fileSorter.service.IFileMover;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * Class realise method which move file
 */
@Slf4j
@Service
public class ExceptionFileMover implements IFileMover {

    @Value("${exception.valid.folder.path}")
    private String validToFolder;

    @Value("${exception.not.valid.folder.path}")
    private String notValidToFolder;

    @Value("${input.folder.path}")
    private String fromFolder;

    @Override
    public boolean moveFile(File file, boolean isValid) {
        return isValid ? move(file, validToFolder) : move(file, notValidToFolder);
    }

    private boolean move(File file, String toFolder) {
        String fromPath = fromFolder + file.getName();
        String toPath = toFolder + file.getName();
        try {
            log.debug("Try to move file {}, to {}", fromPath, toPath);
            Files.move(Paths.get(fromPath), Paths.get(toPath), REPLACE_EXISTING);
        } catch (IOException e) {
            log.error("Get exception with moving files from {}, to {}", fromPath, toPath);
            return false;
        }
        return true;
    }
}