package ee.annjakubel.videorental.service;

import ee.annjakubel.videorental.model.database.Film;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    //see l2heb ordercontrollerisse
    public int calculateInitialPrice(List<Film> films, int days) {
        int sum = 0;
        int daysToPay = 1;
        for (Film f : films) {

            if (f.getType().equals("OLD")) {
                if (days > 5) {
                    daysToPay = days - 4;
                }
            }

            if (f.getType().equals("BASIC")) {
                if (days > 3) {
                    daysToPay = days - 2;
                }
            }

            if (f.getType().equals("PREMIUM")) {
                daysToPay = days;
            }

            sum = sum + (f.getPrice() * daysToPay);
        }

        return sum;
    }


}
