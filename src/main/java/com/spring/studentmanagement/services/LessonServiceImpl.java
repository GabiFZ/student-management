package com.spring.studentmanagement.services;

import com.spring.studentmanagement.exceptions.CourseNotFoundException;
import com.spring.studentmanagement.exceptions.LessonNotFoundException;
import com.spring.studentmanagement.models.Course;
import com.spring.studentmanagement.models.Lesson;
import com.spring.studentmanagement.repositories.CourseRepository;
import com.spring.studentmanagement.repositories.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

            private final LessonRepository lessonRepository;
            private final CourseRepository courseRepository;

    @Override
    public Lesson addLesson(Lesson lesson) {
        Course course = courseRepository.findById(lesson.getCourse().getId()).orElseThrow(() -> new CourseNotFoundException
                ("Course with id: " + lesson.getCourse().getId() +  "not found"));
        lesson.setCourse(course);
        return lessonRepository.save(lesson);
    }


    @Override
    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    @Override
    public Lesson getLessonById(Long id) {
        return lessonRepository.findById(id).orElseThrow(() -> new LessonNotFoundException("Lesson with id: " + id + " not found"));


    }

    @Override
    public Lesson updateLesson(Lesson lesson, Long id) {
        return lessonRepository.findById(id).map(existingLesson -> {
            existingLesson.setTitle(lesson.getTitle());
            existingLesson.setContent(lesson.getContent());
            existingLesson.setVideoUrl(lesson.getVideoUrl());
            existingLesson.setOrderIndex(lesson.getOrderIndex());
            existingLesson.setDurationMinutes(lesson.getDurationMinutes());
            existingLesson.setIsFree(lesson.getIsFree());
            existingLesson.setCourse(lesson.getCourse());
            return lessonRepository.save(existingLesson);
        }).orElseThrow(() -> new LessonNotFoundException("Lesson with id: " + id + " not found"));
    }

    @Override
    public void deleteLesson(Long id) {
        if (!lessonRepository.existsById(id)) {
            throw new LessonNotFoundException("Lesson with id: " + id + " not found");
        }
        lessonRepository.deleteById(id);
    }

    @Override
    public List<Lesson> getLessonsByCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course with id: " + courseId + " not found"));
        return lessonRepository.findByCourse(course);
    }

    @Override
    public List<Lesson> getLessonsByCourseOrdered(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course with id: " + courseId + " not found"));
        return lessonRepository.findByCourseOrderByOrderIndexAsc(course);
    }

    @Override
    public long countLessonsByCourse(Long courseId) {
        if (!courseRepository.existsById(courseId)) {
            throw new CourseNotFoundException("Course with id: " + courseId + " not found");
        }

        return lessonRepository.countByCourseId(courseId);
    }
}
