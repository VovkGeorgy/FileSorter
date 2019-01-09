package by.home.fileSorter.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Exception message entity
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ExceptionMessage extends AbstractMessage {
    private String typeOfException;
}