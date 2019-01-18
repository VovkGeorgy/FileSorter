package by.home.fileSorter.repository;

import by.home.fileSorter.entity.ErrorMessage;
import org.springframework.data.repository.CrudRepository;

public interface ErrorRepository extends CrudRepository<ErrorMessage, Long> {
}
