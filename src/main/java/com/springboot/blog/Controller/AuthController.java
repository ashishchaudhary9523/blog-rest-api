package com.springboot.blog.Controller;

import com.springboot.blog.Repository.CustomerRepository;
import com.springboot.blog.Repository.RoleRepository;
import com.springboot.blog.Security.JWTTokenProvider;
import com.springboot.blog.model.Customer;
import com.springboot.blog.model.Roles;
import com.springboot.blog.payload.JWTAuthResponse;
import com.springboot.blog.payload.LoginDTO;
import com.springboot.blog.payload.SignupDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTTokenProvider jwtTokenProvider;

    @PostMapping("/sign-in")
    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDTO loginDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUserNameOrEmail() ,
                loginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTAuthResponse(token));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupDTO signupDTO) {
        if(customerRepository.existsCustomerByUserName(signupDTO.getUserName())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }
        if(customerRepository.existsCustomerByEmail(signupDTO.getEmail())){
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        Customer customer = new Customer();
        customer.setName(signupDTO.getName());
        customer.setEmail(signupDTO.getEmail());
        customer.setUserName(signupDTO.getUserName());
        customer.setPassword(passwordEncoder.encode(signupDTO.getPassword()));
        Roles role = roleRepository.findByName("ROLE_ADMIN").get();
        customer.setRoles(Collections.singleton(role));

        customerRepository.save(customer);

        return new ResponseEntity<>("successfully sign-up!", HttpStatus.OK);
    }

}
