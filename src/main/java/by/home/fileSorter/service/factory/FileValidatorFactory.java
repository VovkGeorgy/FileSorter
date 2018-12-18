package by.home.fileSorter.service.factory;


import by.home.fileSorter.service.IFIleValidator;
import by.home.fileSorter.service.IFileParser;
import by.home.fileSorter.service.impl.json.JsonFileParser;
import by.home.fileSorter.service.impl.json.JsonFileValidator;
import by.home.fileSorter.service.impl.txt.TxtFileParser;
import by.home.fileSorter.service.impl.txt.TxtFileValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class returns an object depending on the input parameter
 */
@Service
public class FileValidatorFactory {

    @Autowired
    private TxtFileValidator txtFileValidator;

    @Autowired
    private JsonFileValidator jsonFileValidator;

    private static final Logger LOGGER = LoggerFactory.getLogger(FileValidatorFactory.class);

    /**
     * Method return a Validator instance depending on the Parser instance
     *
     * @param fileParser file parser for cheesed file
     * @return file mover for cheesed file
     */
    public IFIleValidator getFileValidator(IFileParser fileParser) {
        LOGGER.info("Getting file validator, depending of file parser");
        IFIleValidator fileValidator = null;
        if (fileParser instanceof TxtFileParser) {
            fileValidator = txtFileValidator;
            LOGGER.debug("Getting file validator {} depending of file parser {}", txtFileValidator, fileParser);
        } else if (fileParser instanceof JsonFileParser) {
            fileValidator = jsonFileValidator;
            LOGGER.debug("Getting file validator {} depending of file parser {}", jsonFileValidator, fileParser);
        }
        return fileValidator;
    }
}
