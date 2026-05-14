package com.spring.studentmanagement.models;


import com.spring.studentmanagement.enums.AnswerOption;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;

    @Column(name = "option_a", nullable = false)
    private String optionA;

    @Column(name = "option_b", nullable = false)
    private String optionB;

    @Column(name = "option_c", nullable = false)
    private String optionC;

    @Column(name = "option_d", nullable = false)
    private String optionD;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "correct_answer", nullable = false)
//    private AnswerOption correctAnswer;

    @Column(nullable = false)
    private String correctAnswers; // Vom stoca ca "A,B,D" (comma-separated)

    @Column(nullable = false)
    private Integer points = 1;

    @ManyToOne
    @JoinColumn(name = "quiz_id",  nullable = false)
    private Quiz quiz;
}
