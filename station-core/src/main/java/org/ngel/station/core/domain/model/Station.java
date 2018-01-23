package org.ngel.station.core.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author vgarg
 */
@Entity
@Table(name = "station")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Station {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    private String ngelId;
    private String station;
    private String city;
    private String country;
    private Double latitude;
    private Double longitude;
}
