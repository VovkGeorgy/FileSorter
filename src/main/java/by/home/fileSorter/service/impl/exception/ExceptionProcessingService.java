package by.home.fileSorter.service.impl.exception;

import by.home.fileSorter.entity.ExceptionMessage;
import by.home.fileSorter.service.IProcessingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Class control services of exception file handling
 */
@Slf4j
@Service
public class ExceptionProcessingService implements IProcessingService<ExceptionMessage> {

    @Value("${input.folder.path}")
    private String inputFolderPath;

    private final ExceptionFileMover exceptionFileMover;

    @Autowired
    public ExceptionProcessingService(ExceptionFileMover exceptionFileMover) {
        this.exceptionFileMover = exceptionFileMover;
    }

    public boolean process(ExceptionMessage exceptionMessage) {
        return exceptionFileMover.moveFile(new File(inputFolderPath + exceptionMessage.getFileName()), exceptionMessage.isValid
                ());
    }
}
