package by.home.fileSorter.service.impl.file;


import by.home.fileSorter.entity.ExceptionMessage;
import by.home.fileSorter.service.IReportParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.format.DateTimeParseException;

/**
 * CSV format file parser
 */
@Slf4j
@Service
public class CsvParser implements IReportParser<ExceptionMessage> {

    /**
     * Method parse input csv file
     *
     * @param file file to parse
     * @return entity from parsed file
     */
    @Override
    public ExceptionMessage parseFile(File file) {
        log.info("Try to parse csv file {}", file.getName());
        Iterable<CSVRecord> records;
        ExceptionMessage exceptionMessage = new ExceptionMessage();
        try (Reader in = new FileReader(file)) {
            records = CSVFormat.RFC4180.withHeader("messageType", "id", "message", "typeOfException", "throwingTime").parse(in);
            for (CSVRecord record : records) {
                exceptionMessage.setMessageType(record.get("messageType"));
                exceptionMessage.setId(Long.parseLong(record.get("id")));
                exceptionMessage.setMessage(record.get("message"));
                exceptionMessage.setTypeOfException(record.get("typeOfException"));
                exceptionMessage.setThrowingTime(record.get("throwingTime"));
                exceptionMessage.setFileName(file.getName());
                exceptionMessage.setValid(true);
            }
            log.info("Done file {} parsing", file.getName());
            return exceptionMessage;
        } catch (IOException | NullPointerException | DateTimeParseException | IllegalArgumentException e) {
            log.error("Can't parse file {}, get exception \n {}", file.getName(), e.getMessage());
            ExceptionMessage notValidExceptionMessage = new ExceptionMessage();
            notValidExceptionMessage.setValid(false);
            notValidExceptionMessage.setFileName(file.getName());
            return notValidExceptionMessage;
        }
    }
}