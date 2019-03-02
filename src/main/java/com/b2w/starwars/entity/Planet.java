package com.b2w.starwars.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PLANET")
public class Planet {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "PLANET_ID")
    private Long planetId;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "CLIMATE", nullable = false)
    private String climate;

    @Column(name = "TERRAIN", nullable = false)
    private String terrain;

    @Column(name = "APPARITIONS", nullable = false)
    private Integer apparitions;
}
