package by.home.fileSorter.service;

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
    public void runSorter() {
        List<File> files = fileManager.getFilesByExtensions();
        if (files.isEmpty()) return;
        files.forEach(file -> fileServiceFactory.getFileService(file).process(file));
    }
}
