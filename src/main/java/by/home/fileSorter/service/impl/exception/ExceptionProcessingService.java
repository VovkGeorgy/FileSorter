package by.home.fileSorter.service.impl.exception;

import by.home.fileSorter.entity.ExceptionMessage;
import by.home.fileSorter.service.IProcessingService;
import by.home.fileSorter.service.impl.file.LocalFileService;
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
public class ExceptionProcessingService implements IProcessingService<ExceptionMessage> {

    @Value("${input.folder.path}")
    private String inputFolderPath;

    @Value("${exception.valid.folder.path}")
    private String validOutputFolderPath;

    @Value("${exception.not.valid.folder.path}")
    private String notValidOutputFolderPath;

    @Value("${max.read.files}")
    private int maxReadFiles;

    @Value("${array.of.extensions}")
    private String[] filesExtensions;

    private final LocalFileService localFIleService;

    @Autowired
    public ExceptionProcessingService(LocalFileService localFIleService) {
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
        return exceptionMessage.isValid() ?
                localFIleService.moveFile(new File(inputFolderPath + fileName), inputFolderPath + fileName
                        , validOutputFolderPath + fileName) :
                localFIleService.moveFile(new File(inputFolderPath + fileName), inputFolderPath + fileName
                        , notValidOutputFolderPath + fileName);
    }
}
