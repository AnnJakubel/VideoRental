package ee.annjakubel.videorental.service;

import ee.annjakubel.videorental.model.database.Film;
import ee.annjakubel.videorental.model.database.Order;
import ee.annjakubel.videorental.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    FilmRepository filmRepository;

    //see l2heb ordercontrollerisse
    public int calculateInitialPrice(List<Film> films, int days) {
        int sum = 0;
        int daysToPay = 1;
        for (Film f : films) {

            if (f.getType().equals("OLD")) {
                if (days > 5) {
                    daysToPay = days - 4;
                }
            }
            if (f.getType().equals("BASIC")) {
                if (days > 3) {
                    daysToPay = days - 2;
                }
            }
            if (f.getType().equals("PREMIUM")) {
                daysToPay = days;
            }
            sum = sum + (f.getPrice() * daysToPay);
        }

        return sum;
    }

    public int calculateBonusPoints(Order order) {
        List<Film> films = order.getFilms();
        int bonusPoints = 0;
        for (Film f : films) {
            if (f.getType().equals("PREMIUM")) {
                bonusPoints += 2;
            } else {
                bonusPoints++;
            }
        }
        return bonusPoints;
    }

    public void addFilmToList(Order order, Film film) {
        List<Film> films = order.getFilms();
        Film filmMatch = null;
        if (filmRepository.existsById(film.getTitle())) {
            filmMatch = filmRepository.findById(film.getTitle()).get();
        }
        films.add(filmMatch);

    }


    public int calculateExtraFee(int daysOver, List<Film> films) {
        int sum = 0;
        for (Film f : films) {
            if (f.getType().equals("OLD") || f.getType().equals("BASIC")) {
                sum = sum + (3 * daysOver);
            }

            if (f.getType().equals("PREMIUM")) {
                sum = sum + (4 * daysOver);
            }
        }
        return sum;
    }
}
