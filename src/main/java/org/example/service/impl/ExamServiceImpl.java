package org.example.service.impl;

import org.example.entity.Course;
import org.example.entity.Exam;
import org.example.entity.person.Teacher;
import org.example.exeption.AccessDenied;
import org.example.repository.ExamRepository;
import org.example.service.CourseService;
import org.example.service.ExamService;
import org.example.service.TeacherService;
import org.example.service.base.BaseServiceImpl;

import java.util.List;
import java.util.Optional;

public class ExamServiceImpl
        extends BaseServiceImpl<Exam, Long, ExamRepository>
        implements ExamService {

        private final CourseService courseService;
        private final TeacherService teacherService;

    public ExamServiceImpl(ExamRepository repository, CourseService courseService, TeacherService teacherService) {
        super(repository);
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    @Override
    public List<Exam> findByCourseId(Long courseId) {
        return repository.findByCourseId(courseId);
    }

    @Override
    public List<Exam> findByTeacherId(Long teacherId) {
        return repository.findByTeacherId(teacherId);
    }

    @Override
    public List<Exam> searchByTitle(String title) {
        return repository.searchByTitle(title);
    }

    @Override
    public List<Exam> findActiveExamsForStudent(Long studentId, int currentTime) {
        return repository.findActiveExamsForStudent(studentId, currentTime);
    }




    @Override
    public List<Exam> getExamsByCourse(Long courseId) {
        Course course = courseService.findById(courseId).orElseThrow();
        return repository.findByCourse(course);
    }




    @Override
    public List<Exam> getExamsByCourseAndTeacher(Long courseId, Long teacherId) {
        Optional<Course> courseOpt = courseService.findById(courseId);
        Optional<Teacher> teacherOpt = teacherService.findById(teacherId);
        if (courseOpt.isPresent() && teacherOpt.isPresent()) {
            return repository.findByCourseAndTeacher(courseOpt.get(), teacherOpt.get());
        }
        return List.of();
    }

    @Override
    public void createExam(Long courseId, Long teacherId, int duration) {
        Course course = courseService.findById(courseId).orElseThrow();
        Teacher teacher = teacherService.findById(teacherId).orElseThrow();
        Exam exam = new Exam();
        exam.setCourse(course);
        exam.setTeacher(teacher);

        exam.setDuration(duration);
        repository.save(exam);
    }

    @Override
    public Exam editExam(Long examId, int duration) {
        Exam exam = repository.findById(examId).orElseThrow();
        exam.setDuration(duration);
        return repository.save(exam);
    }

    @Override
    public void deleteExam(Long examId, Long teacherId) {
        Exam exam = repository.findById(examId).orElseThrow();
        if (!exam.getTeacher().getId().equals(teacherId)) {
            throw new AccessDenied("access denied for delete this exam");
        }
        repository.deleteById(examId);
    }

}