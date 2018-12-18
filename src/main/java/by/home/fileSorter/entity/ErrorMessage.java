package by.home.fileSorter.entity;

import java.time.LocalDate;

/**
 * Entity of Error message, in whom we parse JSON files
 */
public class ErrorMessage {

    private String messageType;
    private Long id;
    private String message;
    private String typeOfError;
    private String throwingTime;
    private String fileName;

    public ErrorMessage() {
    }

    public ErrorMessage(String messageType, Long id, String message, String typeOfError, String throwingTime, String fileName) {
        this.messageType = messageType;
        this.id = id;
        this.message = message;
        this.typeOfError = typeOfError;
        this.throwingTime = throwingTime;
        this.fileName = fileName;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTypeOfError() {
        return typeOfError;
    }

    public void setTypeOfError(String typeOfError) {
        this.typeOfError = typeOfError;
    }

    public String getThrowingTime() {
        return throwingTime;
    }

    public void setThrowingTime(String throwingTime) {
        this.throwingTime = throwingTime;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
