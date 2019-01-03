package by.home.fileSorter.service;

import by.home.fileSorter.service.impl.json.JsonProcessingService;
import by.home.fileSorter.service.impl.txt.TxtProcessingService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Class realise factory of processing services
 */
@Slf4j
@Service
public class FileServiceFactory {
    private final TxtProcessingService txtProcessingService;
    private final JsonProcessingService jsonProcessingService;

    @Value("${array.of.extensions}")
    private String[] filesExtensions;

    @Autowired
    public FileServiceFactory(TxtProcessingService txtProcessingService, JsonProcessingService jsonProcessingService) {
        this.txtProcessingService = txtProcessingService;
        this.jsonProcessingService = jsonProcessingService;
    }

    /**
     * Method return a service instance depending on file extension
     *
     * @param file input file
     * @return file processing service for input file
     */
    public AbstractFileProcessingService getFileService(File file) {
        log.info("Getting file parser, depending of extension");
        return FilenameUtils.getExtension(file.getName()).equals(filesExtensions[1]) ? jsonProcessingService :
                FilenameUtils.getExtension(file.getName()).equals(filesExtensions[0]) ? txtProcessingService : null;
    }
}
