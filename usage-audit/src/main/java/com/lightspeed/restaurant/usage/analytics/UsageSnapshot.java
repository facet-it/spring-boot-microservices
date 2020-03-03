package com.lightspeed.restaurant.usage.analytics;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "enpoint_usage_snapshot")
@NoArgsConstructor
public class UsageSnapshot {

    @Id
    private long time;

    @Column(name = "aggregate")
    private String aggregate;

}
