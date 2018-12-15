package by.home.fileSorter.service.factory;

import by.home.fileSorter.service.IFileMover;
import by.home.fileSorter.service.IFileParser;
import by.home.fileSorter.service.impl.json.JsonFileMover;
import by.home.fileSorter.service.impl.json.JsonFileParser;
import by.home.fileSorter.service.impl.txt.TxtFileMover;
import by.home.fileSorter.service.impl.txt.TxtFileParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Class returns an object depending on the input parameter
 */
public class FileMoverFactory {

    @Autowired
    private TxtFileMover txtFileMover;

    @Autowired
    private JsonFileMover jsonFileMover;

    private static final Logger LOGGER = LoggerFactory.getLogger(FileMoverFactory.class);

    /**
     * Method return a Mover instance depending on the Parser instance
     *
     * @param fileParser file parser for cheesed file
     * @return file mover for cheesed file
     */
    public IFileMover getFileMover(IFileParser fileParser) {
        LOGGER.info("Getting file mover, depending of file parser");
        IFileMover fileMover = null;
        if (fileParser instanceof TxtFileParser) {
            fileMover = txtFileMover;
            LOGGER.debug("Getting file mover {} depending of file parser {}", txtFileMover, fileParser);
        } else if (fileParser instanceof JsonFileParser) {
            fileMover = jsonFileMover;
            LOGGER.debug("Getting file mover {} depending of file parser {}", jsonFileMover, fileParser);
        }
        return fileMover;
    }
}
