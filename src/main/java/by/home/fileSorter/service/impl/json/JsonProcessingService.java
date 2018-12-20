package by.home.fileSorter.service.impl.json;

import by.home.fileSorter.service.AbstractFileProcessingService;
import by.home.fileSorter.service.IValidityChecker;
import by.home.fileSorter.service.IFileMover;
import by.home.fileSorter.service.IFileParser;
import by.home.fileSorter.service.file.FileReader;
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
    private final FileReader fileReader;
    private final IFileParser iFileParser;
    private final IValidityChecker iValidityChecker;
    private final IFileMover iFileMover;

    @Autowired
    public JsonProcessingService(FileReader fileReader, @Qualifier("jsonFileParser") IFileParser iFileParser, @Qualifier("jsonValidityChecker") IValidityChecker iValidityChecker, @Qualifier("jsonFileMover") IFileMover iFileMover) {
        this.fileReader = fileReader;
        this.iFileParser = iFileParser;
        this.iValidityChecker = iValidityChecker;
        this.iFileMover = iFileMover;
    }

    @Override
    public void process(File file) {
        log.info("Begin file handling");
        iFileMover.moveFile(iValidityChecker.isValid(iFileParser.getMessage(fileReader.readFile(file)), file), file);
        log.info("End file handling");
    }
}
