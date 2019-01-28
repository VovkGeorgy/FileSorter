package by.home.fileSorter.service.report;

import by.home.fileSorter.entity.AbstractMessage;
import by.home.fileSorter.entity.ExceptionMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class realise factory of processing services
 */
@Slf4j
@Service
public class ReportServiceFactory {
    private final IReportProcessingService exceptionProcessingService;
    private final IReportProcessingService errorProcessingService;

    @Autowired
    public ReportServiceFactory(IReportProcessingService exceptionReportProcessingService, IReportProcessingService
            errorReportProcessingService) {
        this.exceptionProcessingService = exceptionReportProcessingService;
        this.errorProcessingService = errorReportProcessingService;
    }

    /**
     * Method return a service instance depending of message instance
     *
     * @param message message entity
     * @return message processing service for input message
     */
    public IReportProcessingService getService(AbstractMessage message) {
        log.debug("Get processing service for file {} message", message.getFileName());
        return (message instanceof ExceptionMessage) ? exceptionProcessingService : errorProcessingService;
    }
}
