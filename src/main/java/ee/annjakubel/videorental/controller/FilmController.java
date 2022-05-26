package ee.annjakubel.videorental.controller;

import ee.annjakubel.videorental.model.database.Film;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FilmController {

    //Filmide lisamine andmebaasi, eemaldamine, muutmin
    //V2ljarentiminse output
    //Tagastus(return) output

    //Rendihindade arvutamine (kokku + yle aja)

    @PostMapping
    public void filmToDb(@RequestBody Film film) {

    }
}
