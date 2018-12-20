package by.home.fileSorter.service.impl.txt;

import by.home.fileSorter.service.IFileMover;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Class realise method which move file
 */
@Slf4j
@Service
public class TxtFileMover implements IFileMover {

    @Value("${txt.valid.folder.path}")
    private String validToFolder;

    @Value("${txt.not.valid.folder.path}")
    private String notValidToFolder;

    @Value("${not.sorted.folder.path}")
    private String fromFolder;

    @Override
    public boolean moveFile(boolean isValid, File file) {
        return isValid ? move(file, validToFolder) : move(file, notValidToFolder);
    }

    private boolean move(File file, String toFolder) {
        log.info("Try to moving files");
        String fromPath = fromFolder + file.getName();
        String toPath = toFolder + file.getName();
        try {
            log.debug("Try to move file {}, to {}", fromPath, toPath);
            Files.move(Paths.get(fromPath), Paths.get(toPath));
        } catch (IOException e) {
            log.error("Get exception with moving files from {}, to {}", fromPath, toPath);
            return false;
        }
        return true;
    }
}