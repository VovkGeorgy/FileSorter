package by.home.fileSorter.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * Exception message entity
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ExceptionMessage extends AbstractMessage {
    private String messageType;
    private Long id;
    private String message;
    private String typeOfException;
    private LocalDate throwingTime;
    private String fileName;
    private boolean valid;
}