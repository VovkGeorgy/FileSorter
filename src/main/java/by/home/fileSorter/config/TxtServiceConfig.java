package by.home.fileSorter.config;

import by.home.fileSorter.service.impl.txt.TxtFileMover;
import by.home.fileSorter.service.impl.txt.TxtFileParser;
import by.home.fileSorter.service.impl.txt.TxtFileValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Class configure Spring Beans, include txt file service beans
 */
@Configuration
public class TxtServiceConfig {

    @Bean
    public TxtFileMover txtFileMover() {
        return new TxtFileMover();
    }

    @Bean
    public TxtFileParser txtFileParser() {
        return new TxtFileParser();
    }

    @Bean
    public TxtFileValidator txtFileValidator() {
        return new TxtFileValidator();
    }
}
