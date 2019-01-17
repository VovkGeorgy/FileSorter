package by.home.fileSorter.entity;

import lombok.Data;

/**
 * Cass of abstract message entity
 */
@Data
public class AbstractMessage {
    protected String messageType;
    protected Long id;
    protected String message;
    protected String throwingTime;
    protected String fileName;
    protected boolean isValid;
}
