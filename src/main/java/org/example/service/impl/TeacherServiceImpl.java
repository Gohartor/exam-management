package org.example.service.impl;

import org.example.entity.Course;
import org.example.entity.Exam;
import org.example.entity.Question;
import org.example.entity.person.Teacher;
import org.example.repository.TeacherRepository;
import org.example.service.TeacherService;
import org.example.service.base.BaseServiceImpl;

import java.util.List;

public class TeacherServiceImpl
        extends BaseServiceImpl<Teacher, Long, TeacherRepository>
        implements TeacherService {

    public TeacherServiceImpl(TeacherRepository repository) {
        super(repository);
    }

    @Override
    public List<Teacher> findByCourseId(Long courseId) {
        return repository.findByCourseId(courseId);
    }

    @Override
    public List<Teacher> searchByNameOrCode(String keyword) {
        return repository.searchByNameOrTeacherCode(keyword);
    }

    @Override
    public List<Course> getCourses(Long teacherId) {
        return List.of();
    }

    @Override
    public List<Exam> getExams(Long teacherId) {
        return List.of();
    }

    @Override
    public void createExam(Long teacherId, Long courseId, Exam exam) {

    }

    @Override
    public void addQuestionToExam(Long examId, Question question) {

    }

    @Override
    public List<Question> getAllQuestions(Long teacherId) {
        return List.of();
    }

    @Override
    public List<Exam> getExamsOfCourse(Long courseId) {
        return List.of();
    }
}