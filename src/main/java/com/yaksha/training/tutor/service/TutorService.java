package com.yaksha.training.tutor.service;

import com.yaksha.training.tutor.entity.Tutor;
import com.yaksha.training.tutor.repository.TutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorService {

    private final TutorRepository tutorRepository;

    public TutorService(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    public List<Tutor> getTutors() {
        List<Tutor> tutors = tutorRepository.findAll();
        return tutors;
    }

    public Tutor saveTutor(Tutor tutor) {
        return tutorRepository.save(tutor);
    }

    public Tutor getTutor(Long id) {
        return tutorRepository.findById(id).get();
    }

    public boolean deleteTutor(Long id) {
        tutorRepository.deleteById(id);
        return true;
    }

    public List<Tutor> searchTutors(String theSearchName) {
        if (theSearchName != null && theSearchName.trim().length() > 0) {
            return tutorRepository.findByTutorNameAndSubject(theSearchName);
        } else {
            return tutorRepository.findAll();
        }
    }
}
