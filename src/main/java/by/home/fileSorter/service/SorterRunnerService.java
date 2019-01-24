package by.home.fileSorter.service;

import by.home.fileSorter.entity.AbstractMessage;
import by.home.fileSorter.service.file.impl.LocalFileService;
import by.home.fileSorter.service.report.ReportParserFactory;
import by.home.fileSorter.service.report.ReportServiceFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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
    private final ReportServiceFactory reportServiceFactory;

    @Value("${input.folder.path}")
    private String inputFolder;

    @Value("${max.read.files}")
    private int maxReadFiles;

    @Value("#{'${list.of.error.report.extensions}'.split(',')}")
    private List<String> errorExtensions;

    @Value("#{'${list.of.exception.report.extensions}'.split(',')}")
    private List<String> exceptionExtensions;

    private String[] filesExtensions;

    @PostConstruct
    private void getFilesExtensions(){
        errorExtensions.addAll(exceptionExtensions);
        filesExtensions = (String[]) errorExtensions.toArray();
    }


    @Autowired
    public SorterRunnerService(LocalFileService localFileService, ReportParserFactory reportParserFactory, ReportServiceFactory
            reportServiceFactory) {
        this.localFileService = localFileService;
        this.reportParserFactory = reportParserFactory;
        this.reportServiceFactory = reportServiceFactory;
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
            boolean result = reportServiceFactory.getService(message).process(message);
            log.debug("File {} is processed  - {}", file.getName(), result);
        });
    }
}
