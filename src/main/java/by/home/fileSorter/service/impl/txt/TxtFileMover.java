package by.home.fileSorter.service.impl.txt;

import by.home.fileSorter.service.IFileMover;
import by.home.fileSorter.utils.PropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Class realise method which move JSON file from local folder to sftp server
 */
public class TxtFileMover implements IFileMover {

    @Autowired
    private PropertiesUtils propertiesUtils;

    private static final Logger LOGGER = LoggerFactory.getLogger(TxtFileMover.class);

    @Override
    public void moveFiles(List<File> validFileList, List<File> notValidFileList) {
        LOGGER.info("Get config for moving files");
        String validFolder = propertiesUtils.getTxtFolderPath();
        String notValidFolder = propertiesUtils.getTxtNotValidFolderPath();
        String notSortedFolderPath = propertiesUtils.getNotSortedFolderPath();
        LOGGER.debug("Get config from properties");
        move(validFileList, notSortedFolderPath, validFolder);
        move(notValidFileList, notSortedFolderPath, notValidFolder);
    }

    private void move(List<File> fileList, String fromFolder, String toFolder) {
        LOGGER.info("Try to moving files");
        for (File file : fileList) {
            String fromPath = fromFolder + file.getName();
            String toPath = toFolder + file.getName();
            try {
                LOGGER.debug("Try to move file {}, to {}", fromPath, toPath);
                Files.move(Paths.get(fromPath), Paths.get(toPath));
            } catch (IOException e) {
                LOGGER.error("Get exception with moving files from {}, to {}", fromPath, toPath);
            }
        }
    }
}
