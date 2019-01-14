package by.home.fileSorter.entity;

import lombok.Data;

/**
 * Cass of abstract message entity
 */
@Data
public class AbstractMessage {
    private String messageType;
    private Long id;
    private String message;
    private String throwingTime;
    private String fileName;
    private boolean isValid;
}
