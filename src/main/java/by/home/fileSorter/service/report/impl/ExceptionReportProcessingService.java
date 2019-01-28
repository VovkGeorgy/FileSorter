package by.home.fileSorter.service.report.impl;

import by.home.fileSorter.entity.ExceptionMessage;
import by.home.fileSorter.repository.ExceptionRepository;
import by.home.fileSorter.service.file.IFileService;
import by.home.fileSorter.service.report.IReportProcessingService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ExceptionReportProcessingService implements IReportProcessingService<ExceptionMessage> {

    @Value("${input.folder.path}")
    private String inputFolderPath;

    @Value("${exception.valid.folder.path}")
    private String validOutputFolderPath;

    @Value("${exception.not.valid.folder.path}")
    private String notValidOutputFolderPath;

    private final IFileService localFileService;
    private final ExceptionRepository exceptionRepository;

    /**
     * Method consist services for handing exception entity
     *
     * @param exceptionMessage exception entity
     * @return result of work (true - positive, false - negative)
     */
    public boolean process(ExceptionMessage exceptionMessage) {
        if (exceptionMessage.isValid()) {
            exceptionRepository.save(exceptionMessage);
            log.debug("Process valid exception message entity from {} file", exceptionMessage.getFileName());
            return moveFiles(exceptionMessage, validOutputFolderPath);
        }
        log.debug("Process NOT valid exception message entity from {} file", exceptionMessage.getFileName());
        return moveFiles(exceptionMessage, notValidOutputFolderPath);
    }

    private boolean moveFiles(ExceptionMessage exceptionMessage, String targetFolderPath) {
        return localFileService.moveFile(new File(inputFolderPath + exceptionMessage.getFileName()), targetFolderPath);
    }
}
