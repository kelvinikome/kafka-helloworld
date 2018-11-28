package com.jobboard.app.service;

import com.jobboard.app.model.Job;
import com.jobboard.app.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SearchService {

    @Autowired
    private JobRepository jobRepository;

    public List<Job> fetchJobs(String term) {
        final Optional<List<Job>> optionalJobs = this.jobRepository.findByTitleContainingOrTypeContainingOrAvailabilityContainingOrExperienceLevelContainingOrCountryContaining(term, term, term, term, term);
        if (optionalJobs.isPresent())
            return optionalJobs.get();
        return new ArrayList<>();
    }
}
