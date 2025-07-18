package com.springboot.blog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "posts" , uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title" , nullable = false)
    private String title;

    @Column(name="description" , nullable = false)
    private String description;

    @Column(name="content" , nullable = false)
    private String content;

    @Column(name="image_name")
    private String imageName;
    @Column(name="image_type")
    private String imageType;

    @Lob
    @Column(name="image_data")
    private byte[] imageData;

    @OneToMany(mappedBy = "post" , cascade = CascadeType.ALL , orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

}
