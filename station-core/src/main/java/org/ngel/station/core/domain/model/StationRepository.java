package org.ngel.station.core.domain.model;

import java.util.List;

/**
 * @author vgarg
 */
public interface StationRepository {

    Integer PAGE_SIZE = 100;

    Long totalStations();

    List<Station> findAllOAPMStations();

    List<Station> findAllOAPMStationsWithLatestPm25Mean();

    Station findByNgelId(String ngelId);

    Station findByStationName(String stationName);

    List<Station> findByCountry(String country);

    void save(Station station);
}
