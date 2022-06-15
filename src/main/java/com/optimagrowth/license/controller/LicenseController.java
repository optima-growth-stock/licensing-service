package com.optimagrowth.license.controller;

import com.optimagrowth.license.model.License;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/organization/{organizationId}/license")
public class LicenseController {

    @GetMapping(value = "/{licenseId}")
    public License getLicense(@PathVariable String organizationId, @PathVariable String licenseId) {
        License license = new License();
        license.setOrganizationId(organizationId);
        license.setLicenseId(licenseId);
        license.setDescription("test");
        return license;
    }
}
