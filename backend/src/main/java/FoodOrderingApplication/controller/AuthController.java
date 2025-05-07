package com.project.Online.Food.Ordering.backend.controller;

import com.project.Online.Food.Ordering.backend.Config.JwtProvider;
import com.project.Online.Food.Ordering.backend.model.Cart;
import com.project.Online.Food.Ordering.backend.model.Role;
import com.project.Online.Food.Ordering.backend.model.User;
import com.project.Online.Food.Ordering.backend.request.LoginRequest;
import com.project.Online.Food.Ordering.backend.response.AuthResponse;
import com.project.Online.Food.Ordering.backend.repository.CartRepository;
import com.project.Online.Food.Ordering.backend.repository.UserRepository;
import com.project.Online.Food.Ordering.backend.services.impl.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Autowired
    private CartRepository cartRepository;


    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user)
    throws Exception {
        User isEmailExist = userRepository.findByEmail(user.getEmail());
        System.out.println("========signup====");
        AuthResponse authResponse = new AuthResponse();

        if(isEmailExist!=null){
            authResponse.setMessage("Email is already used with another account.");
            return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);
        }

        User createdUser = User.builder()
                .email(user.getEmail())
                .fullName(user.getFullName())
                .password(passwordEncoder.encode(user.getPassword()))
                .role(user.getRole())
                .build();

        User savedUser  = userRepository.save(createdUser);

        Cart cart = new Cart();
        cart.setCustomer(savedUser);
        cartRepository.save(cart);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);
        authResponse.setJwt(jwt);
        authResponse.setMessage("Register Success");
        authResponse.setRole(savedUser.getRole());
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest loginRequest){
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Authentication authentication = authenticate(username,password);
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();

        String jwt = jwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setRole(Role.valueOf(role));
        authResponse.setMessage("Login Success");
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    public Authentication authenticate(String username, String password) {
        UserDetails userDetails = customUserDetailService.loadUserByUsername(username);
        if(userDetails==null){
            throw new BadCredentialsException("Invaid username....");
        }

        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Invaid username....");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}
