package com.yaksha.training.tutor.repository;

import com.yaksha.training.tutor.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TutorRepository extends JpaRepository<Tutor, Long> {

    @Query(value = "Select c from Tutor c where lower(name) like %:keyword% or lower(subject) like %:keyword%")
    List<Tutor> findByTutorNameAndSubject(@Param("keyword") String keyword);
}
