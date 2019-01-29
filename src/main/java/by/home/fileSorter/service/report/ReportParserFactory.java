package by.home.fileSorter.service.report;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class of parser services factory
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReportParserFactory {

    @Value("#{'${list.of.error.report.extensions}'.split(',')}")
    private final List<String> errorExtensions;

    @Value("#{'${list.of.exception.report.extensions}'.split(',')}")
    private final List<String> exceptionExtensions;

    private Map<List<String>, IReportParser> reportParsersMap = new HashMap<>();
    private final IReportParser jsonParser;
    private final IReportParser csvParser;

    @PostConstruct
    private void init() {
        this.reportParsersMap.put(errorExtensions, jsonParser);
        this.reportParsersMap.put(exceptionExtensions, csvParser);
    }


    /**
     * Method return entity of file parser depending of input file extension
     *
     * @param file input file
     * @return file parser instance
     */
    public IReportParser getParser(File file) {
        log.debug("Get parser for {} file", file.getName());
        return reportParsersMap.get(reportParsersMap.keySet().stream()
                .filter(list -> list.contains(FilenameUtils.getExtension(file.getName()))).findFirst().orElseGet(null));
    }
}
