package by.home.fileSorter.service;

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
     * @param message
     */
    T build(String message);
}
