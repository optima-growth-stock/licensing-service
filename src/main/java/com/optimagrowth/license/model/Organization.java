package com.optimagrowth.license.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@Getter
@Setter
@ToString
@RedisHash("organization")
public class Organization {
    @Id
    private String id;
    private String name;
    private String contactName;
    private String contactEmail;
    private String contactPhone;
}
