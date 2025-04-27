package com.wisitor.project;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@SpringBootApplication
public class ProjectApplication {

	static {
		// Load .env before Spring context initializes
		Dotenv dotenv = Dotenv.configure().load();
		dotenv.entries().forEach(entry -> {
			System.setProperty(entry.getKey(), entry.getValue());
		});
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

}

// FOR NOW KEEP THE THINGS SIMPLE AND DONT DIRECTLY TRY TO IMPLEMENT INDUSTRY STANDARD        ^
// FIRST IMPLEMENT WHAT YOU KNOW THAN WE WILL MAKE IT TO STANDARDS THATS HOW LEARNING WORKS (-|-)
//                                                                                            /\
