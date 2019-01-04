package by.home.fileSorter.service.impl.exception;

import by.home.fileSorter.entity.ExceptionMessage;
import by.home.fileSorter.service.IProcessingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Class control services of exception file handling
 */
@Slf4j
@Service
public class ExceptionProcessingService implements IProcessingService<ExceptionMessage> {

    public boolean process(ExceptionMessage exceptionMessage) {
        return false;
    }
}
