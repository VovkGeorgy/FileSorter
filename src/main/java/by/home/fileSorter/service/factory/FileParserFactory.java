package by.home.fileSorter.service.factory;

import by.home.fileSorter.service.IFileParser;
import by.home.fileSorter.service.impl.json.JsonFileParser;
import by.home.fileSorter.service.impl.txt.TxtFileParser;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * Class returns an object depending on the input parameter
 */
@Service
public class FileParserFactory {

    @Autowired
    private TxtFileParser txtFileParser;

    @Autowired
    private JsonFileParser jsonFileParser;

    @Value("${json.file.extension}")
    private String jsonFileExtension;

    @Value("${txt.file.extension}")
    private String txtFileExtension;

    private static final Logger LOGGER = LoggerFactory.getLogger(FileParserFactory.class);

    /**
     * Method return a Parser instance depending on file in fileList ending
     *
     * @param fileList list of input files
     * @return file Parser for cheesed files
     */
    public IFileParser getFileParser(List<File> fileList) {
        LOGGER.info("Getting file parser, depending of file in fileList ending");
        IFileParser fileParser = null;
        if (FilenameUtils.getExtension(fileList.get(0).getName()).equals(txtFileExtension)) {
            fileParser = txtFileParser;
            LOGGER.debug("Getting file parser {} depending of file in list ending {}", txtFileParser, fileList);
        } else if (FilenameUtils.getExtension(fileList.get(0).getName()).equals(jsonFileExtension)) {
            fileParser = jsonFileParser;
            LOGGER.debug("Getting file parser {} depending of file in list ending {}", jsonFileParser, fileList);
        }
        return fileParser;
    }
}