package org.ngel.station.core.persistence;

import static java.util.stream.Collectors.toList;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.joda.time.LocalDate;
import org.ngel.station.core.domain.model.StationDailyData;
import org.ngel.station.core.domain.model.StationDailyDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author vgarg
 */
@Repository
public class DefaultStationDailyDataRepository implements StationDailyDataRepository {

    @Autowired
    private SpringDataStationDailyDataRepository springDataStationDailyDataRepository;

    @Override
    public StationDailyData findLastOccurred(String ngelId) {
        return springDataStationDailyDataRepository.findFirstByNgelIdOrderByOccurredDesc(ngelId);
    }

    @Override
    public StationDailyData findStationDailyData(String ngelId, LocalDate occurred) {
        return springDataStationDailyDataRepository.findByNgelIdAndOccurred(ngelId, occurred);
    }

    @Override
    public List<StationDailyData> findLast60DaysStationData(String ngelId) {
        List<StationDailyData> stationDailyData = springDataStationDailyDataRepository.findByNgelIdOrderByOccurredDesc(ngelId);
        if (CollectionUtils.isNotEmpty(stationDailyData)) {
            if (stationDailyData.size() > 60) {
                stationDailyData = stationDailyData.subList(0, 60);
            }
            return stationDailyData.stream().filter(data -> data.getPm25Mean() > -1.0 && data.getTau3Mean() > -1.0)
                    .collect(toList());
        }
        return Collections.emptyList();
    }

    @Override
    public List<StationDailyData> findStationData(String ngelId, LocalDate startDate, LocalDate endDate) {
        List<StationDailyData> stationDailyData = springDataStationDailyDataRepository.findByNgelIdAndOccurredBetween(ngelId, startDate, endDate);
        if (CollectionUtils.isNotEmpty(stationDailyData)) {
            return stationDailyData.stream().filter(data -> data.getPm25Mean() > -1.0 && data.getTau3Mean() > -1.0)
                    .collect(toList());
        }
        return Collections.emptyList();
    }

    @Override
    public List<LocalDate> findMinAndMaxOccurred(String ngelId) {
        List<Object[]> dates = springDataStationDailyDataRepository.findMinAndMaxOccurred(ngelId);
        return Arrays.asList(new LocalDate((Timestamp) dates.get(0)[0]), new LocalDate((Timestamp) dates.get(0)[1]));
    }

    @Override
    public List<LocalDate> findMinAndMaxOccurred() {
        List<Object[]> dates = springDataStationDailyDataRepository.findMinAndMaxOccurred();
        return Arrays.asList(new LocalDate((Timestamp) dates.get(0)[0]), new LocalDate((Timestamp) dates.get(0)[1]));
    }
}
