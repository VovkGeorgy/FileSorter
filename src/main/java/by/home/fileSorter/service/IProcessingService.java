package by.home.fileSorter.service;

import by.home.fileSorter.entity.AbstractMessage;

/**
 * Interface of entity processing services
 *
 * @param <T> type of message
 */
public interface IProcessingService<T extends AbstractMessage> {
    /**
     * Method process entity with services
     *
     * @param message input message
     * @return result of processing
     */
    boolean process(T message);
}
