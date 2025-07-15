package com.springboot.blog.Security;

import com.springboot.blog.Repository.CustomerRepository;
import com.springboot.blog.model.Customer;
import com.springboot.blog.model.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;
    @Autowired
    public CustomUserDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByUserNameOrEmail(usernameOrEmail , usernameOrEmail).orElseThrow(
                () -> new UsernameNotFoundException("Username or Email not found")
        );

        return new User(customer.getEmail() , customer.getPassword() , getAuthorities(customer.getRoles()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Set<Roles> roles) {
       return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
