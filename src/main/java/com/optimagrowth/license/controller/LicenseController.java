package com.optimagrowth.license.controller;

import com.optimagrowth.license.model.License;
import com.optimagrowth.license.service.LicenseService;
import com.optimagrowth.license.utils.UserContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Locale;


@RestController
@RequestMapping(value = "/v1/organization/{organizationId}/license")
public class LicenseController {
    private static final Logger logger = LoggerFactory.getLogger(LicenseController.class);

    @Autowired
    private LicenseService licenseService;

    @GetMapping
    public ResponseEntity<List<License>> getLicenses(@PathVariable String organizationId) {
        logger.debug("LicenseServiceController Correlation id: {}", UserContextHolder.getContext().getCorrelationId());
        List<License> licenses = licenseService.getLicenses(organizationId);
        licenses.forEach(License::addLinks);

        return ResponseEntity.ok(licenses);
    }

    @GetMapping(value = "/{licenseId}")
    public ResponseEntity<License> getLicense(@PathVariable String organizationId, @PathVariable String licenseId, @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        License license = licenseService.getLicense(organizationId, licenseId, locale);
        license.addLinks();

        return ResponseEntity.ok(license);
    }

    @PostMapping
    public ResponseEntity<License> createLicense(@PathVariable String organizationId, @RequestBody License license) {
        license.setOrganizationId(organizationId);
        License createdLicense = licenseService.createLicense(license);
        license.addLinks();
        return ResponseEntity.ok(createdLicense);
    }

    @PutMapping(value = "{licenseId}")
    public ResponseEntity<License> updateLicense(@PathVariable String organizationId, @PathVariable String licenseId, @RequestBody License license) {
        license.setOrganizationId(organizationId);
        license.setLicenseId(licenseId);
        License updatedLicense = licenseService.updateLicense(license);
        license.addLinks();
        return ResponseEntity.ok(updatedLicense);
    }

    @DeleteMapping(value = "{licenseId}")
    public ResponseEntity deleteLicense(@PathVariable String organizationId, @PathVariable String licenseId, @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        String msg = licenseService.deleteLicense(organizationId, licenseId, locale);
        return ResponseEntity.ok(msg);
    }
}
