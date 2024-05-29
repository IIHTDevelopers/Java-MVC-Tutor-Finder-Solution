package com.yaksha.training.tutor.exception;

import com.yaksha.training.tutor.controller.TutorController;
import com.yaksha.training.tutor.entity.Tutor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import static com.yaksha.training.tutor.utils.MasterData.getTutor;
import static com.yaksha.training.tutor.utils.TestUtils.currentTest;
import static com.yaksha.training.tutor.utils.TestUtils.exceptionTestFile;
import static com.yaksha.training.tutor.utils.TestUtils.testReport;
import static com.yaksha.training.tutor.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class TutorExceptionTest {

    @InjectMocks
    private TutorController tutorController;

    private MockMvc mockMvc;

    BindingResult bindingResult = mock(BindingResult.class);

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(tutorController).build();
    }

    @After
    public void afterAll() {
        testReport();
    }

    @Test
    public void testExceptionSaveTutorNameAsNull() throws Exception {
        Tutor tutor = getTutor();
        tutor.setId(null);
        tutor.setName(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveTutor")
                .flashAttr("tutor", tutor)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("add-tutor-form")), exceptionTestFile);
    }

    @Test
    public void testExceptionSaveTutorDescAsNull() throws Exception {
        Tutor tutor = getTutor();
        tutor.setId(null);
        tutor.setDescription(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveTutor")
                .flashAttr("tutor", tutor)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("add-tutor-form")), exceptionTestFile);
    }

    @Test
    public void testExceptionSaveTutorSubjectAsNull() throws Exception {
        Tutor tutor = getTutor();
        tutor.setId(null);
        tutor.setSubject(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveTutor")
                .flashAttr("tutor", tutor)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("add-tutor-form")), exceptionTestFile);
    }

    @Test
    public void testExceptionSaveTutorDurationAsNull() throws Exception {
        Tutor tutor = getTutor();
        tutor.setId(null);
        tutor.setDuration(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveTutor")
                .flashAttr("tutor", tutor)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("add-tutor-form")), exceptionTestFile);
    }

    @Test
    public void testExceptionSaveTutorPriceAsNull() throws Exception {
        Tutor tutor = getTutor();
        tutor.setId(null);
        tutor.setPrice(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveTutor")
                .flashAttr("tutor", tutor)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("add-tutor-form")), exceptionTestFile);
    }


    @Test
    public void testExceptionUpdateTutorNameAsNull() throws Exception {
        Tutor tutor = getTutor();
        tutor.setName(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveTutor")
                .flashAttr("tutor", tutor)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("update-tutor-form")), exceptionTestFile);
    }

    @Test
    public void testExceptionUpdateTutorDescAsNull() throws Exception {
        Tutor tutor = getTutor();
        tutor.setDescription(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveTutor")
                .flashAttr("tutor", tutor)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("update-tutor-form")), exceptionTestFile);
    }

    @Test
    public void testExceptionUpdateTutorSubjectAsNull() throws Exception {
        Tutor tutor = getTutor();
        tutor.setSubject(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveTutor")
                .flashAttr("tutor", tutor)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("update-tutor-form")), exceptionTestFile);
    }

    @Test
    public void testExceptionUpdateTutorDurationAsNull() throws Exception {
        Tutor tutor = getTutor();
        tutor.setDuration(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveTutor")
                .flashAttr("tutor", tutor)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("update-tutor-form")), exceptionTestFile);
    }

    @Test
    public void testExceptionUpdateTutorPriceAsNull() throws Exception {
        Tutor tutor = getTutor();
        tutor.setPrice(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/saveTutor")
                .flashAttr("tutor", tutor)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("update-tutor-form")), exceptionTestFile);
    }


}