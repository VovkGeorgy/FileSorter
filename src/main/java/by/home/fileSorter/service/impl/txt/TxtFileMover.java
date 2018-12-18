package by.home.fileSorter.service.impl.txt;

import by.home.fileSorter.entity.ExceptionMessage;
import by.home.fileSorter.service.IFileMover;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Class realise method which move JSON file from local folder to sftp server
 */
@Service
public class TxtFileMover implements IFileMover<List<ExceptionMessage>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TxtFileMover.class);

    @Value("${txt.valid.folder.path}")
    private String txtFolderPath;

    @Value("${txt.not.valid.folder.path}")
    private String txtNotValidFolderPath;

    @Value("${not.sorted.folder.path}")
    private String notSortedFolderPath;

    @Override
    public void moveFiles(List<ExceptionMessage> validFileList, List<File> notValidFileList) {
        LOGGER.info("Get config for moving files");
        LOGGER.debug("Get config from properties");
        move(getFilesByObject(validFileList), notSortedFolderPath, txtFolderPath);
        move(notValidFileList, notSortedFolderPath, txtNotValidFolderPath);
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

    private List<File> getFilesByObject(List<ExceptionMessage> validObjectsList) {
        ArrayList<File> validFileList = new ArrayList<>();
        validObjectsList.forEach(exceptionMessage -> validFileList.add(FileUtils.getFile(notSortedFolderPath, exceptionMessage.getFileName())));
        return validFileList;
    }
}