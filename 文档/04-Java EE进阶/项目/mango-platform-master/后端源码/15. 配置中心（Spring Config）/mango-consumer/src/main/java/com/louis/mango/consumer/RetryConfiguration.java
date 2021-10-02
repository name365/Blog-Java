package com.louis.mango.consumer;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.interceptor.RetryInterceptorBuilder;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;

public class RetryConfiguration {
	
	@Bean
	@ConditionalOnMissingBean(name = "configServerRetryInterceptor")
	public RetryOperationsInterceptor configServerRetryInterceptor() {
		return RetryInterceptorBuilder.stateless().backOffOptions(1000, 1.2, 5000).maxAttempts(10).build();
	}
	
}