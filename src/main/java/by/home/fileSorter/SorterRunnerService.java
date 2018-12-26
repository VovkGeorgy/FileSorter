package by.home.fileSorter;

import by.home.fileSorter.service.FileServiceFactory;
import by.home.fileSorter.service.file.FileManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/**
 * Class control program running
 */
@Slf4j
@Component
@EnableScheduling
public class SorterRunnerService {

    private FileManager fileManager;
    private FileServiceFactory fileServiceFactory;

    @Autowired
    public SorterRunnerService(FileManager fileManager, FileServiceFactory fileServiceFactory) {
        this.fileManager = fileManager;
        this.fileServiceFactory = fileServiceFactory;
    }

    /**
     * Method exist all program functions, to step by step working, and repeat evey 5 sec
     */
    @Scheduled(fixedDelayString = "${scan.delay}")
    void runSorter() {
        List<File> files = fileManager.getFilesByExtension();
        if (files.isEmpty()) return;
        log.info("Get new files {}", files);
        files.forEach(file -> fileServiceFactory.getFileService(file).process(file));
    }
}
