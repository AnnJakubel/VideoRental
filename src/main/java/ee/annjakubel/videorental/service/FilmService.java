package ee.annjakubel.videorental.service;

import ee.annjakubel.videorental.model.FilmType;
import ee.annjakubel.videorental.model.database.Film;
import org.springframework.stereotype.Service;

@Service
public class FilmService {

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
}
