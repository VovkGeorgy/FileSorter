package by.home.fileSorter.service;

import by.home.fileSorter.entity.AbstractMessage;
import by.home.fileSorter.service.impl.file.LocalFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    private final LocalFileService localFileService;
    private final ReportParserFactory reportParserFactory;
    private final MessageServiceFactory messageServiceFactory;

    @Value("${input.folder.path}")
    private String inputFolder;

    @Value("${max.read.files}")
    private int maxReadFiles;

    @Value("${array.of.extensions}")
    private String[] filesExtensions;

    @Autowired
    public SorterRunnerService(LocalFileService localFileService, ReportParserFactory reportParserFactory, MessageServiceFactory
            messageServiceFactory) {
        this.localFileService = localFileService;
        this.reportParserFactory = reportParserFactory;
        this.messageServiceFactory = messageServiceFactory;
    }

    /**
     * Method exist all program functions, to step by step working, and repeat evey 3 sec
     */
    @Scheduled(fixedDelayString = "${scan.delay}")
    public void runSorter() {
        List<File> files = localFileService.getFilesByExtensions(inputFolder, filesExtensions, maxReadFiles);
        if (files.isEmpty()) return;
        files.forEach(file -> {
            AbstractMessage message = reportParserFactory.getParser(file).parseFile(file);
            boolean result = messageServiceFactory.getMessageService(message).process(message);
            log.info("File {} is processed  - {}", file.getName(), result);
        });
    }
}
