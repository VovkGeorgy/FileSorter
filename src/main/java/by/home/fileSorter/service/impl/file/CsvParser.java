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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

@Slf4j
@Service
public class CsvParser implements IReportParser<ExceptionMessage> {

    @Override
    public ExceptionMessage parseFile(File file) {
        Iterable<CSVRecord> records;
        ExceptionMessage exceptionMessage = new ExceptionMessage();
        try {
            Reader in = new FileReader(file);
            records = CSVFormat.RFC4180.withHeader("messageType", "id", "message", "typeOfException", "throwingTime").parse(in);
            for (CSVRecord record : records) {
                exceptionMessage.setMessageType(record.get("messageType"));
                exceptionMessage.setId(Long.parseLong(record.get("id")));
                exceptionMessage.setMessage(record.get("message"));
                exceptionMessage.setTypeOfException(record.get("typeOfException"));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
                exceptionMessage.setThrowingTime(LocalDate.parse(record.get("throwingTime"), formatter));
                exceptionMessage.setFileName(file.getName());
                exceptionMessage.setValid(true);
            }
        } catch (IOException | NullPointerException | DateTimeParseException e) {
            log.error("Can't parse file {}, get exception \n {}", file.getName(), e.getMessage());
            exceptionMessage.setValid(false);
            exceptionMessage.setFileName(file.getName());
        }
        return exceptionMessage;
    }
}