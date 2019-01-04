package by.home.fileSorter.service;

import by.home.fileSorter.entity.AbstractMessage;
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

    private final ReportManager reportManager;
    private final ReportParserFactory reportParserFactory;
    private final MessageServiceFactory messageServiceFactory;

    @Autowired
    public SorterRunnerService(ReportManager reportManager, ReportParserFactory reportParserFactory, MessageServiceFactory
            messageServiceFactory) {
        this.reportManager = reportManager;
        this.reportParserFactory = reportParserFactory;
        this.messageServiceFactory = messageServiceFactory;
    }

    /**
     * Method exist all program functions, to step by step working, and repeat evey 5 sec
     */
    @Scheduled(fixedDelayString = "${scan.delay}")
    public void runSorter() {
        List<File> files = reportManager.getFilesByExtensions();
        if (files.isEmpty()) return;
        files.forEach(file -> {
            AbstractMessage message = reportParserFactory.getParser(file).parseFile(file);
            IProcessingService service = messageServiceFactory.getMessageService(message);
            service.process(message);
        });
    }
}
