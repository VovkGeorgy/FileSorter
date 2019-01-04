package by.home.fileSorter.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Error message entity
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ErrorMessage extends AbstractMessage {
    private String messageType;
    private Long id;
    private String message;
    private String typeOfError;
    private String throwingTime;
    private String fileName;
    private boolean validity;
}