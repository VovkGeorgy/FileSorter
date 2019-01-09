package by.home.fileSorter.service.impl.error;

import by.home.fileSorter.entity.ErrorMessage;
import by.home.fileSorter.service.IProcessingService;
import by.home.fileSorter.service.SftpService;
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
public class ErrorProcessingService implements IProcessingService<ErrorMessage> {

    @Value("${input.folder.path}")
    private String inputFolderPath;

    @Value("${error.sftp.valid.folder.path}")
    private String validOutFolderPath;

    @Value("${error.sftp.not.valid.folder.path}")
    private String notValidOutFolderPath;

    private final SftpService sftpService;

    @Autowired
    public ErrorProcessingService(SftpService sftpService) {
        this.sftpService = sftpService;
    }

    /**
     * Method consist services for handing error entity
     *
     * @param errorMessage error entity
     * @return result of work (true - positive, false - negative)
     */
    public boolean process(ErrorMessage errorMessage) {
        String fileName = errorMessage.getFileName();

        return errorMessage.isValid() ?
                sftpService.moveFile(new File(inputFolderPath + errorMessage.getFileName()),
                        inputFolderPath + fileName, validOutFolderPath + fileName) :
                sftpService.moveFile(new File(inputFolderPath + errorMessage.getFileName()),
                        inputFolderPath + fileName, notValidOutFolderPath + fileName);
    }
}
