package org.example.context;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.example.entity.*;
import org.example.entity.person.Admin;
import org.example.entity.person.Student;
import org.example.entity.person.Teacher;
import org.example.repository.*;
import org.example.repository.implement.*;
import org.example.service.*;
import org.example.service.impl.*;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import java.util.Objects;

public class ApplicationContext {

    private static ApplicationContext instance;
    private ValidatorFactory validatorFactory;
    private Validator validator;

    private ApplicationContext() {
    }

    public static ApplicationContext getInstance() {
        if (instance == null) {
            instance = new ApplicationContext();
        }
        return instance;
    }

    private EntityManagerFactory emf;

    public EntityManagerFactory getEmf() {
        if (Objects.isNull(emf)) {
            emf = Persistence.createEntityManagerFactory("default");
        }
        return emf;
    }

    private EntityManager em;

    public EntityManager getEntityManager() {
        if (Objects.isNull(em)) {
            em = getEmf().createEntityManager();
        }
        return em;
    }


    private ValidatorFactory getValidatorFactory() {
        if (validatorFactory == null) {
            validatorFactory = Validation.byDefaultProvider()
                    .configure()
                    .messageInterpolator(new ParameterMessageInterpolator())
                    .buildValidatorFactory();
        }
        return validatorFactory;
    }

    public Validator getValidator() {
        if (validator == null) {
            validator = getValidatorFactory().getValidator();
        }
        return validator;
    }


    private AdminRepository adminRepository;

    public AdminRepository getAdminRepository() {
        if (Objects.isNull(adminRepository)) {
            adminRepository = new AdminRepositoryImpl(getEntityManager(), Admin.class);
        }
        return adminRepository;
    }


    private CourseRepository courseRepository;

    public CourseRepository getCourseRepository() {
        if (Objects.isNull(courseRepository)) {
            courseRepository = new CourseRepositoryImpl(getEntityManager(), Course.class);
        }
        return courseRepository;
    }


    private ExamRepository examRepository;

    public ExamRepository getExamRepository() {
        if (Objects.isNull(examRepository)) {
            examRepository = new ExamRepositoryImpl(getEntityManager(), Exam.class);
        }
        return examRepository;
    }


    private OptionRepository optionRepository;

    public OptionRepository getOptionRepository() {
        if (Objects.isNull(optionRepository)) {
            optionRepository = new OptionRepositoryImpl(getEntityManager(), Option.class);
        }
        return optionRepository;
    }


    private QuestionsBankRepository questionsBankRepository;

    public QuestionsBankRepository getQuestionBankRepository() {
        if (Objects.isNull(questionsBankRepository)) {
            questionsBankRepository = new QuestionsBankRepositoryImpl(getEntityManager(), QuestionsBank.class);
        }
        return questionsBankRepository;
    }


    private QuestionRepository questionRepository;

    public QuestionRepository getQuestionRepository() {
        if (Objects.isNull(questionRepository)) {
            questionRepository = new QuestionRepositoryImpl(getEntityManager(), Question.class);
        }
        return questionRepository;
    }


    private StudentRepository studentRepository;

    public StudentRepository getStudentRepository() {
        if (Objects.isNull(studentRepository)) {
            studentRepository = new StudentRepositoryImpl(getEntityManager(), Student.class);
        }
        return studentRepository;
    }


    private TeacherRepository teacherRepository;

    public TeacherRepository getTeacherRepository() {
        if (Objects.isNull(teacherRepository)) {
            teacherRepository = new TeacherRepositoryImpl(getEntityManager(), Teacher.class);
        }
        return teacherRepository;
    }


    private AdminService adminService;

    public AdminService getAdminService() {
        if (Objects.isNull(adminService)) {
            adminService = new AdminServiceImpl(getAdminRepository(), getStudentService(), getTeacherService(), getCourseService());
        }
        return adminService;
    }

    private CourseService courseService;

    public CourseService getCourseService() {
        if (Objects.isNull(courseService)) {
            courseService = new CourseServiceImpl(getCourseRepository(), getTeacherService());
        }
        return courseService;
    }

    private ExamService examService;

    public ExamService getExamService() {
        if (Objects.isNull(examService)) {
            examService = new ExamServiceImpl(getExamRepository(), getCourseService(), getTeacherService());
        }
        return examService;
    }


    private OptionService optionService;

    public OptionService getOptionService() {
        if (Objects.isNull(optionService)) {
            optionService = new OptionServiceImpl(getOptionRepository());
        }
        return optionService;
    }


    private QuestionService questionService;

    public QuestionService getQuestionService() {
        if (Objects.isNull(questionService)) {
            questionService = new QuestionServiceImpl(getQuestionRepository(), getExamService(), getQuestionsBankService());
        }
        return questionService;
    }

    private QuestionsBankService questionsBankService;

    public QuestionsBankService getQuestionsBankService() {
        if (Objects.isNull(questionsBankService)) {
            questionsBankService = new QuestionsBankServiceImpl(getQuestionBankRepository());
        }
        return questionsBankService;
    }


    private StudentService studentService;

    public StudentService getStudentService() {
        if (Objects.isNull(studentService)) {
            studentService = new StudentServiceImpl(getStudentRepository());
        }
        return studentService;
    }

    private TeacherService teacherService;

    public TeacherService getTeacherService() {
        if (Objects.isNull(teacherService)) {
            teacherService = new TeacherServiceImpl(getTeacherRepository(), getCourseService());
        }
        return teacherService;
    }

    private StudentExamRepository studentExamRepository;

    public StudentExamRepository getStudentExamRepository() {
        if (Objects.isNull(studentExamRepository)) {
            studentExamRepository = new StudentExamRepositoryImpl(getEntityManager(), StudentExam.class);
        }
        return studentExamRepository;
    }

    private StudentExamService studentExamService;

    public StudentExamService getStudentExamService() {
        if (Objects.isNull(studentExamService)) {
            studentExamService = new StudentExamServiceImpl(getStudentExamRepository(),
                    getExamService(),
                    getStudentService(),
                    getStudentAnswerService(),
                    getExamQuestionService(),
                    getOptionService()
            );
        }
        return studentExamService;
    }

    private StudentAnswerRepository studentAnswerRepository;
    public StudentAnswerRepository getStudentAnswerRepository() {
        if (Objects.isNull(studentAnswerRepository)) {
            studentAnswerRepository = new StudentAnswerRepositoryImpl(getEntityManager(), StudentAnswer.class);
        }
        return studentAnswerRepository;
    }

    private StudentAnswerService studentAnswerService;
    public StudentAnswerService getStudentAnswerService() {
        if (Objects.isNull(studentAnswerService)) {
            studentAnswerService = new StudentAnswerServiceImpl(getStudentAnswerRepository(), getStudentExamService(), getQuestionService());
        }
        return studentAnswerService;
    }


    private ExamQuestionRepository examQuestionRepository;
    public ExamQuestionRepository getExamQuestionRepository() {
        if (Objects.isNull(examQuestionRepository)) {
            examQuestionRepository = new ExamQuestionRepositoryImpl(getEntityManager(), ExamQuestion.class);
        }
        return examQuestionRepository;
    }

    private ExamQuestionService examQuestionService;
    public ExamQuestionService getExamQuestionService() {
        if (Objects.isNull(examQuestionService)) {
            examQuestionService = new ExamQuestionServiceImpl(getExamQuestionRepository());
        }
        return examQuestionService;
    }

}
