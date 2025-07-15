package com.springboot.blog.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "customer" , uniqueConstraints = {@UniqueConstraint(columnNames = {"userName"}) ,
                                                @UniqueConstraint(columnNames = {"email"})})
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String userName;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "customer_role" ,
                joinColumns = @JoinColumn(name = "customer_id" , referencedColumnName = "id") ,
                inverseJoinColumns = @JoinColumn(name = "role_id" , referencedColumnName = "id")
                )
    private Set<Roles> roles;
}
