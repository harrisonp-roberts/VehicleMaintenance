package com.maintenancetracker.configurations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configures the application to allow Cross Origin Resource Sharing with the Angular Frontend
 * which is hosted at a different URL.
 */
@Configuration
@EnableWebMvc
public class CORSConfiguration implements WebMvcConfigurer
{

	/**
	 * Configures the application for Cross Origin Resource Sharing
	 *
	 * @return WebMvcConfigurer Web Application Configuration
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer()
	{

		return new WebMvcConfigurer()
		{

			/**
			 * Set parameters for who is allowed Cross Origin Resource Sharing Access to the site.
			 *
			 * @param reg Cross origin registry
			 */
			@Override
			public void addCorsMappings(CorsRegistry reg)
			{

				/*
				Configures Cross Origin Resource Sharing to allow the Angular front-end to request resources on the
				Spring Boot Backend
				 */
				reg.addMapping("/**")
						.allowedOrigins("http://localhost:4200", "http://localhost:4200/accountInfo")
						.allowedHeaders("Origin", "Content-Type", "Accept", "Access-Control-Request-Headers", "Access-Control-Allow-Origin", "Authorization")
						.exposedHeaders("Location")
						.allowedMethods("GET", "OPTIONS", "PATCH", "POST", "PUT", "DELETE", "HEAD")
						.allowCredentials(true)
						.maxAge(3600);
			}
		};
	}

}
