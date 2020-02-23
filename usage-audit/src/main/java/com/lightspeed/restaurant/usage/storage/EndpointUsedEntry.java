package com.lightspeed.restaurant.usage.storage;

import com.lightspeed.restaurant.usage.EndpointUsedEvent;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "events")
@NoArgsConstructor
public class EndpointUsedEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "time")
    private LocalDateTime time;

    @Column(name = "userId")
    private int userId;

    @Column(name = "companyId")
    private int companyId;

    @Column(name = "username")
    private String username;

    @Column(name = "userUuid")
    private String userUuid;

    @Column(name = "userType")
    private String userType;

    @Column(name = "companyType")
    private String companyType;

    @Column(name = "companyCity")
    private String companyCity;

    @Column(name = "companyCountry")
    private String companyCountry;

    @Column(name = "companyName")
    private String companyName;

    @Column(name = "endpoint")
    private String endpoint;

    @Column(name = "arguments")
    private String arguments;

    public EndpointUsedEntry(EndpointUsedEvent event) {
        this.arguments = event.getArguments();
        this.companyCity = event.getCompanyDetails().getCity();
        this.companyCountry = event.getCompanyDetails().getCountry();
        this.companyId = event.getCompanyDetails().getOid();
        this.companyType = event.getCompanyDetails().getType();
        this.companyName = event.getCompanyDetails().getName();
        this.userId = event.getUserDetails().getOid();
        this.username = event.getUserDetails().getUsername();
        this.userType = event.getUserDetails().getType();
        this.userUuid = event.getUserDetails().getUuid();
        this.endpoint = event.getEndpoint();
        this.time = event.getTime();
    }

}
