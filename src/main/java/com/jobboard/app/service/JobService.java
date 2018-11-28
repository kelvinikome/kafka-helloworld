package com.jobboard.app.service;

import com.jobboard.app.model.Job;
import com.jobboard.app.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public void createJob(Job job) {
        this.jobRepository.save(job);
    }

    public Job getJob(Long id) {
        final Optional<Job> job = this.jobRepository.findById(id);
        if (job.isPresent())
            return job.get();
        return null;
    }

    public List<Job> getAllJobs() {
        return this.jobRepository.findAll();
    }

    public void updateJob(Job job) {
        this.jobRepository.save(job);
    }

    public void deleteJob(Long id) {
        this.jobRepository.delete(this.jobRepository.findById(id).get());
    }
}
