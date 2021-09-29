package br.com.devchallenge.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Discovery utilizando o Eureka
 * @author erick.oliveira
 *
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
	}

}