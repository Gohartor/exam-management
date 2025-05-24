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

    public AdminRepository getProvinceRepository() {
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

    private QuestionBankRepository questionBankRepository;

    public QuestionBankRepository getQuestionBankRepository() {
        if (Objects.isNull(questionBankRepository)) {
            questionBankRepository = new QuestionBankRepositoryImpl(getEntityManager(), QuestionsBank.class);
        }
        return questionBankRepository;
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
}
