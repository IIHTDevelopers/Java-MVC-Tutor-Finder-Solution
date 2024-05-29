package com.yaksha.training.tutor.repository;

import com.yaksha.training.tutor.entity.Tutor;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TutorRepository extends JpaRepository<Tutor, Long> {

    @Query(value = "Select c from Tutor c where lower(name) like %:keyword% or lower(subject) like %:keyword%")
    Page<Tutor> findByTutorNameAndSubject(@Param("keyword") String keyword, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "UPDATE tutor SET is_available = :status where id = :id", nativeQuery = true)
    void updateTutorAvailability(@Param("id") Long id, @Param("status") Integer status);

    @Query("SELECT t FROM Tutor t")
    Page<Tutor> findAllTutor(Pageable pageable);
}
