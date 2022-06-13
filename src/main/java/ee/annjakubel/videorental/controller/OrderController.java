package ee.annjakubel.videorental.controller;

import ee.annjakubel.videorental.model.database.Film;
import ee.annjakubel.videorental.model.database.Order;
import ee.annjakubel.videorental.model.database.Person;
import ee.annjakubel.videorental.repository.FilmRepository;
import ee.annjakubel.videorental.repository.OrderRepository;
import ee.annjakubel.videorental.repository.PersonRepository;
import ee.annjakubel.videorental.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("videorental/order/{personId}")
    public String startNewOrder(@RequestBody Order order, @PathVariable Long personId) {
        Person personFromDb = personRepository.findById(personId).get();
        order.setPerson(personFromDb);
        List<Film> filmsToRent = new ArrayList<>();
        order.setFilms(filmsToRent);
        order.setInitialPrice(0);
        order.setReturned(false);
        orderRepository.save(order);
        return "Order number " + order.getOrderNumber() + " started.";
    }

    @GetMapping("videorental/order/{orderNumber}")
    public ResponseEntity<Order> getOrder(@PathVariable Long orderNumber) {
        return ResponseEntity.ok()
                .body(orderRepository.findById(orderNumber).get());
    }

    @PutMapping("videorental/order/{orderNumber}")
    public void addFilmsToOrder(@RequestBody Film film, @PathVariable Long orderNumber) {
        Order orderFromDb = orderRepository.findById(orderNumber).get(); //leiab orderi
        orderService.addFilmToList(orderFromDb, film); //lisab filmi
        int initialPrice =  orderService.calculateInitialPrice(orderFromDb.getFilms(), orderFromDb.getDays());
        orderFromDb.setInitialPrice(initialPrice); //lisab hinna
        int bonusPoints = orderService.calculateBonusPoints(orderFromDb);
        //boonuspunktid peavad LISANDUMA kliendile olemasolevatele.
        orderRepository.save(orderFromDb); //salvestab
    }


}
