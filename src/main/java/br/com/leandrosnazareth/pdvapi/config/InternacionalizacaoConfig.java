package br.com.leandrosnazareth.pdvapi.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class InternacionalizacaoConfig {

	// definir arquivo fonte das mensagens
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		// nome do arquivo .properties que contem as mensagens
		messageSource.setBasename("classpath:mensagens");
		messageSource.setDefaultEncoding("utf-8");
		messageSource.setDefaultLocale(Locale.getDefault());
		return messageSource;
	}
	
	// definir bean para ser utilizado pela validacao do bean validator
	@Bean
	public LocalValidatorFactoryBean validatorFactoryBean() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}
}