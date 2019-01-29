package by.home.fileSorter.repository;

import by.home.fileSorter.entity.ErrorMessage;
import org.springframework.data.repository.CrudRepository;

/**
 * JPA repository for error message entity
 */
public interface ErrorRepository extends CrudRepository<ErrorMessage, Long> {
}
