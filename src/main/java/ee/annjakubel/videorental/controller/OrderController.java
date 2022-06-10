package ee.annjakubel.videorental.controller;

import ee.annjakubel.videorental.model.database.Film;
import ee.annjakubel.videorental.model.database.Order;
import ee.annjakubel.videorental.model.database.Person;
import ee.annjakubel.videorental.repository.FilmRepository;
import ee.annjakubel.videorental.repository.OrderRepository;
import ee.annjakubel.videorental.repository.PersonRepository;
import ee.annjakubel.videorental.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    FilmRepository filmRepository;

    @Autowired
    PersonRepository personRepository;

    @PostMapping("videorental/order")
    public void startNewOrder(@RequestBody Person person) {
        Order order = new Order();
        List<Film> filmsToRent = new ArrayList<>();
        order.setFilms(filmsToRent);
        if (personRepository.existsById(person.getId())) {
            order.setPerson(person);
        } else {
            //return error - register person first
        }
    }

    @PutMapping("videorental/order/{orderNumber}")
    public void addFilmsToOrder(@RequestBody Film film, @PathVariable Long orderNumber) {
        Order orderFromDb = orderRepository.findById(orderNumber).get();
        orderService.addFilmToList(orderFromDb, film);
        int initialPrice =  orderService.calculateInitialPrice(orderFromDb.getFilms(), orderFromDb.getDays());
        orderFromDb.setInitialPrice(initialPrice);
        int bonusPoints = orderService.calculateBonusPoints(orderFromDb);
        //boonuspunktid peavad LISANDUMA kliendile olemasolevatele.
        
        orderRepository.save(orderFromDb);
    }

}
