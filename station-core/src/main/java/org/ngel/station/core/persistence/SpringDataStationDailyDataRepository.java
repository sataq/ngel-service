package org.ngel.station.core.persistence;

import java.util.List;

import org.joda.time.LocalDate;
import org.ngel.station.core.domain.model.StationDailyData;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author vgarg
 */
public interface SpringDataStationDailyDataRepository extends PagingAndSortingRepository<StationDailyData, Long> {

    StationDailyData findFirstByNgelIdOrderByOccurredDesc(String ngelId);

    StationDailyData findByNgelIdAndOccurred(String ngelId, LocalDate occurred);

    List<StationDailyData> findByNgelIdOrderByOccurredDesc(String ngelId);

    List<StationDailyData> findByNgelIdAndOccurredBetween(String ngelId, LocalDate startDate, LocalDate endDate);
}
