package org.ngel.station.core.persistence;

import java.util.List;

import org.ngel.station.core.domain.model.Station;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author vgarg
 */
public interface SpringDataStationRepository extends PagingAndSortingRepository<Station, Long> {

    @Query("SELECT COUNT(s) FROM Station s")
    Long countAll();

    @Query("SELECT st from Station st WHERE st.ngelId like %:oapm%")
    List<Station> findByNgelIdWithOAPM(@Param("oapm") String oapm);

    Station findByNgelId(String ngelId);

    Station findByStation(String station);

    List<Station> findByCountry(String country);
}
