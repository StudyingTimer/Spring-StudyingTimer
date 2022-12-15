package com.studyTimer.programming.studyTimer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class StudyTimerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyTimerApplication.class, args);
	}

}
