package by.home.fileSorter.service;

import by.home.fileSorter.entity.AbstractMessage;

public interface IProcessingService<T extends AbstractMessage> {
    boolean process(T message);
}
