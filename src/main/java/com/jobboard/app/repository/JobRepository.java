package com.jobboard.app.repository;

import com.jobboard.app.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Long> {
    Optional<List<Job>> findByTitleContainingOrTypeContainingOrAvailabilityContainingOrExperienceLevelContainingOrCountryContaining(
            String title, String type, String availability, String experienceLevel, String country);

    Optional<Job> findById(Long id);
}
