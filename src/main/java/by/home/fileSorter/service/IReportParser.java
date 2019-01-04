package by.home.fileSorter.service;

import by.home.fileSorter.entity.AbstractMessage;

import java.io.File;

/**
 * Declare method for parsers
 */
public interface IReportParser<T extends AbstractMessage> {

    T parseFile(File file);
}
