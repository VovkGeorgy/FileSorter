package by.home.fileSorter.service.impl.txt;

import by.home.fileSorter.service.AbstractFileProcessingService;
import by.home.fileSorter.service.IValidityChecker;
import by.home.fileSorter.service.IFileMover;
import by.home.fileSorter.service.IFileParser;
import by.home.fileSorter.service.file.FileManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.File;


/**
 * Class control services of txt file handling
 */
@Slf4j
@Service
public class TxtProcessingService extends AbstractFileProcessingService {
    private final FileManager fileManager;
    private final IFileParser fileParser;
    private final IValidityChecker validityChecker;
    private final IFileMover fileMover;

    @Autowired
    public TxtProcessingService(FileManager fileManager, @Qualifier("txtFileParser") IFileParser fileParser, @Qualifier("txtValidityChecker") IValidityChecker validityChecker, @Qualifier("txtFileMover") IFileMover fileMover) {
        this.fileManager = fileManager;
        this.fileParser = fileParser;
        this.validityChecker = validityChecker;
        this.fileMover = fileMover;
    }

    @Override
    public void process(File file) {
        log.info("Process txt file {}", file.getName());
        fileMover.moveFile(validityChecker.isValid(fileParser.getMessage(fileManager.readFile(file)), file), file);
    }
}
