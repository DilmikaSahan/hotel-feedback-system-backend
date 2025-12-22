package com.feedback.feedback_system;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FeedbackSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeedbackSystemApplication.class, args);
	}
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
