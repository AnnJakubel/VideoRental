package ee.annjakubel.videorental.repository;

import ee.annjakubel.videorental.model.database.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Long> {
}
