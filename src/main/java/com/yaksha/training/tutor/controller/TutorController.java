package com.yaksha.training.tutor.controller;

import com.yaksha.training.tutor.entity.Tutor;
import com.yaksha.training.tutor.service.TutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public String listTutors(@PageableDefault(size = 5) Pageable pageable, Model model) {
        Page<Tutor> tutors = tutorService.getTutors(pageable);
        model.addAttribute("tutors", tutors.getContent());
        model.addAttribute("theSearchName", "");
        model.addAttribute("totalPage", tutors.getTotalPages());
        model.addAttribute("page", pageable.getPageNumber());
        model.addAttribute("sortBy", pageable.getSort().get().count() != 0 ?
                pageable.getSort().get().findFirst().get().getProperty() + "," + pageable.getSort().get().findFirst().get().getDirection() : "");
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

    @RequestMapping("/search")
    public String searchTutors(@RequestParam(value = "theSearchName", required = false) String theSearchName,
                               @PageableDefault(size = 5) Pageable pageable,
                               Model theModel) {

        Page<Tutor> theTutors = tutorService.searchTutors(theSearchName, pageable);
        theModel.addAttribute("theSearchName", theSearchName != null ? theSearchName : "");
        theModel.addAttribute("tutors", theTutors.getContent());
        theModel.addAttribute("totalPage", theTutors.getTotalPages());
        theModel.addAttribute("page", pageable.getPageNumber());
        theModel.addAttribute("sortBy", pageable.getSort().get().count() != 0 ?
                pageable.getSort().get().findFirst().get().getProperty() + "," + pageable.getSort().get().findFirst().get().getDirection() : "");
        return "list-tutors";
    }

    @GetMapping("/updateAvailability")
    public String updateAvailability(@RequestParam("tutorId") Long id,
                                     @RequestParam("status") Integer status) {
        tutorService.updateTutorAvailability(id, status);
        return "redirect:/tutor/list";
    }
}
