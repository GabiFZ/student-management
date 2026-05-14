package com.spring.studentmanagement.repositories;

import com.spring.studentmanagement.models.Question;
import com.spring.studentmanagement.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {

    List<Question> findByQuiz(Quiz quiz);

    boolean existsByTextAndQuiz(String text, Quiz quiz);

    long countByQuiz(Quiz quiz);
}
