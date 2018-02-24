package org.ngel.station.core.service;

import org.apache.commons.csv.CSVRecord;
import org.joda.time.LocalDate;
import org.ngel.station.common.representation.StationDailyDataRepresentationCollection;

/**
 * @author vgarg
 */
public interface StationDailyDataService {

    Double DEFAULT_PM25_MEAN = -1.0;

    void loadStationDailyData(CSVRecord csvRecord);

    Double getLatestPm25Mean(String ngelId);

    Double getPm25Mean(String ngelId, LocalDate forDate);

    StationDailyDataRepresentationCollection getStationDailyDataForLast60Days(String ngelId);

    StationDailyDataRepresentationCollection getStationDailyData(String ngelId, LocalDate startDate, LocalDate endDate);
}
