package com.jdj.movie;

import com.jdj.movie.model.Audience;
import com.jdj.movie.typesHandlers.HTTPBasicAuthorizeHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(Audience.class)
public class MovieServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieServerApplication.class, args);
	}

    @Bean
    public FilterRegistrationBean setFilter(){

        FilterRegistrationBean filterBean = new FilterRegistrationBean();
        filterBean.setFilter(new HTTPBasicAuthorizeHandler());
        filterBean.setName("HTTPBasicAuthorizeHandler");
        filterBean.addUrlPatterns("/*");
        filterBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico");
        return filterBean;
    }
}
