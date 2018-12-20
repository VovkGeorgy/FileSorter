package by.home.fileSorter;

import by.home.fileSorter.service.FileServiceFactory;
import by.home.fileSorter.service.file.FileGetter;
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

    private FileGetter fileGetter;
    private FileServiceFactory fileServiceFactory;

    @Autowired
    public SorterRunnerService(FileGetter fileGetter, FileServiceFactory fileServiceFactory) {
        this.fileGetter = fileGetter;
        this.fileServiceFactory = fileServiceFactory;
    }

    /**
     * Method exist all program functions, to step by step working, and repeat evey 5 sec
     */
    @Scheduled(fixedDelayString = "${scan.delay}")
    void runSorter() {
        log.info("Program started");
        List<File> files = fileGetter.getFilesByExtension();
        if (files.isEmpty()) return;
        files.forEach(file -> fileServiceFactory.getFileService(file).process(file));
    }
}
