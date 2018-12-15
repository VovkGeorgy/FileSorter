package by.home.fileSorter.service.factory;

import by.home.fileSorter.service.IFileParser;
import by.home.fileSorter.service.IMessageBuilder;
import by.home.fileSorter.service.impl.ErrorMessageBuilder;
import by.home.fileSorter.service.impl.ExceptionMessageBuilder;
import by.home.fileSorter.service.impl.json.JsonFileParser;
import by.home.fileSorter.service.impl.txt.TxtFileParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Class returns an object depending on the input parameter
 */
public class MessageBuilderFactory {

    @Autowired
    private ExceptionMessageBuilder exceptionMessageBuilder;
    
    @Autowired
    private ErrorMessageBuilder errorMessageBuilder;

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageBuilderFactory.class);

    /**
     * Method return a Builder instance depending on the Parser instance
     *
     * @param fileParser file parser for cheesed file
     * @return file Builder for cheesed file
     */
    public IMessageBuilder getFileBuilder(IFileParser fileParser) {
        LOGGER.info("Getting file builder, depending of file parser");
        IMessageBuilder fileBilder = null;
        if (fileParser instanceof TxtFileParser) {
            fileBilder = exceptionMessageBuilder;
            LOGGER.debug("Getting file builder {} depending of file parser {}", exceptionMessageBuilder, fileParser);
        } else if (fileParser instanceof JsonFileParser) {
            fileBilder = errorMessageBuilder;
            LOGGER.debug("Getting file builder {} depending of file parser {}", errorMessageBuilder, fileParser);
        }
        return fileBilder;
    }
}
