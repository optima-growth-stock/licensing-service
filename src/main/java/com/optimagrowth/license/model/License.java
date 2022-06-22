package com.optimagrowth.license.model;

import com.optimagrowth.license.controller.LicenseController;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Getter
@Setter
@ToString
@Entity
@Table(name = "licenses")
public class License extends RepresentationModel<License> {
    @Id
    @Column(nullable = false)
    private String licenseId;
    private String description;
    @Column(nullable = false)
    private String organizationId;
    @Column(nullable = false)
    private String productName;
    @Column(nullable = false)
    private String licenseType;
    private String comment;

    public License withComment(String comment) {
        this.setComment(comment);
        return this;
    }

    public License addLinks() {
        this.add(
                linkTo(methodOn(LicenseController.class).getLicense(this.organizationId, this.licenseId, null)).withSelfRel(),
                linkTo(methodOn(LicenseController.class).updateLicense(this.organizationId, null, this)).withRel("updateLicense"),
                linkTo(methodOn(LicenseController.class).deleteLicense(this.organizationId, this.licenseId, null)).withRel("deleteLicense"),
                linkTo(methodOn(LicenseController.class).createLicense(this.organizationId, this)).withRel("createLicense")
        );

        return this;
    }

}
