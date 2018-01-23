package org.ngel.station.core.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author vgarg
 */
@Entity
@Table(name = "station_daily_data")
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StationDailyData {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    private String ngelId;

    @Column(name = "occurred")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate occurred;

    @Column(name = "pm25_mean")
    private Double pm25Mean;

    @Column(name = "pm25_std")
    private Double pm25Std;

    @Column(name = "pm25_median")
    private Double pm25Median;

    private Integer pmCount;
    private String satFilename;
    private Double satHour;
    private Double searchRadius;
    private Double tauNearest;
    private Double tauPix;

    @Column(name = "tau3_mean")
    private Double tau3Mean;

    @Column(name = "tau3_median")
    private Double tau3Median;

    @Column(name = "tau3_std")
    private Double tau3Std;
}
