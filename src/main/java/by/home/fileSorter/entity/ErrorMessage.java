package by.home.fileSorter.entity;

import lombok.Data;

/**
 * Error message entity
 */
@Data
public class ErrorMessage {
    private String messageType;
    private Long id;
    private String message;
    private String typeOfError;
    private String throwingTime;
    private String fileName;
}