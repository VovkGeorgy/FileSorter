package by.home.fileSorter.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Exception message entity
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ExceptionMessage extends AbstractMessage {
    private String typeOfException;

    public ExceptionMessage(String fileName, boolean isValid) {
        this.fileName = fileName;
        this.isValid = isValid;
    }

    public ExceptionMessage(String typeOfException, String messageType, Long id, String message, String throwingTime, String
            fileName, boolean isValid) {
        this.typeOfException = typeOfException;
        this.messageType = messageType;
        this.id = id;
        this.message = message;
        this.throwingTime = throwingTime;
        this.fileName = fileName;
        this.isValid = isValid;
    }
}