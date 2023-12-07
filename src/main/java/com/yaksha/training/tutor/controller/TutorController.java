package com.yaksha.training.tutor.controller;

import com.yaksha.training.tutor.entity.Tutor;
import com.yaksha.training.tutor.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping(value = {"/tutor", "/"})
public class TutorController {

    @InitBinder
    public void InitBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @Autowired
    private TutorService tutorService;

    @GetMapping(value = {"/list", "/"})
    public String listTutors(Model model) {
        List<Tutor> tutors = tutorService.getTutors();
        model.addAttribute("tutors", tutors);
        return "list-tutors";
    }

    @GetMapping("/addTutorForm")
    public String showFormForAdd(Model model) {
        Tutor tutor = new Tutor();
        model.addAttribute("tutor", tutor);
        return "add-tutor-form";
    }

    @PostMapping("/saveTutor")
    public String saveTutor(@Valid @ModelAttribute("tutor") Tutor tutor, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            if (tutor.getId() != null) {
                return "update-tutor-form";
            }
            return "add-tutor-form";
        } else {
            tutorService.saveTutor(tutor);
            return "redirect:/tutor/list";
        }
    }

    @GetMapping("/updateTutorForm")
    public String showFormForUpdate(@RequestParam("tutorId") Long id, Model model) {
        Tutor tutor = tutorService.getTutor(id);
        model.addAttribute("tutor", tutor);
        return "update-tutor-form";
    }

    @GetMapping("/delete")
    public String deleteTutor(@RequestParam("tutorId") Long id) {
        tutorService.deleteTutor(id);
        return "redirect:/tutor/list";
    }

    @PostMapping("/search")
    public String searchTutors(@RequestParam("theSearchName") String theSearchName,
                               Model theModel) {

        List<Tutor> theTutors = tutorService.searchTutors(theSearchName);
        theModel.addAttribute("tutors", theTutors);
        return "list-tutors";
    }
}
