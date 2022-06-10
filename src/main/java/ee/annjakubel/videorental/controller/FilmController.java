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



    //Kliendibaas
        //Esialgu basic: nimi, ID, orderite massiiv?, boonuspunktid
    //Klient - staatus laenatud film
        //   - staatus tagastatud film
            // hetkene kuup2ev (DateUtils?) tellimuse tegemisel
            // ja tagastusel
            // et arvutada p2evade vahe ja lisap2evad
                // lisap2evade p6hjal hind
    //Boonuspunktid

    @Autowired
    FilmService filmService;

    @Autowired
    FilmRepository filmRepository;

    //OK
    @PostMapping("videorental")
    public ResponseEntity<List<Film>> saveFilm(@RequestBody Film film) {
        filmRepository.save(film);
        filmService.updateFilm(film);
        filmService.setPrice(film);
        filmRepository.save(film);
        return ResponseEntity.ok()
                .body(filmRepository.findAll());
    }

    //OK
    @GetMapping("videorental")
    public ResponseEntity<List<Film>> getAllFilms() {
        return ResponseEntity.ok().body(filmRepository.findAll());
    }


    // OK
    @PutMapping("videorental")
    public ResponseEntity<Film> editFilm(@RequestBody Film film) {
        filmService.updateFilm(film);
        filmService.setPrice(film);
        filmRepository.save(film);
        return ResponseEntity.ok().body(filmRepository.findById(film.getTitle()).get());
    }

    //OK
    @DeleteMapping("videorental")
    public ResponseEntity<List<Film>> deleteFilm(@RequestBody Film film) {
        filmRepository.deleteById(film.getTitle());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(filmRepository.findAll());
    }
}
