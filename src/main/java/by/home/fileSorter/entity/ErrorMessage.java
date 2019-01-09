package by.home.fileSorter.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Error message entity
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ErrorMessage extends AbstractMessage {
    private String typeOfError;
}