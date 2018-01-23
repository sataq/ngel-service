package org.ngel.station.core.persistence;

import java.util.ArrayList;
import java.util.List;

import org.ngel.station.core.domain.model.Station;
import org.ngel.station.core.domain.model.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

/**
 * @author vgarg
 */
@Repository
public class DefaultStationRepository implements StationRepository {

    @Autowired
    private SpringDataStationRepository stationRepository;

    @Override
    public Long totalStations() {
        return stationRepository.countAll();
    }

    @Override
    public List<Station> findByPage(int page) {
        Iterable<Station> iterable = stationRepository.findAll(new PageRequest(page - 1, PAGE_SIZE));
        List<Station> allStations = new ArrayList<>();
        iterable.forEach(allStations::add);
        return allStations;
    }

    @Override
    public Station findByNgelId(String ngelId) {
        return stationRepository.findByNgelId(ngelId);
    }

    @Override
    public Station findByStationName(String stationName) {
        return stationRepository.findByStation(stationName);
    }

    @Override
    public List<Station> findByCountry(String country) {
        return stationRepository.findByCountry(country);
    }

    @Override
    public void save(Station station) {
        stationRepository.save(station);
    }
}
