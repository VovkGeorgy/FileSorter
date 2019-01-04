package by.home.fileSorter.service;

import by.home.fileSorter.entity.AbstractMessage;
import by.home.fileSorter.entity.ExceptionMessage;
import by.home.fileSorter.service.impl.error.ErrorProcessingService;
import by.home.fileSorter.service.impl.exception.ExceptionProcessingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class realise factory of processing services
 */
@Slf4j
@Service
public class MessageServiceFactory {
    private final ExceptionProcessingService exceptionProcessingService;
    private final ErrorProcessingService errorProcessingService;

    @Autowired
    public MessageServiceFactory(ExceptionProcessingService exceptionProcessingService, ErrorProcessingService
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
    IProcessingService getMessageService(AbstractMessage message) {
        return (message instanceof ExceptionMessage) ? exceptionProcessingService : errorProcessingService;
    }
}
