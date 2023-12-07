package com.yaksha.training.tutor.functional;

import com.yaksha.training.tutor.controller.TutorController;
import com.yaksha.training.tutor.entity.Tutor;
import com.yaksha.training.tutor.service.TutorService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.yaksha.training.tutor.utils.MasterData.asJsonString;
import static com.yaksha.training.tutor.utils.MasterData.getTutor;
import static com.yaksha.training.tutor.utils.MasterData.getTutorList;
import static com.yaksha.training.tutor.utils.MasterData.randomStringWithSize;
import static com.yaksha.training.tutor.utils.TestUtils.businessTestFile;
import static com.yaksha.training.tutor.utils.TestUtils.currentTest;
import static com.yaksha.training.tutor.utils.TestUtils.testReport;
import static com.yaksha.training.tutor.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class TutorControllerTest {

    @Mock
    private TutorService tutorService;

    @InjectMocks
    private TutorController tutorController;

    private MockMvc mockMvc;

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
    public void testControllerListTutorsHome() throws Exception {
        List<Tutor> expected = getTutorList(5);
        when(tutorService.getTutors()).thenReturn(expected);
        MvcResult result = this.mockMvc.perform(get("/")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                        && result.getModelAndView().getViewName() != null
                        && result.getModelAndView().getViewName().contentEquals("list-tutors")
                        && asJsonString(expected).equals(asJsonString(result.getModelAndView().getModel().get("tutors")))
                        ? "true"
                        : "false"
                , businessTestFile);
    }

    @Test
    public void testControllerListTutors() throws Exception {
        List<Tutor> expected = getTutorList(5);
        when(tutorService.getTutors()).thenReturn(expected);
        MvcResult result = this.mockMvc.perform(get("/list")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                        && result.getModelAndView().getViewName() != null
                        && result.getModelAndView().getViewName().contentEquals("list-tutors")
                        && asJsonString(expected).equals(asJsonString(result.getModelAndView().getModel().get("tutors")))
                        ? "true"
                        : "false"
                , businessTestFile);
    }

    @Test
    public void testControllerShowFormForAdd() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/addTutorForm")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("add-tutor-form"), businessTestFile);
    }

    @Test
    public void testControllerSaveTutor() throws Exception {
        Tutor tutor = getTutor();
        MvcResult result = this.mockMvc.perform(post("/saveTutor")
                .flashAttr("tutor", tutor)).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("redirect:/tutor/list"), businessTestFile);
    }

    @Test
    public void testControllerSaveTutorHasError() throws Exception {
        Tutor tutor = getTutor();
        tutor.setId(null);
        tutor.setName(null);
        MvcResult result = this.mockMvc.perform(post("/saveTutor")
                .flashAttr("tutor", tutor)).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("add-tutor-form"), businessTestFile);
    }

    @Test
    public void testControllerUpdateTutorHasError() throws Exception {
        Tutor tutor = getTutor();
        tutor.setName(null);
        MvcResult result = this.mockMvc.perform(post("/saveTutor")
                .flashAttr("tutor", tutor)).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("update-tutor-form"), businessTestFile);
    }

    @Test
    public void testControllerShowFormForUpdate() throws Exception {
        Tutor tutor = getTutor();
        when(tutorService.getTutor(tutor.getId())).thenReturn(tutor);
        MvcResult result = this.mockMvc.perform(get("/updateTutorForm")
                .param("tutorId", tutor.getId().toString())).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("update-tutor-form"), businessTestFile);
    }

    @Test
    public void testControllerDeleteTutor() throws Exception {
        Tutor tutor = getTutor();
        MvcResult result = this.mockMvc.perform(get("/delete")
                .param("tutorId", tutor.getId().toString())).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("redirect:/tutor/list"), businessTestFile);
    }

    @Test
    public void testControllerSearchTutors() throws Exception {
        String key = randomStringWithSize(2);
        List<Tutor> expected = getTutorList(5);
        when(tutorService.searchTutors(key)).thenReturn(expected);
        MvcResult result = this.mockMvc.perform(post("/search")
                .param("theSearchName", key)).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                        && result.getModelAndView().getViewName() != null
                        && result.getModelAndView().getViewName().contentEquals("list-tutors")
                        && asJsonString(expected).equals(asJsonString(result.getModelAndView().getModel().get("tutors")))
                        ? "true"
                        : "false",
                businessTestFile);
    }

}
