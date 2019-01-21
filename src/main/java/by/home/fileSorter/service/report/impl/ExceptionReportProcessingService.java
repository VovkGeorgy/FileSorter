package by.home.fileSorter.service.report.impl;

import by.home.fileSorter.entity.ExceptionMessage;
import by.home.fileSorter.repository.ExceptionRepository;
import by.home.fileSorter.service.file.IFileService;
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

    private final IFileService localFileService;
    private final ExceptionRepository exceptionRepository;

    @Autowired
    public ExceptionReportProcessingService(IFileService localFileService, ExceptionRepository exceptionRepository) {
        this.localFileService = localFileService;
        this.exceptionRepository = exceptionRepository;
    }

    /**
     * Method consist services for handing exception entity
     *
     * @param exceptionMessage exception entity
     * @return result of work (true - positive, false - negative)
     */
    public boolean process(ExceptionMessage exceptionMessage) {
        if (exceptionMessage.isValid()) {
            exceptionRepository.save(exceptionMessage);
            return moveFiles(exceptionMessage, validOutputFolderPath);
        } else {
            return moveFiles(exceptionMessage, notValidOutputFolderPath);
        }
    }

    private boolean moveFiles(ExceptionMessage exceptionMessage, String targetFolderPath) {
        String fileName = exceptionMessage.getFileName();
        return localFileService.moveFile(new File(inputFolderPath + fileName), targetFolderPath + fileName);
    }
}
