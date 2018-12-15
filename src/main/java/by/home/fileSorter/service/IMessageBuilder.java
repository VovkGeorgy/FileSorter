package by.home.fileSorter.service;

import by.home.fileSorter.entity.ErrorMessage;

import java.util.List;

/**
 * Interface declare method for Messages entity builders
 *
 * @param <T> type of Messages
 */
public interface IMessageBuilder<T> {

    /**
     * Method build entities of passing type
     *
     * @return entities
     */
    List<T> build();
}
