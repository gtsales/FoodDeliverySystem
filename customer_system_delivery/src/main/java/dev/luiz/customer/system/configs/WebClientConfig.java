package dev.luiz.customer.system.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

	@Bean
	WebClient webClientCep(WebClient.Builder builder) {
		
		return builder.baseUrl("viacep.com.br/ws/").build();
	}
}
