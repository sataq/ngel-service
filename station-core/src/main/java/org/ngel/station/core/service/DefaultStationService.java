package org.ngel.station.core.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.joda.time.LocalDate;
import org.ngel.station.common.representation.StationRepresentation;
import org.ngel.station.core.domain.model.Station;
import org.ngel.station.core.domain.model.StationRepository;
import org.ngel.station.core.transformers.StationTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author vgarg
 */
@Service
public class DefaultStationService implements StationService {

    @Autowired
    private StationDailyDataService stationDailyDataService;

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private StationTransformer stationTransformer;

    @Override
    public List<StationRepresentation> getStations() {
        Long totalStations = stationRepository.totalStations();
        int totalPages = getTotalPages(totalStations);
        List<Station> stations = new ArrayList<>();
        for (int i = 1; i <= totalPages; i++) {
            stations.addAll(stationRepository.findByPage(i));
        }
        return stationTransformer.transform(stations);
    }

    @Override
    public List<StationRepresentation> getStationsWithPm25Mean() {
        List<StationRepresentation> stationRepresentations = getStations();
        stationRepresentations.forEach(stationRepresentation -> {
            Double pm25Mean = stationDailyDataService.getLatestPm25Mean(stationRepresentation.getNgelId());
            stationRepresentation.setLatestPM25Mean(pm25Mean);
        });
        return stationRepresentations;
    }

    @Override
    public List<StationRepresentation> getStationsWithPm25Mean(LocalDate occurred) {
        List<StationRepresentation> stationRepresentations = getStations();
        stationRepresentations.forEach(stationRepresentation -> {
            Double pm25Mean = stationDailyDataService.getPm25Mean(stationRepresentation.getNgelId(), occurred);
            stationRepresentation.setLatestPM25Mean(pm25Mean);
        });
        return stationRepresentations;
    }


    @Override
    public void loadStation(CSVRecord csvRecord) {
        if (csvRecord == null) {
            return;
        }
        Station.StationBuilder builder = Station.builder();
        builder.ngelId(csvRecord.get(0)).station(csvRecord.get(1)).city(csvRecord.get(2)).country(csvRecord.get(3))
                .latitude(getDouble(csvRecord.get(4))).longitude(getDouble(csvRecord.get(5)));
        stationRepository.save(builder.build());
    }

    int getTotalPages(Long total) {
        return (total.intValue() / StationRepository.PAGE_SIZE) + ((total.intValue() % StationRepository.PAGE_SIZE) > 0 ? 1 : 0);
    }

    private Double getDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException ex) {
            return -1.0;
        }
    }
}
