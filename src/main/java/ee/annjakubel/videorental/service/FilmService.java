package ee.annjakubel.videorental.service;

import ee.annjakubel.videorental.model.FilmType;
import ee.annjakubel.videorental.model.database.Film;
import ee.annjakubel.videorental.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmService {

    @Autowired
    FilmRepository filmRepository;

    //setPrice l2heb controllerisse kohe kui v6tab body vastu
    public void setPrice(Film film) {
        if (film.getType() == FilmType.OLD) {
            film.setPrice(1);
        } else if (film.getType() == FilmType.BASIC) {
            film.setPrice(3);
        } else if (film.getType() == FilmType.PREMIUM) {
            film.setPrice(4);
        } else {
            film.setPrice(0);
        }
    }

    public Film updateFilm(Film film) {
        Film updatedFilm = filmRepository.findById(film.getId()).get();
        updatedFilm.setType(film.getType());
        return updatedFilm;

    }
}
