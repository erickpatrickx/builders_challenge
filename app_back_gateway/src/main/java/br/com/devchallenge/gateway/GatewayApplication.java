package br.com.devchallenge.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/** Gateway dos microserviços
 * @author erick.oliveira
 *
 */
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class GatewayApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

}