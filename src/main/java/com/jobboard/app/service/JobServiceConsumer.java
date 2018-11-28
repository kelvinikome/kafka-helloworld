package com.jobboard.app.service;

import com.jobboard.app.model.Job;
import com.jobboard.app.repository.JobRepository;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JobServiceConsumer {

	@Autowired
	private JobRepository jobRepository;

	private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);

	private static final int JOB_TITLE = 0;
	private static final int JOB_DESCRIPTION = 1;
	private static final int JOB_PAY_RATE = 2;
	private static final int JOB_SKILLS = 3;
	private static final int JOB_TYPE = 4;
	private static final int JOB_EXPERIENCE_LEVEL = 5;
	private static final int JOB_COUNTRY = 6;
	private static final int JOB_LANGUAGES = 7;
	private static final int JOB_POSTED_BY = 8;
	private static final int JOB_AVAILABILITY = 9;

	@KafkaListener(topics="import-csv")
	public void importCsv(String data) {
		log.info("Importing CSV data...", data);

		BufferedReader fileReader = null;

		try {
			List<Job> jobList = new ArrayList<Job>();
			String line = "";
			fileReader = new BufferedReader(new StringReader(data));
			fileReader.readLine();

			while ((line = fileReader.readLine()) != null) {
				String[] tokens = line.split(",");
				if (tokens.length > 0) {

					List<String> skills = new ArrayList<>();
					for (String string : tokens[JOB_SKILLS].trim().split("\\s+"))
						skills.add(string);

					List<String> languages = new ArrayList<>();
					for (String string : tokens[JOB_LANGUAGES].trim().split("\\s+"))
						skills.add(string);

					Job job = new Job();
					job.setTitle(tokens[JOB_TITLE]);
					job.setDescription(tokens[JOB_DESCRIPTION]);
					job.setPayRate(new BigDecimal(Integer.parseInt(tokens[JOB_PAY_RATE])));
					job.setSkills(skills);
					job.setType(tokens[JOB_TYPE]);
					job.setExperienceLevel(tokens[JOB_EXPERIENCE_LEVEL]);
					job.setCountry(tokens[JOB_COUNTRY]);
					job.setLanguages(languages);
					job.setPostedBy(tokens[JOB_POSTED_BY]);
					job.setAvailability(tokens[JOB_AVAILABILITY]);

					jobList.add(job);
				}
			}

			log.info("Writing to database...", data);
			for (Job job : jobList) {
				this.jobRepository.save(job);
			}

		} catch (Exception e) {
			log.error("Reading CSV Error!");
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				log.error("Closing fileReader Error!");
				e.printStackTrace();
			}
		}
	}
}
