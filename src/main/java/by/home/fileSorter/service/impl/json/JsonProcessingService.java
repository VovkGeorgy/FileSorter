package by.home.fileSorter.service.impl.json;

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
 * Class control services of json file handling
 */
@Slf4j
@Service
public class JsonProcessingService extends AbstractFileProcessingService {
    private final FileManager fileManager;
    private final IFileParser iFileParser;
    private final IValidityChecker iValidityChecker;
    private final IFileMover iFileMover;

    @Autowired
    public JsonProcessingService(FileManager fileManager, @Qualifier("jsonFileParser") IFileParser iFileParser, @Qualifier("jsonValidityChecker") IValidityChecker iValidityChecker, @Qualifier("jsonFileMover") IFileMover iFileMover) {
        this.fileManager = fileManager;
        this.iFileParser = iFileParser;
        this.iValidityChecker = iValidityChecker;
        this.iFileMover = iFileMover;
    }

    @Override
    public void process(File file) {
        log.info("Process json file {}", file);
        iFileMover.moveFile(iValidityChecker.isValid(iFileParser.getMessage(fileManager.readFile(file)), file), file);
    }
}
