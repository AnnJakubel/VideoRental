package ee.annjakubel.videorental.repository;

import ee.annjakubel.videorental.model.database.Order;
import org.springframework.data.jpa.repository.JpaRepository;



public interface OrderRepository extends JpaRepository<Order, Long>{
}
