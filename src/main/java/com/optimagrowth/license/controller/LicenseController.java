package com.optimagrowth.license.controller;

import com.optimagrowth.license.model.License;
import com.optimagrowth.license.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/v1/organization/{organizationId}/license")
public class LicenseController {

    @Autowired
    private LicenseService licenseService;

    @GetMapping(value = "/{licenseId}")
    public ResponseEntity<License> getLicense(@PathVariable String organizationId, @PathVariable String licenseId) {
        License license = licenseService.getLicense(organizationId, licenseId);
        license.add(linkTo(methodOn(LicenseController.class).getLicense(organizationId, licenseId)).withSelfRel());
        license.add(linkTo(methodOn(LicenseController.class).createLicense(organizationId, license, null)).withRel("createLicense"));
        license.add(linkTo(methodOn(LicenseController.class).updateLicense(organizationId, license, null)).withRel("updateLicense"));
        license.add(linkTo(methodOn(LicenseController.class).deleteLicense(organizationId, licenseId, null)).withRel("deleteLicense"));

        return ResponseEntity.ok(license);
    }

    @PostMapping
    public ResponseEntity<String> createLicense(@PathVariable String organizationId, @RequestBody License license, @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        String msg = licenseService.createLicense(license, organizationId, locale);
        return ResponseEntity.ok(msg);
    }

    @PutMapping
    public ResponseEntity<String> updateLicense(@PathVariable String organizationId, @RequestBody License license, @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        String msg = licenseService.updateLicense(license, organizationId, locale);
        return ResponseEntity.ok(msg);
    }

    @DeleteMapping(value = "{licenseId}")
    public ResponseEntity deleteLicense(@PathVariable String organizationId, @PathVariable String licenseId, @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        String msg = licenseService.deleteLicense(organizationId, licenseId, locale);
        return ResponseEntity.ok(msg);
    }
}
