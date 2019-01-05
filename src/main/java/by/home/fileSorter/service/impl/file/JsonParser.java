package by.home.fileSorter.service.impl.file;

import by.home.fileSorter.entity.ErrorMessage;
import by.home.fileSorter.service.IReportParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * JSON format file parser
 */
@Slf4j
@Service
public class JsonParser implements IReportParser<ErrorMessage> {

    /**
     * Method parse json input file
     *
     * @param file input file
     * @return entity from parsed file
     */
    @Override
    public ErrorMessage parseFile(File file) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ErrorMessage errorMessage = objectMapper.readValue(file, new TypeReference<ErrorMessage>() {
            });
            errorMessage.setFileName(file.getName());
            errorMessage.setValid(true);
            return errorMessage;
        } catch (IOException e) {
            log.error("Cant parse file {}, IOException \n", file.getName(), e.getMessage());
            ErrorMessage notValidErrorMessage = new ErrorMessage();
            notValidErrorMessage.setFileName(file.getName());
            notValidErrorMessage.setValid(false);
            return notValidErrorMessage;
        }
    }
}
