package com.jdj.movie;

import com.jdj.movie.typesHandlers.HTTPBasicAuthorizeHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MovieServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieServerApplication.class, args);
	}

	@Bean
    public FilterRegistrationBean filterRegistrationBean(){
	    FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        HTTPBasicAuthorizeHandler httpBasicAuthorizeHandler = new HTTPBasicAuthorizeHandler();
        registrationBean.setFilter(httpBasicAuthorizeHandler);
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/movies/*");
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;

    }
}
