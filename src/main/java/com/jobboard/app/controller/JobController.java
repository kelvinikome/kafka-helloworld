package com.jobboard.app.controller;

import com.jobboard.app.model.Job;
import com.jobboard.app.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobController {

    @Autowired
    private JobService jobService;

    @RequestMapping(value="/jobs", method= RequestMethod.POST)
    public void createJob(@RequestBody Job job) {
        this.jobService.createJob(job);
    }

    @RequestMapping(value="/jobs", method= RequestMethod.GET)
    public List<Job> fetchJobs() {
        return this.jobService.getAllJobs();
    }

    @RequestMapping(value="/jobs/{id}", method= RequestMethod.GET)
    public Job getJob(@PathVariable Long id) {
        return this.jobService.getJob(id);
    }

    @RequestMapping(value="/jobs", method= RequestMethod.PUT)
    public void updateJob(@RequestBody Job job) {
        this.jobService.updateJob(job);
    }

    @RequestMapping(value="/jobs/{id}", method= RequestMethod.DELETE)
    public void deleteJob(@PathVariable Long id) {
        this.jobService.deleteJob(id);
    }
}
