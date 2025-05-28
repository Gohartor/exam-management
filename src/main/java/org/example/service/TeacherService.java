package org.example.service;

import org.example.entity.Course;
import org.example.entity.Exam;
import org.example.entity.Question;
import org.example.entity.person.Teacher;
import org.example.service.base.BaseService;

import java.util.List;

public interface TeacherService extends BaseService<Teacher, Long> {

    List<Teacher> findByCourseId(Long courseId);
    List<Teacher> searchByNameOrCode(String keyword);

    List<Course> getCourses(Long teacherId);
    List<Exam> getExams(Long teacherId);
    void createExam(Long teacherId, Long courseId, Exam exam);
    void addQuestionToExam(Long examId, Question question);
    List<Question> getAllQuestions(Long teacherId); // بانک سوالات
    List<Exam> getExamsOfCourse(Long courseId);
}