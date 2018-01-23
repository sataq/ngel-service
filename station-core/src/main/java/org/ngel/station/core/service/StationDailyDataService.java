package org.ngel.station.core.service;

import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.joda.time.LocalDate;
import org.ngel.station.common.representation.StationDailyDataRepresentation;

/**
 * @author vgarg
 */
public interface StationDailyDataService {

    Double DEFAULT_PM25_MEAN = -1.0;

    void loadStationDailyData(CSVRecord csvRecord);

    Double getLatestPm25Mean(String ngelId);

    Double getPm25Mean(String ngelId, LocalDate forDate);

    List<StationDailyDataRepresentation> getStationDailyDataForLast60Days(String ngelId);

    List<StationDailyDataRepresentation> getStationDailyData(String ngelId, LocalDate startDate, LocalDate endDate);
}
