package com.optimagrowth.license.controller;

import com.optimagrowth.license.model.License;
import com.optimagrowth.license.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/organization/{organizationId}/license")
public class LicenseController {

    @Autowired
    private LicenseService licenseService;

    @GetMapping(value = "/{licenseId}")
    public ResponseEntity<License> getLicense(@PathVariable String organizationId, @PathVariable String licenseId) {
        License license = licenseService.getLicense(organizationId, licenseId);
        return ResponseEntity.ok(license);
    }

    @PostMapping
    public ResponseEntity<String> createLicense(@PathVariable String organizationId, @RequestBody License license) {
        String msg = licenseService.createLicense(license, organizationId);
        return ResponseEntity.ok(msg);
    }

    @PutMapping
    public ResponseEntity<String> updateLicense(@PathVariable String organizationId, @RequestBody License license) {
        String msg = licenseService.updateLicense(license, organizationId);
        return ResponseEntity.ok(msg);
    }

    @DeleteMapping(value = "{licenseId}")
    public ResponseEntity deleteLicense(@PathVariable String organizationId, @PathVariable String licenseId) {
        String msg = licenseService.deleteLicense(organizationId, licenseId);
        return ResponseEntity.ok(msg);
    }
}
