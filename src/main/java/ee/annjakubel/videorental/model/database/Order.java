package ee.annjakubel.videorental.model.database;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long orderNumber;
    @ManyToOne
    private Person person;
    @OneToMany
    private List<Film> films;
    private int days;
    private int daysOver;
    private int initialPrice;
    private int extraFee;
    private boolean returned;
}
