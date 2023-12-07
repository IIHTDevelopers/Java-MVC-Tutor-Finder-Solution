package com.yaksha.training.tutor.service;

import com.yaksha.training.tutor.entity.Tutor;
import com.yaksha.training.tutor.repository.TutorRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static com.yaksha.training.tutor.utils.MasterData.asJsonString;
import static com.yaksha.training.tutor.utils.MasterData.getTutor;
import static com.yaksha.training.tutor.utils.MasterData.getTutorList;
import static com.yaksha.training.tutor.utils.MasterData.randomStringWithSize;
import static com.yaksha.training.tutor.utils.TestUtils.businessTestFile;
import static com.yaksha.training.tutor.utils.TestUtils.currentTest;
import static com.yaksha.training.tutor.utils.TestUtils.testReport;
import static com.yaksha.training.tutor.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

public class TutorServiceTest {

    @Mock
    private TutorRepository tutorRepository;

    @InjectMocks
    private TutorService tutorService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void afterAll() {
        testReport();
    }

    @Test
    public void testServiceGetTutors() throws Exception {
        List<Tutor> actual = getTutorList(5);
        when(tutorRepository.findAll()).thenReturn(actual);
        List<Tutor> expected = tutorService.getTutors();
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testServiceSaveTutor() throws Exception {
        Tutor actual = getTutor();
        when(tutorRepository.save(actual)).thenReturn(actual);
        Tutor expected = tutorService.saveTutor(actual);
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testServiceGetTutor() throws Exception {
        Tutor actual = getTutor();
        when(tutorRepository.findById(actual.getId())).thenReturn(Optional.of(actual));
        Tutor expected = tutorService.getTutor(actual.getId());
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testServiceDeleteTutor() throws Exception {
        Tutor actual = getTutor();
        boolean expected = tutorService.deleteTutor(actual.getId());
        yakshaAssert(currentTest(),
                (expected ? true : false),
                businessTestFile);
    }

    @Test
    public void testServiceSearchTutorWithNull() throws Exception {
        List<Tutor> actual = getTutorList(5);
        when(tutorRepository.findAll()).thenReturn(actual);
        List<Tutor> expected = tutorService.searchTutors(null);
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testServiceSearchTutorWithSearchName() throws Exception {
        String searchKey = randomStringWithSize(2);
        List<Tutor> actual = getTutorList(5);
        when(tutorRepository.findByTutorNameAndSubject(searchKey)).thenReturn(actual);
        List<Tutor> expected = tutorService.searchTutors(searchKey);
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

}
