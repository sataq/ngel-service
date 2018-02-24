package org.ngel.station.core.service;

import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.joda.time.LocalDate;
import org.ngel.station.common.representation.StationRepresentation;
import org.ngel.station.common.representation.StationRepresentationCollection;

/**
 * @author vgarg
 */
public interface StationService {

    List<StationRepresentation> getStations();

    StationRepresentationCollection getStationsWithPm25Mean();

    StationRepresentationCollection getStationsWithPm25Mean(LocalDate forDate);

    void loadStation(CSVRecord csvRecord);
}
