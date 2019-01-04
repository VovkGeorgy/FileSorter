package by.home.fileSorter.service.impl.error;

import by.home.fileSorter.entity.ErrorMessage;
import by.home.fileSorter.service.IProcessingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Class control services of error file handling
 */
@Slf4j
@Service
public class ErrorProcessingService implements IProcessingService<ErrorMessage> {

    @Value("${input.folder.path}")
    private String inputFolderPath;

    private final ErrorFileMover errorFileMover;

    @Autowired
    public ErrorProcessingService(ErrorFileMover errorFileMover) {
        this.errorFileMover = errorFileMover;
    }

    public boolean process(ErrorMessage errorMessage) {
        return errorFileMover.moveFile(new File(inputFolderPath + errorMessage.getFileName()), errorMessage.isValid());
    }
}
