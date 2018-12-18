package by.home.fileSorter.entity;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Entity of Error message, in whom we parse TXT files
 */
@Component
public class ExceptionMessage {

    private String messageType;
    private Long id;
    private String message;
    private String typeOfException;
    private LocalDate throwingTime;
    private String fileName;

    public ExceptionMessage() {
    }

    public ExceptionMessage(String messageType, Long id, String message, String typeOfException, LocalDate throwingTime, String fileName) {
        this.messageType = messageType;
        this.id = id;
        this.message = message;
        this.typeOfException = typeOfException;
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

    public String getTypeOfException() {
        return typeOfException;
    }

    public void setTypeOfException(String typeOfException) {
        this.typeOfException = typeOfException;
    }

    public LocalDate getThrowingTime() {
        return throwingTime;
    }

    public void setThrowingTime(LocalDate throwingTime) {
        this.throwingTime = throwingTime;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
