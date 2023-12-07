package com.yaksha.training.tutor.boundary;


import com.yaksha.training.tutor.entity.Tutor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static com.yaksha.training.tutor.utils.MasterData.getTutor;
import static com.yaksha.training.tutor.utils.MasterData.randomStringWithSize;
import static com.yaksha.training.tutor.utils.TestUtils.boundaryTestFile;
import static com.yaksha.training.tutor.utils.TestUtils.currentTest;
import static com.yaksha.training.tutor.utils.TestUtils.testReport;
import static com.yaksha.training.tutor.utils.TestUtils.yakshaAssert;

@ExtendWith(SpringExtension.class)
public class BoundaryTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterAll
    public static void afterAll() {
        testReport();
    }

    @Test
    public void testTutorNameNotBlank() throws Exception {
        Tutor tutor = getTutor();
        tutor.setName("");
        Set<ConstraintViolation<Tutor>> violations = validator.validate(tutor);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testTutorNameNotNull() throws Exception {
        Tutor tutor = getTutor();
        tutor.setName(null);
        Set<ConstraintViolation<Tutor>> violations = validator.validate(tutor);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testTutorNameMinTwo() throws Exception {
        Tutor tutor = getTutor();
        tutor.setName(randomStringWithSize(1));
        Set<ConstraintViolation<Tutor>> violations = validator.validate(tutor);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testTutorNameMaxForty() throws Exception {
        Tutor tutor = getTutor();
        tutor.setName(randomStringWithSize(41));
        Set<ConstraintViolation<Tutor>> violations = validator.validate(tutor);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testTutorSubjectNotNull() throws Exception {
        Tutor tutor = getTutor();
        tutor.setSubject(null);
        Set<ConstraintViolation<Tutor>> violations = validator.validate(tutor);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testTutorSubjectNotBlank() throws Exception {
        Tutor tutor = getTutor();
        tutor.setSubject("");
        Set<ConstraintViolation<Tutor>> violations = validator.validate(tutor);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testTutorDescriptionNotNull() throws Exception {
        Tutor tutor = getTutor();
        tutor.setDescription(null);
        Set<ConstraintViolation<Tutor>> violations = validator.validate(tutor);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testTutorDescriptionMinTwo() throws Exception {
        Tutor tutor = getTutor();
        tutor.setDescription(randomStringWithSize(1));
        Set<ConstraintViolation<Tutor>> violations = validator.validate(tutor);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testTutorDescriptionMaxTwoHundred() throws Exception {
        Tutor tutor = getTutor();
        tutor.setDescription(randomStringWithSize(201));
        Set<ConstraintViolation<Tutor>> violations = validator.validate(tutor);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testTutorDescriptionNotBlank() throws Exception {
        Tutor tutor = getTutor();
        tutor.setDescription("");
        Set<ConstraintViolation<Tutor>> violations = validator.validate(tutor);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testTutorDurationNotNull() throws Exception {
        Tutor tutor = getTutor();
        tutor.setDuration(null);
        Set<ConstraintViolation<Tutor>> violations = validator.validate(tutor);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testTutorPriceNotNull() throws Exception {
        Tutor tutor = getTutor();
        tutor.setPrice(null);
        Set<ConstraintViolation<Tutor>> violations = validator.validate(tutor);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }


}
