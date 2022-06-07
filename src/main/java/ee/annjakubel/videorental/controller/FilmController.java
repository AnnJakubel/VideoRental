package ee.annjakubel.videorental.controller;

import ee.annjakubel.videorental.model.database.Film;
import ee.annjakubel.videorental.repository.FilmRepository;
import ee.annjakubel.videorental.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FilmController {

    //Hindade arvutamise systeem
        //Ka OrderService alla?

    // Tellimuse arvutamine
        //Order
        //OrderController?
        //OrderService?

    //Kliendibaas
        //Esialgu basic: nimi, ID, orderite massiiv?, boonuspunktid
    //Klient - staatus laenatud film
        //   - staatus tagastatud film
            // hetkene kuup2ev (DateUtils?) tellimuse tegemisel
            // ja tagastusel
            // et arvutada p2evade vahe ja lisap2evad
                // lisap2evade p6hjal hind
        //tellimusele vaja ID eraldi kylge
    //Boonuspunktid

    @Autowired
    FilmService filmService;

    @Autowired
    FilmRepository filmRepository;

    //Vist ok
    @PostMapping("videorental")
    public ResponseEntity<List<Film>> saveFilm(@RequestBody Film film) {
        filmRepository.save(film);
        filmService.updateFilm(film);
        filmService.setPrice(film);
        filmRepository.save(film);
        return ResponseEntity.ok()
                .body(filmRepository.findAll());
    }

    //Vist ok
    @GetMapping("videorental")
    public ResponseEntity<List<Film>> getAllFilms() {

        return ResponseEntity.ok().body(filmRepository.findAll());
    }


    // Vist ok
    @PutMapping("videorental")
    public ResponseEntity<Film> editFilm(@RequestBody Film film) {
        filmService.updateFilm(film);
        filmService.setPrice(film);
        filmRepository.save(film);
        return ResponseEntity.ok().body(filmRepository.findById(film.getId()).get());
    }

    //Vist ok
    @DeleteMapping("videorental/{id}")
    public ResponseEntity<List<Film>> deleteFilm(@PathVariable Long id) {
        filmRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(filmRepository.findAll());
    }
}
