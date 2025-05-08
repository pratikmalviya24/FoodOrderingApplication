package FoodOrderingApplication.response;

import FoodOrderingApplication.model.Role;
import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String message;
    private Role role;
}
