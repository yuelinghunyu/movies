package com.jdj.movie;

import com.jdj.movie.model.Audience;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
@SpringBootApplication
@EnableConfigurationProperties(Audience.class)
@ServletComponentScan
public class MovieServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieServerApplication.class, args);
	}

}
