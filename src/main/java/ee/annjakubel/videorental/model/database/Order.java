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
public class Order {

    @Id
    private Long orderNumber;
    @OneToOne
    private Person person;
    @OneToMany
    private List<Film> films;
    private int days;
    private int initialPrice;
    private boolean returned;
}
