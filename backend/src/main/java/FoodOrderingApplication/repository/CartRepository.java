package FoodOrderingApplication.repository;

import FoodOrderingApplication.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    public Cart findByCustomerId(Long userID);

}
