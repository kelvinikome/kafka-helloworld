package com.jobboard.app.controller;

import com.jobboard.app.service.JobService;
import com.jobboard.app.service.JobServiceProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class BulkJobController {

    @Autowired
    private JobServiceProducer jobServiceProducer;

    @RequestMapping(value = "/import/csv", method = RequestMethod.POST)
    public void importCsv(@RequestParam("file") MultipartFile multipartFile) {
        this.jobServiceProducer.importJobsFromCsv(multipartFile);
    }

    @RequestMapping(value = "/import/excel", method = RequestMethod.POST)
    public void importExcel(@RequestBody MultipartFile multipartFile) {
        this.jobServiceProducer.importJobsFromExcel(multipartFile);
    }
}
