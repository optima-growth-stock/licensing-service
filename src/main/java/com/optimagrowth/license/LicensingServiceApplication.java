package com.optimagrowth.license;

import com.optimagrowth.license.utils.UserContextInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

@SpringBootApplication
public class LicensingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LicensingServiceApplication.class, args);
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.US);
        return localeResolver;
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setBasename("messages");
        return messageSource;
    }

    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate() {
        final RestTemplate template = new RestTemplate();

        List<ClientHttpRequestInterceptor> interceptors = template.getInterceptors();
        interceptors.add(new UserContextInterceptor());
        template.setInterceptors(interceptors);

        return template;
    }

}
