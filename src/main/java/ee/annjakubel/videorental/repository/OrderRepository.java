package ee.annjakubel.videorental.repository;

import ee.annjakubel.videorental.model.database.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
}
