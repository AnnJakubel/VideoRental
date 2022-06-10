package ee.annjakubel.videorental.repository;

import ee.annjakubel.videorental.model.database.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
