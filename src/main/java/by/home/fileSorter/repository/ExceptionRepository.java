package by.home.fileSorter.repository;

import by.home.fileSorter.entity.ExceptionMessage;
import org.springframework.data.repository.CrudRepository;

/**
 * JPA repository for exception message entity
 */
public interface ExceptionRepository extends CrudRepository<ExceptionMessage, Long> {
}
