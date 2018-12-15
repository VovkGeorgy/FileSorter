package by.home.fileSorter.config;

import by.home.fileSorter.service.impl.json.JsonFileMover;
import by.home.fileSorter.service.impl.json.JsonFileParser;
import by.home.fileSorter.service.impl.json.JsonFileValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Class configure Spring Beans, include json file service beans
 */
@Configuration
public class JsonServiceConfig {

    @Bean
    public JsonFileMover jsonFileMover() {
        return new JsonFileMover();
    }

    @Bean
    public JsonFileParser jsonFileParser() {
        return new JsonFileParser();
    }

    @Bean
    public JsonFileValidator jsonFileValidator() {
        return new JsonFileValidator();
    }
}
