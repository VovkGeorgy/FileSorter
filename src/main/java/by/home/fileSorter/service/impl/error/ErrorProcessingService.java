package by.home.fileSorter.service.impl.error;

import by.home.fileSorter.entity.ErrorMessage;
import by.home.fileSorter.service.IProcessingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Class control services of error file handling
 */
@Slf4j
@Service
public class ErrorProcessingService implements IProcessingService<ErrorMessage> {

    public boolean process(ErrorMessage errorMessage) {
        return false;
    }
}
