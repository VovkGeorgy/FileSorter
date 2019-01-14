package by.home.fileSorter.service.report;

import by.home.fileSorter.entity.AbstractMessage;
import by.home.fileSorter.entity.ExceptionMessage;
import by.home.fileSorter.service.report.impl.ErrorReportProcessingService;
import by.home.fileSorter.service.report.impl.ExceptionReportProcessingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class realise factory of processing services
 */
@Slf4j
@Service
public class ReportServiceFactory {
    private final ExceptionReportProcessingService exceptionProcessingService;
    private final ErrorReportProcessingService errorProcessingService;

    @Autowired
    public ReportServiceFactory(ExceptionReportProcessingService exceptionProcessingService, ErrorReportProcessingService
            errorProcessingService) {
        this.exceptionProcessingService = exceptionProcessingService;
        this.errorProcessingService = errorProcessingService;
    }

    /**
     * Method return a service instance depending of message instance
     *
     * @param message message entity
     * @return message processing service for input message
     */
    public IReportProcessingService getMessageService(AbstractMessage message) {
        return (message instanceof ExceptionMessage) ? exceptionProcessingService : errorProcessingService;
    }
}
