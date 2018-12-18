package by.home.fileSorter.service.factory;

import by.home.fileSorter.entity.ErrorMessage;
import by.home.fileSorter.entity.ExceptionMessage;
import by.home.fileSorter.service.IFileMover;
import by.home.fileSorter.service.impl.json.JsonFileMover;
import by.home.fileSorter.service.impl.txt.TxtFileMover;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;

/**
 * Class returns an object depending on the input parameter
 */
@Service
public class FileMoverFactory {

    @Autowired
    private TxtFileMover txtFileMover;

    @Autowired
    private JsonFileMover jsonFileMover;

    @Value("${json.file.extension}")
    private String jsonFileExtension;

    @Value("${txt.file.extension}")
    private String txtFileExtension;


    private static final Logger LOGGER = LoggerFactory.getLogger(FileMoverFactory.class);

    /**
     * Method return a Mover instance depending on the object type
     *
     * @param listOfObjects list of input objects
     * @return file mover for cheesed objects
     */
    public IFileMover getFileMover(ArrayList listOfObjects, ArrayList<File> notValidFileslist) {
        LOGGER.info("Getting file mover, depending of file parser");
        if (listOfObjects.isEmpty()) return checkNotValidFiles(notValidFileslist);
        else return checkObjects(listOfObjects);
    }

    private IFileMover checkNotValidFiles(ArrayList<File> notValidFilesList) {
        if (FilenameUtils.getExtension(notValidFilesList.get(0).getName()).equals(txtFileExtension)) {
            LOGGER.debug("Getting file mover {}", txtFileMover);
            return txtFileMover;
        } else if (FilenameUtils.getExtension(notValidFilesList.get(0).getName()).equals(jsonFileExtension)) {
            LOGGER.debug("Getting file mover {}", jsonFileMover);
            return jsonFileMover;
        }
        return null;
    }

    private IFileMover checkObjects(ArrayList objectsLisr) {
        IFileMover fileMover = null;
        if (objectsLisr.get(0) instanceof ExceptionMessage) {
            fileMover = txtFileMover;
            LOGGER.debug("Getting file mover {} depending of object type", txtFileMover);
        } else if (objectsLisr.get(0) instanceof ErrorMessage) {
            fileMover = jsonFileMover;
            LOGGER.debug("Getting file mover {} depending of object type", jsonFileMover);
        }
        return fileMover;
    }
}
