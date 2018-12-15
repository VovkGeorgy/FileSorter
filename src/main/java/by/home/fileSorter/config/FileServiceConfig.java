package by.home.fileSorter.config;

import by.home.fileSorter.service.file.FileReader;
import by.home.fileSorter.service.file.FileSeparator;
import by.home.fileSorter.service.file.FilesFolderScanner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileServiceConfig {

    @Bean
    public FileReader fileReader() {
        return new FileReader();
    }

    @Bean
    public FilesFolderScanner filesFolderScanner() {
        return new FilesFolderScanner();
    }

    @Bean
    public FileSeparator fileSeparator() {
        return new FileSeparator();
    }
}
