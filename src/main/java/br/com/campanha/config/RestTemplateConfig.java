package br.com.campanha.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateConfig {

	@Value("${app.resttemplate.timeout}")
	private int timeout;
	
	@Bean
	public RestTemplate resttemplate() {
		
		final SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setConnectTimeout(timeout);
		requestFactory.setReadTimeout(timeout);
		
		return new RestTemplate(new BufferingClientHttpRequestFactory(requestFactory));
	}
	
}
