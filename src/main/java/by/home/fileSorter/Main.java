package by.home.fileSorter;

import by.home.fileSorter.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    /**
     * Main class BITCH!
     * @param args -
     */
    public static void main(String[] args) {
        System.out.println("WORK!");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        context.getBean(SorterRunner.class).runSorter();
    }
}
