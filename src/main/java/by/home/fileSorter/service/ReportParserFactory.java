package by.home.fileSorter.service;

import by.home.fileSorter.service.impl.file.CsvParser;
import by.home.fileSorter.service.impl.file.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Map;

@Slf4j
@Service
public class ReportParserFactory {
    private Map<String, IReportParser> reportParsersMap;

    @Autowired
    public ReportParserFactory(JsonParser jsonParser, CsvParser csvParser) {
        this.reportParsersMap.put("json", jsonParser);
        this.reportParsersMap.put("csv", csvParser);
    }

    IReportParser getParser(File file) {
        return reportParsersMap.get(FilenameUtils.getExtension(file.getName()));
    }
}
