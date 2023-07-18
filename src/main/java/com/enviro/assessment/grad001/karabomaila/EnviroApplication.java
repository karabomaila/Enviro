package com.enviro.assessment.grad001.karabomaila;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;

@SpringBootApplication
public class EnviroApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnviroApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(FileParser fileParser){
        return runner->{
            File file = new File("/home/karabo/Downloads/Dev_AssessmentCsv_Ref003.csv");
            fileParser.parseCSV(file);
            System.out.println("Save user's profiles");
        };
    }

}
