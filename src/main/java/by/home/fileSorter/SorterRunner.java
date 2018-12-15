package by.home.fileSorter;

import by.home.fileSorter.service.IFIleValidator;
import by.home.fileSorter.service.IFileMover;
import by.home.fileSorter.service.IFileParser;
import by.home.fileSorter.service.IMessageBuilder;
import by.home.fileSorter.service.factory.FileMoverFactory;
import by.home.fileSorter.service.factory.FileParserFactory;
import by.home.fileSorter.service.factory.FileValidatorFactory;
import by.home.fileSorter.service.factory.MessageBuilderFactory;
import by.home.fileSorter.service.file.FileReader;
import by.home.fileSorter.service.file.FileSeparator;
import by.home.fileSorter.service.file.FilesFolderScanner;
import by.home.fileSorter.service.impl.txt.TxtFileParser;
import by.home.fileSorter.utils.PropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.List;

/**
 * Class control program running
 */
public class SorterRunner {

    @Autowired
    private FilesFolderScanner filesFolderScanner;
    @Autowired
    private PropertiesUtils propertiesUtils;
    @Autowired
    private FileSeparator fileSeparator;
    @Autowired
    private FileMoverFactory fileMoverFactory;
    @Autowired
    private FileParserFactory fileParserFactory;
    @Autowired
    private FileReader fileReader;
    @Autowired
    private FileValidatorFactory fileValidatorFactory;
    @Autowired
    private MessageBuilderFactory messageBuilderFactory;

    private static final Logger LOGGER = LoggerFactory.getLogger(SorterRunner.class);

    /**
     * Method exist all program functions, to step by step working
     */
    void runSorter() {
        LOGGER.info("Program started");
        List<String> fileTemplates = propertiesUtils.getTemplates();
        for (String template : fileTemplates) {
            List<File> notSortedFiles = filesFolderScanner.scan();
            LOGGER.debug("Get not sorted {} files {}", template, notSortedFiles);
            if (notSortedFiles == null) continue;
            List<File> sortedFiles = fileSeparator.separate(notSortedFiles, template);
            LOGGER.debug("Get sorted {} files {}", template, sortedFiles);
            if (sortedFiles == null) continue;
            IFileParser fileParser = fileParserFactory.getFileParser(sortedFiles);
            LOGGER.debug("Get file parser {}", fileParser);
            IFIleValidator fileValidator = fileValidatorFactory.getFileValidator(fileParser);
            LOGGER.debug("Get file validator {}", fileValidator);
            sortedFiles.forEach(file -> fileValidator.valid(fileParser.getMessages(fileReader.readFile(file.getPath())), file));
            IMessageBuilder messageBuilder = messageBuilderFactory.getFileBuilder(fileParser);
            LOGGER.debug("Get message builder {}", messageBuilder);
            messageBuilder.build();
            IFileMover fileMover = fileMoverFactory.getFileMover(fileParser);
            LOGGER.debug("Get file mover {}", fileMover);
            List<File> validFileList = fileValidator.getValidMessageFileList();
            List<File> notValidFileList = fileValidator.getNotValidMessageFileList();
            fileMover.moveFiles(validFileList, notValidFileList);
            LOGGER.info("End cycle of program");
        }
        try {
            LOGGER.debug("Wait {} mseconds", propertiesUtils.getScanDelay());
            Thread.sleep(propertiesUtils.getScanDelay());
            runSorter();
        } catch (Exception ex) {
            LOGGER.error("Program get exception on sleep {}", ex.getMessage());
        }
    }
}
