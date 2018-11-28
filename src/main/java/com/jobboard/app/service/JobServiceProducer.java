package com.jobboard.app.service;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Scanner;

@Service
public class JobServiceProducer {

    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${jsa.kafka.topic}")
    String kafkaTopic = "jsa-test";

    public void importJobsFromCsv(MultipartFile multipartFile) {

        try {
            InputStream inputStream = multipartFile.getInputStream();

            String data;
            try (Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name())) {
                data = scanner.useDelimiter("\\A").next();
            }

            log.info("Importing data...");
            kafkaTemplate.send("import-csv", data);
        } catch (IOException e) {
            log.error("Data import failed. ", e);
        }
    }

    public void importJobsFromExcel(MultipartFile multipartFile) {

    }
}
