package com.jobboard.app.controller;

import com.jobboard.app.model.Job;
import com.jobboard.app.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/search/{term}")
    public List<Job> fetchJobs(@PathVariable String term) {
        return this.searchService.fetchJobs(term);
    }
}
