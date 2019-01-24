package by.home.fileSorter.service.report.impl;

import by.home.fileSorter.entity.ErrorMessage;
import by.home.fileSorter.repository.ErrorRepository;
import by.home.fileSorter.service.file.IFileService;
import by.home.fileSorter.service.report.IReportProcessingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Class control services of error entity handing
 */
@Slf4j
@Service
public class ErrorReportProcessingService implements IReportProcessingService<ErrorMessage> {

    @Value("${input.folder.path}")
    private String inputFolderPath;

    @Value("${error.sftp.valid.folder.path}")
    private String validOutFolderPath;

    @Value("${error.sftp.not.valid.folder.path}")
    private String notValidOutFolderPath;

    private final IFileService sftpFileService;
    private final ErrorRepository errorRepository;

    @Autowired
    public ErrorReportProcessingService(IFileService sftpFileService, ErrorRepository errorRepository) {
        this.sftpFileService = sftpFileService;
        this.errorRepository = errorRepository;
    }

    /**
     * Method consist services for handing error entity
     *
     * @param errorMessage error entity
     * @return result of work (true - positive, false - negative)
     */
    public boolean process(ErrorMessage errorMessage) {
        if (errorMessage.isValid()) {
            log.info("Process valid error message entity from {} file", errorMessage.getFileName());
            errorRepository.save(errorMessage);
            return moveFiles(errorMessage, validOutFolderPath);
        }
        log.info("Process NOT valid error message entity from {} file", errorMessage.getFileName());
        return moveFiles(errorMessage, notValidOutFolderPath);
    }

    private boolean moveFiles(ErrorMessage errorMessage, String targetFolderPath) {
        return sftpFileService.moveFile(new File(inputFolderPath + errorMessage.getFileName()), targetFolderPath);
    }
}
