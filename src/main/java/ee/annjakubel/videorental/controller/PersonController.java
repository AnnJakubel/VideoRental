package ee.annjakubel.videorental.controller;

import ee.annjakubel.videorental.model.database.Person;
import ee.annjakubel.videorental.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @PostMapping("videorental/person")
    public void addPerson(@RequestBody Person person) {
        personRepository.save(person);
    }

    /*@PutMapping("videorental/addPoints") {
        //arvuta v2lja orderi p6hjal oubktid??
    }*/


}
