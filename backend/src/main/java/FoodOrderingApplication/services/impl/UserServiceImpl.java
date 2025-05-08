package FoodOrderingApplication.services.impl;

import FoodOrderingApplication.Config.JwtProvider;
import FoodOrderingApplication.model.User;
import FoodOrderingApplication.repository.UserRepository;
import FoodOrderingApplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserByJwtToken(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        User user = findUserByEmail(email);
        return user;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if(user==null) throw new Exception("User does not exist the following email Id : \n"+email);
        return user;
    }
}
