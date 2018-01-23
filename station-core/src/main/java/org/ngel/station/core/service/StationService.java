package org.ngel.station.core.service;

import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.joda.time.LocalDate;
import org.ngel.station.common.representation.StationRepresentation;

/**
 * @author vgarg
 */
public interface StationService {

    List<StationRepresentation> getStations();

    List<StationRepresentation> getStationsWithPm25Mean();

    List<StationRepresentation> getStationsWithPm25Mean(LocalDate forDate);

    void loadStation(CSVRecord csvRecord);
}
