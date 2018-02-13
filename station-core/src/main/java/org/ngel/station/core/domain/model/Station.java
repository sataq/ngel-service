package org.ngel.station.core.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

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
@SqlResultSetMapping(name = "stationsWithLatestPm25Mean", entities = @EntityResult(entityClass = Station.class))
@NamedNativeQueries(value = {
        @NamedNativeQuery(name = Station.STATIONS_WITH_LATEST_PM25_MEAN_QUERY_NAME,
                query = Station.STATIONS_WITH_LATEST_PM25_MEAN_QUERY, resultSetMapping = "stationsWithLatestPm25Mean")
})
public class Station {

    public static final String STATIONS_WITH_LATEST_PM25_MEAN_QUERY_NAME = "Station.stationsWithLatestPm25Mean";

    public static final String STATIONS_WITH_LATEST_PM25_MEAN_QUERY = "SELECT st.*, sdd.pm25_mean as pm25_mean FROM station st INNER JOIN (SELECT s.ngel_id, s.pm25_mean FROM station_daily_data s WHERE (s.ngel_id, s.occurred) IN (SELECT sd.ngel_id, max(sd.occurred) FROM station_daily_data sd WHERE sd.ngel_id like '%_OAPM_%' GROUP BY sd.ngel_id)) sdd ON sdd.ngel_id=st.ngel_id";

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

    @Column(name = "pm25_mean")
    private Double pm25Mean;
}
