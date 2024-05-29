package com.yaksha.training.tutor.service;

import com.yaksha.training.tutor.entity.Tutor;
import com.yaksha.training.tutor.repository.TutorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TutorService {

    private final TutorRepository tutorRepository;

    public TutorService(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    public Page<Tutor> getTutors(Pageable pageable) {
        Page<Tutor> tutors = tutorRepository.findAllTutor(pageable);
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

    public Page<Tutor> searchTutors(String theSearchName, Pageable pageable) {
        if (theSearchName != null && theSearchName.trim().length() > 0) {
            return tutorRepository.findByTutorNameAndSubject(theSearchName, pageable);
        } else {
            return tutorRepository.findAllTutor(pageable);
        }
    }

    public boolean updateTutorAvailability(Long id, Integer status) {
        tutorRepository.updateTutorAvailability(id, status);
        return true;
    }
}
