package ee.annjakubel.videorental.controller;

import ee.annjakubel.videorental.model.database.Film;
import ee.annjakubel.videorental.repository.FilmRepository;
import ee.annjakubel.videorental.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FilmController {

    //Filmide lisamine andmebaasi, eemaldamine, muutmin
    //V2ljarentiminse output
    //Tagastus(return) output

    //Rendihindade arvutamine (kokku + yle aja)

    @Autowired
    FilmService filmService;

    @Autowired
    FilmRepository filmRepository;

    //Teha
    @PostMapping
    public void filmToDb(@RequestBody Film film) {

    }


    // Vist ok
    @PutMapping
    public ResponseEntity<Film> editFilm(@RequestBody Film film) {
        filmService.updateFilm(film);
        filmService.setPrice(film);
        filmRepository.save(film);
        return ResponseEntity.ok().body(filmRepository.findById(film.getId()).get());
    }

    //Teha
    @DeleteMapping
}
