package org.ngel.station.core.persistence;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.ngel.station.core.config.SpringTestContextInitializer;
import org.ngel.station.core.domain.model.Station;
import org.ngel.station.core.domain.model.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author vgarg
 */
public class StationRepositoryIntTest extends SpringTestContextInitializer {

    private static final String NGEL_ID1 = "ngelId1";
    private static final String NGEL_ID2 = "ngelId2";
    private static final String STATION_NAME1 = "station1";
    private static final String STATION_NAME2 = "station2";
    private static final String CITY = "city";
    private static final String COUNTRY = "country";

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private SpringDataStationRepository springDataStationRepository;

    @Before
    public void setup() {
        setupStations();
    }

    @After
    public void teardown() {
        springDataStationRepository.deleteAll();
    }

    @Test
    public void findByNgelId() {
        Station station = stationRepository.findByNgelId(NGEL_ID1);
        assertThat(station, notNullValue());
        assertThat(station.getNgelId(), equalTo(NGEL_ID1));
    }

    @Test
    public void findByStationName() {
        Station station = stationRepository.findByStationName(STATION_NAME1);
        assertThat(station, notNullValue());
        assertThat(station.getNgelId(), equalTo(NGEL_ID1));
        assertThat(station.getStation(), equalTo(STATION_NAME1));
    }

    @Test
    public void findByCountry() {
        List<Station> stations = stationRepository.findByCountry(COUNTRY);
        assertThat(stations, hasSize(2));
    }

    @Test
    public void totalStations() {
        Long totalStations = stationRepository.totalStations();
        assertThat(totalStations, equalTo(2L));
    }

    @Test
    public void findByPage() {
        List<Station> stations = stationRepository.findByPage(1);
        assertThat(stations, hasSize(2));
    }

    private void setupStations() {
        Station station = Station.builder().ngelId(NGEL_ID1).station(STATION_NAME1).city(CITY).country(COUNTRY).build();
        springDataStationRepository.save(station);

        station = Station.builder().ngelId(NGEL_ID2).station(STATION_NAME2).city(CITY).country(COUNTRY).build();
        springDataStationRepository.save(station);
    }
}
