package by.home.fileSorter.entity;

import lombok.Data;

import java.time.LocalDate;

/**
 * Exception message entity
 */
@Data
public class ExceptionMessage {
    private String messageType;
    private Long id;
    private String message;
    private String typeOfException;
    private LocalDate throwingTime;
    private String fileName;
}


