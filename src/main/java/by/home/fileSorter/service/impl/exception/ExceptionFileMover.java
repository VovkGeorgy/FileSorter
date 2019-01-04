package by.home.fileSorter.service.impl.exception;

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
public class ExceptionFileMover implements IFileMover {

    @Value("${exception.valid.folder.path}")
    private String validOutputFolder;

    @Value("${exception.not.valid.folder.path}")
    private String notValidOutputFolder;

    @Value("${input.folder.path}")
    private String inputFolder;

    @Override
    public boolean moveFile(File file, boolean isValid) {
        return isValid ? move(file, validOutputFolder) : move(file, notValidOutputFolder);
    }

    private boolean move(File file, String outFolder) {
        String fromPath = inputFolder + file.getName();
        String outPath = outFolder + file.getName();
        try {
            log.debug("Try to move file {}, to {}", fromPath, outPath);
            Files.move(Paths.get(fromPath), Paths.get(outPath));
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Get exception\n {} \nwith moving files from {}, to {}", e.getLocalizedMessage(), fromPath, outPath);
            return false;
        }
        return true;
    }
}