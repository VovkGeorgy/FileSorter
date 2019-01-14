package by.home.fileSorter.service.report.impl;

import by.home.fileSorter.entity.ErrorMessage;
import by.home.fileSorter.service.file.IFileService;
import by.home.fileSorter.service.file.impl.SftpFileService;
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

    @Autowired
    public ErrorReportProcessingService(SftpFileService sftpFileService) {
        this.sftpFileService = sftpFileService;
    }

    /**
     * Method consist services for handing error entity
     *
     * @param errorMessage error entity
     * @return result of work (true - positive, false - negative)
     */
    public boolean process(ErrorMessage errorMessage) {
        String fileName = errorMessage.getFileName();
        String targetFolderPath = errorMessage.isValid() ? validOutFolderPath : notValidOutFolderPath;
        return sftpFileService.moveFile(new File(inputFolderPath + fileName), inputFolderPath + fileName,
                targetFolderPath + fileName);
    }
}
