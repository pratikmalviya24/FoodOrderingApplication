package FoodOrderingApplication.request;

import FoodOrderingApplication.model.Address;
import lombok.Data;

@Data
public class OrderRequest {
    private Long restaurtantId;
    private Address deliveryAddress;
}
