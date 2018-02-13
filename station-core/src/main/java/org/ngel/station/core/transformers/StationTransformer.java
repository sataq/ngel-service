package org.ngel.station.core.transformers;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.ngel.station.common.representation.StationRepresentation;
import org.ngel.station.core.domain.model.Station;
import org.springframework.stereotype.Component;

/**
 * @author vgarg
 */
@Component
public class StationTransformer {

    public List<StationRepresentation> transform(List<Station> stations) {
        if (CollectionUtils.isEmpty(stations)) {
            return Collections.emptyList();
        }

        return stations.stream().map(this::transform).collect(toList());
    }

    private StationRepresentation transform(Station station) {
        return StationRepresentation.builder().ngelId(station.getNgelId()).name(station.getStation()).city(station.getCity())
                    .country(station.getCountry()).latitude(station.getLatitude()).longitude(station.getLongitude())
                .latestPM25Mean(station.getPm25Mean()).build();
    }
}
