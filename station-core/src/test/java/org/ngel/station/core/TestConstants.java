package org.ngel.station.core;

import org.joda.time.LocalDate;
import org.ngel.station.core.domain.model.Station;
import org.ngel.station.core.domain.model.StationDailyData;

import lombok.experimental.UtilityClass;

/**
 * @author vgarg
 */
@UtilityClass
public class TestConstants {

    public static final String NGEL_ID = "ngelId";
    public static final Double PM25_MEAN = -2.0;
    private static final Double TAU3_MEAN = -2.0;
    private static final String STATION_NAME = "stationName";
    private static final Double LONGITUDE = 1.234;
    private static final Double LATITUDE = 1.234;
    private static final LocalDate OCCURRED = LocalDate.now();

    public Station getStation() {
        return Station.builder().ngelId(NGEL_ID).station(STATION_NAME)
                .latitude(LATITUDE).longitude(LONGITUDE).build();
    }

    public StationDailyData getStationDailyData() {
        return StationDailyData.builder().ngelId(NGEL_ID).occurred(OCCURRED).pm25Mean(PM25_MEAN).tau3Mean(TAU3_MEAN).build();
    }
}
