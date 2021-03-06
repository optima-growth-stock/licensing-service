package com.optimagrowth.license.service;

import com.optimagrowth.license.config.ServiceConfig;
import com.optimagrowth.license.model.License;
import com.optimagrowth.license.model.Organization;
import com.optimagrowth.license.repository.LicenseRepository;
import com.optimagrowth.license.service.client.OrganizationServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LicenseService {

    @Autowired
    private ResourceBundleMessageSource messages;
    @Autowired
    private LicenseRepository licenseRepository;
    @Autowired
    private ServiceConfig config;

    @Autowired
    private OrganizationServiceClient organizationClient;

    public List<License> getLicenses(String organizationId) {
        List<License> licenses = licenseRepository.findByOrganizationId(organizationId);

        Organization organization = organizationClient.getOrganization(organizationId);

        licenses.forEach(l -> fillLicenseWithOrganizationData(l, organization));
        licenses.forEach(l -> l.withComment(config.getProperty()));
        return licenses;
    }

    public License getLicense(String organizationId, String licenseId, Locale locale) {
        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
        if (license == null)
            throw new IllegalArgumentException(String.format(messages.getMessage("license.search.error.message", null, locale), licenseId, organizationId));

        fillLicenseWithOrganizationData(license, organizationClient.getOrganization(organizationId));

        return license.withComment(config.getProperty());
    }

    public License createLicense(License license) {
        license.setLicenseId(UUID.randomUUID().toString());

        return licenseRepository.save(license).withComment(config.getProperty());
    }

    public License updateLicense(License license) {
        return licenseRepository.save(license).withComment(config.getProperty());
    }

    public String deleteLicense(String organizationId, String licenseId, Locale locale) {
        licenseRepository.deleteByOrganizationIdAndLicenseId(organizationId, licenseId);
        String responseMessage = String.format(messages.getMessage("license.delete.message", null, locale), licenseId, organizationId);
        return responseMessage;
    }

    private void fillLicenseWithOrganizationData(License license, Organization organization) {

        if (organization != null) {
            license.setName(organization.getName());
            license.setContactName(organization.getContactName());
            license.setContactEmail(organization.getContactEmail());
            license.setContactPhone(organization.getContactPhone());
        }
    }
}
