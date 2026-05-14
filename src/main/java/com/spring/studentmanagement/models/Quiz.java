package com.spring.studentmanagement.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.studentmanagement.enums.QuizStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "quizzes")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "time_limit")
    private Integer timeLimit;

    @Enumerated(EnumType.STRING)
    @Column
    private QuizStatus status = QuizStatus.ACTIVE;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;


     @JsonIgnore
     @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
     private List<Question> questions = new ArrayList<>();

}
