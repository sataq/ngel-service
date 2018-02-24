package org.ngel.station.core.persistence;

import java.util.List;

import org.joda.time.LocalDate;
import org.ngel.station.core.domain.model.StationDailyData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author vgarg
 */
public interface SpringDataStationDailyDataRepository extends PagingAndSortingRepository<StationDailyData, Long> {

    StationDailyData findFirstByNgelIdOrderByOccurredDesc(String ngelId);

    StationDailyData findByNgelIdAndOccurred(String ngelId, LocalDate occurred);

    List<StationDailyData> findByNgelIdOrderByOccurredDesc(String ngelId);

    List<StationDailyData> findByNgelIdAndOccurredBetween(String ngelId, LocalDate startDate, LocalDate endDate);

    @Query(value = "Select min(sdd.occurred) as min_occurred, max(sdd.occurred) as max_occurred from station_daily_data sdd where ngel_id=:ngel_id", nativeQuery = true)
    List<Object[]> findMinAndMaxOccurred(@Param("ngel_id") String ngelId);

    @Query(value = "Select min(sdd.occurred) as min_occurred, max(sdd.occurred) as max_occurred from station_daily_data sdd", nativeQuery = true)
    List<Object[]> findMinAndMaxOccurred();
}
