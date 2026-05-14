package com.spring.studentmanagement.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "Professor")
@Table(name = "professors")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 65, unique = false, nullable = false, name = "firstname")
    private String firstName;

    @Column(length = 65 , unique = false, nullable = false, name = "lastname")
    private String lastName;

    @Column(name = "username", length = 45, unique = true, nullable = false)
    private String username;

    @Column(unique = true, length = 65, nullable = false, name = "email")
    private String email;

    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @Column(name = "phone", length = 15, nullable = true)
    private String phone;

    @Column(name = "bio" , columnDefinition = "TEXT")
    private String bio;

    @Column(name = "avatar_url" , nullable = false)
    private String avatarUrl;

    @Column(columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean isActive;

    @CreationTimestamp
    private LocalDateTime createdAt;


    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @JsonIgnore
    @OneToMany(mappedBy = "professor")
    private List<Course> courses = new ArrayList<>();
}
