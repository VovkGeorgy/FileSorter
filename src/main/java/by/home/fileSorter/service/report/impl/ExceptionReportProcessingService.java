package by.home.fileSorter.service.report.impl;

import by.home.fileSorter.entity.ExceptionMessage;
import by.home.fileSorter.service.file.IFileService;
import by.home.fileSorter.service.file.impl.LocalFileService;
import by.home.fileSorter.service.report.IReportProcessingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Class control services of exception entity handling
 */
@Slf4j
@Service
public class ExceptionReportProcessingService implements IReportProcessingService<ExceptionMessage> {

    @Value("${input.folder.path}")
    private String inputFolderPath;

    @Value("${exception.valid.folder.path}")
    private String validOutputFolderPath;

    @Value("${exception.not.valid.folder.path}")
    private String notValidOutputFolderPath;

    private final IFileService localFIleService;

    @Autowired
    public ExceptionReportProcessingService(LocalFileService localFIleService) {
        this.localFIleService = localFIleService;
    }

    /**
     * Method consist services for handing exception entity
     *
     * @param exceptionMessage exception entity
     * @return result of work (true - positive, false - negative)
     */
    public boolean process(ExceptionMessage exceptionMessage) {
        String fileName = exceptionMessage.getFileName();
        String targetFplderPath = exceptionMessage.isValid() ? validOutputFolderPath : notValidOutputFolderPath;
        return localFIleService.moveFile(new File(inputFolderPath + fileName), inputFolderPath + fileName
                , targetFplderPath + fileName);
    }
}
