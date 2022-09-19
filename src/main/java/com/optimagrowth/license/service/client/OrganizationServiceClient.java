package com.optimagrowth.license.service.client;

import com.optimagrowth.license.model.Organization;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrganizationServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    public Organization getOrganization(String organizationId) {
        ResponseEntity<Organization> organization
                = restTemplate.exchange("http://organization-service/v1/organization/{organizationId}", HttpMethod.GET, null, Organization.class, organizationId);

        return organization.getBody();
    }
}
