package ee.annjakubel.videorental.controller;

import ee.annjakubel.videorental.model.database.Film;
import ee.annjakubel.videorental.model.database.Order;
import ee.annjakubel.videorental.model.database.Person;
import ee.annjakubel.videorental.repository.FilmRepository;
import ee.annjakubel.videorental.repository.OrderRepository;
import ee.annjakubel.videorental.repository.PersonRepository;
import ee.annjakubel.videorental.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<Order>> startNewOrder(@RequestBody Order order, @PathVariable Long personId) {
        Person personFromDb = personRepository.findById(personId).get();
        order.setPerson(personFromDb);
        List<Film> filmsToRent = new ArrayList<>();
        order.setFilms(filmsToRent);
        order.setInitialPrice(0);
        order.setReturned(false);
        orderRepository.save(order);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderRepository.findAll());
    }

    @GetMapping("videorental/order/{orderNumber}")
    public ResponseEntity<Order> getOrder(@PathVariable Long orderNumber) {
        return ResponseEntity.ok()
                .body(orderRepository.findById(orderNumber).get());
    }

    @GetMapping("videorental/order")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok()
                .body(orderRepository.findAll());
    }



    @PutMapping("videorental/order/{orderNumber}")
    public ResponseEntity<Order> addFilmsToOrder(@RequestBody Film film, @PathVariable Long orderNumber) {
        Order orderFromDb = orderRepository.findById(orderNumber).get();
        orderService.addFilmToList(orderFromDb, film);
        int initialPrice =  orderService.calculateInitialPrice(orderFromDb.getFilms(), orderFromDb.getDays());
        orderFromDb.setInitialPrice(initialPrice);
        orderRepository.save(orderFromDb);
        return ResponseEntity.ok()
                .body(orderRepository.findById(orderNumber).get());
    }

    //Vaja katsetada
    @PutMapping("videorental/order/complete")
    public ResponseEntity<Order> completeOrder(@RequestBody Order order) {
        Order orderFromDb = orderRepository.findById(order.getOrderNumber()).get();
        orderFromDb.setReturned(true);
        orderFromDb.setDaysOver(order.getDaysOver());
        List<Film> films = orderFromDb.getFilms();
        Person person = orderFromDb.getPerson();
        int bonusPoints = orderService.calculateBonusPoints(orderFromDb);
        person.setBonusPoints(bonusPoints);
        personRepository.save(person);
        int fee = orderService.calculateExtraFee(order.getDaysOver(), films);
        orderFromDb.setExtraFee(fee);
        orderRepository.save(orderFromDb);

        return ResponseEntity.ok()
                .body(orderRepository.findById(order.getOrderNumber()).get());
    }


}
