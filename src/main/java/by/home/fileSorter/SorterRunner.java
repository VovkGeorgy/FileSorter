package by.home.fileSorter;

import by.home.fileSorter.service.IFIleValidator;
import by.home.fileSorter.service.IFileMover;
import by.home.fileSorter.service.IFileParser;
import by.home.fileSorter.service.factory.FileMoverFactory;
import by.home.fileSorter.service.factory.FileParserFactory;
import by.home.fileSorter.service.factory.FileValidatorFactory;
import by.home.fileSorter.service.file.FileReader;
import by.home.fileSorter.service.file.FileSeparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Class control program running
 */
@Component
@EnableScheduling
public class SorterRunner {

    @Value("${scan.delay}")
    private int scanDelay;

    @Value("${json.file.extension}")
    private String jsonFileExtension;

    @Value("${txt.file.extension}")
    private String txtFileExtension;

    @PostConstruct
    void postConstruct() {
        filesExtensions.add(txtFileExtension);
        filesExtensions.add(jsonFileExtension);
    }

    private FileSeparator fileSeparator;
    private FileMoverFactory fileMoverFactory;
    private FileParserFactory fileParserFactory;
    private FileReader fileReader;
    private FileValidatorFactory fileValidatorFactory;
    private List<String> filesExtensions = new ArrayList<>();

    @Autowired
    public SorterRunner(FileSeparator fileSeparator,
                        FileMoverFactory fileMoverFactory, FileParserFactory fileParserFactory, FileReader fileReader,
                        FileValidatorFactory fileValidatorFactory) {
        this.fileSeparator = fileSeparator;
        this.fileMoverFactory = fileMoverFactory;
        this.fileParserFactory = fileParserFactory;
        this.fileReader = fileReader;
        this.fileValidatorFactory = fileValidatorFactory;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(SorterRunner.class);

    /**
     * Method exist all program functions, to step by step working, and repeat evey 5 sec
     */
    @Scheduled(fixedDelay = 5000)
    void runSorter() {
        LOGGER.info("Program started");
        for (String extension : filesExtensions) {
            List<File> files = fileSeparator.getFilesByExtension(extension);
            LOGGER.debug("Get sorted {} files {}", extension, files);
            if (files.isEmpty()) continue;
            IFileParser fileParser = fileParserFactory.getFileParser(files);
            LOGGER.debug("Get file parser {}", fileParser);
            IFIleValidator fileValidator = fileValidatorFactory.getFileValidator(fileParser);
            LOGGER.debug("Get file validator {}", fileValidator);
            files.forEach(file -> fileValidator.valid(fileParser.getMessage(fileReader.readFile(file.getPath())), file));
            IFileMover fileMover = fileMoverFactory.getFileMover(fileValidator.getValidMessageList(), fileValidator.getNotValidMessageFileList());
            LOGGER.debug("Get file mover {}", fileMover);
            ArrayList validMessageList = fileValidator.getValidMessageList();
            ArrayList notValidFileList = fileValidator.getNotValidMessageFileList();
            fileMover.moveFiles(validMessageList, notValidFileList);
            LOGGER.info("End cycle of program");
        }
    }
}
