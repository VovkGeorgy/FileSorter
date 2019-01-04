package by.home.fileSorter.service;

import by.home.fileSorter.service.impl.file.CsvParser;
import by.home.fileSorter.service.impl.file.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Class of parser services factory
 */
@Slf4j
@Service
public class ReportParserFactory {
    private Map<String, IReportParser> reportParsersMap = new HashMap<>();

    @Autowired
    public ReportParserFactory(JsonParser jsonParser, CsvParser csvParser) {
        this.reportParsersMap.put("json", jsonParser);
        this.reportParsersMap.put("msg", jsonParser);
        this.reportParsersMap.put("csv", csvParser);
        this.reportParsersMap.put("txt", csvParser);
    }

    /**
     * Method return entity of file parser depending of input file extension
     *
     * @param file input file
     * @return file parser instance
     */
    IReportParser getParser(File file) {
        return reportParsersMap.get(FilenameUtils.getExtension(file.getName()));
    }
}
