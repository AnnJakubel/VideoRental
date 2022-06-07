package ee.annjakubel.videorental.controller;

import ee.annjakubel.videorental.model.database.Film;
import ee.annjakubel.videorental.model.database.Order;
import ee.annjakubel.videorental.model.database.Person;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

public class OrderController {

    @PostMapping
    public void takeNewOrder(@RequestBody Person person) {
        Order order = new Order();
        List<Film> filmsToRent = new ArrayList<>();
        //kontrollida yle kas klient juba andmebaasis
        //vaadata ID situatsioon yle
        order.setPerson(person);
    }

    //
}
