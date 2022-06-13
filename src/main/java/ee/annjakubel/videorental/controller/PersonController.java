package ee.annjakubel.videorental.controller;

import ee.annjakubel.videorental.model.database.Person;
import ee.annjakubel.videorental.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @PostMapping("videorental/person")
    public ResponseEntity<List<Person>> addPerson(@RequestBody Person person) {
        personRepository.save(person);
        return ResponseEntity.ok().body(personRepository.findAll());
    }

    @GetMapping("videorental/person")
    public ResponseEntity<List<Person>> getAllPeople() {
        return ResponseEntity.ok().body(personRepository.findAll());
    }


    /*@PutMapping("videorental/addPoints") {
        //arvuta v2lja orderi p6hjal oubktid??
    }*/


}
