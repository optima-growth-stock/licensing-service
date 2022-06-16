package com.optimagrowth.license.controller;

import com.optimagrowth.license.model.License;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/organization/{organizationId}/license")
public class LicenseController {

    @GetMapping(value = "/{licenseId}")
    public ResponseEntity<License> getLicense(@PathVariable String organizationId, @PathVariable String licenseId) {
        License license = new License();
        license.setOrganizationId(organizationId);
        license.setLicenseId(licenseId);
        license.setDescription("test");
        return ResponseEntity.ok(license);
    }

    @PostMapping
    public ResponseEntity<License> createLicense(@PathVariable String organizationId, @RequestBody License license) {
        license.setOrganizationId(organizationId);
        return ResponseEntity.ok(license);
    }

    @PutMapping
    public ResponseEntity<License> updateLicense(@PathVariable String organizationId, @RequestBody License license) {
        license.setOrganizationId(organizationId);
        return ResponseEntity.ok(license);
    }

    @DeleteMapping(value = "{licenseId}")
    public ResponseEntity deleteLicense(@PathVariable String organizationId, @PathVariable String licenseId) {
        return ResponseEntity.noContent().build();
    }
}
