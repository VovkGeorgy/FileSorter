package by.home.fileSorter.service.file.impl;

import by.home.fileSorter.service.file.IFileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * Class realise files service methods
 */
@Slf4j
@Service
public class LocalFileService implements IFileService {

    @Override
    public boolean moveFile(File file, String inputPath, String outputPath) {
        try {
            log.debug("Try to move file {}, to {}", inputPath, outputPath);
            Files.move(Paths.get(inputPath), Paths.get(outputPath), REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Get exception\n {} \nwith moving files from {}, to {}", e.getLocalizedMessage(), inputPath, outputPath);
            return false;
        }
        return true;
    }

    /**
     * Method get list of files By extensions
     *
     * @return files
     */
    public List<File> getFilesByExtensions(String inputFolder, String[] filesExtensions, int maxReadFiles) {
        Iterator<File> it = FileUtils.iterateFiles(new File(inputFolder), filesExtensions, false);
        log.info("Scan files in folder {}", inputFolder);
        ArrayList<File> receivedFiles = new ArrayList<>();
        log.debug("Get {} files by {} extensions", receivedFiles.size(), filesExtensions.length);
        for (int filesCount = 0; filesCount < maxReadFiles; filesCount++) {
            if (it.hasNext()) receivedFiles.add(it.next());
            else break;
        }
        return receivedFiles;
    }
}