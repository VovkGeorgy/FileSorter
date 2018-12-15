package by.home.fileSorter.service.factory;

import by.home.fileSorter.service.IFileParser;
import by.home.fileSorter.service.impl.json.JsonFileParser;
import by.home.fileSorter.service.impl.txt.TxtFileParser;
import by.home.fileSorter.utils.PropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.List;

/**
 * Class returns an object depending on the input parameter
 */
public class FileParserFactory {

    @Autowired
    private PropertiesUtils propertiesUtils;
    @Autowired
    private TxtFileParser txtFileParser;
    @Autowired
    private JsonFileParser jsonFileParser;

    private static final Logger LOGGER = LoggerFactory.getLogger(FileParserFactory.class);

    /**
     * Method return a Parser instance depending on file in fileList ending
     *
     * @param fileList list of input files
     * @return file Parser for cheesed files
     */
    public IFileParser getFileParser(List<File> fileList) {
        LOGGER.info("Getting file parser, depending of file in fileList ending");
        String txtFileTemplate = propertiesUtils.getTxtFileTemplate();
        String jsonFileTemplate = propertiesUtils.getJsonFileTemplate();
        IFileParser fileParser = null;
        if (fileList.get(0).getName().endsWith(txtFileTemplate)) {
            fileParser = txtFileParser;
            LOGGER.debug("Getting file parser {} depending of file in list ending {}", txtFileParser, fileList);
        } else if (fileList.get(0).getName().endsWith(jsonFileTemplate)) {
            fileParser = jsonFileParser;
            LOGGER.debug("Getting file parser {} depending of file in list ending {}", jsonFileParser, fileList);
        }
        return fileParser;
    }
}