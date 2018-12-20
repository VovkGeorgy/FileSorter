package by.home.fileSorter.service;

/**
 * Interface declare method for message entity builders
 *
 * @param <T> type of Messages
 */
public interface IBuilder<T, P> {

    /**
     * Method build entities of passing type
     *
     * @param parameter input parameter
     * @return entity
     */
    T build(P parameter);
}
