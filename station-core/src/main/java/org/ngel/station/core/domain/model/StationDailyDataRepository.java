package org.ngel.station.core.domain.model;

import java.util.List;

import org.joda.time.LocalDate;

/**
 * @author vgarg
 */
public interface StationDailyDataRepository {

    StationDailyData findLastOccurred(String ngelId);

    StationDailyData findStationDailyData(String ngelId, LocalDate occurred);

    List<StationDailyData> findLast60DaysStationData(String ngelId);

    List<StationDailyData> findStationData(String ngelId, LocalDate startDate, LocalDate endDate);
}
