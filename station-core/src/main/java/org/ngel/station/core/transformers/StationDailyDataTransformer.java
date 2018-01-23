package org.ngel.station.core.transformers;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.ngel.station.common.representation.StationDailyDataRepresentation;
import org.ngel.station.core.domain.model.StationDailyData;
import org.springframework.stereotype.Component;

/**
 * @author vgarg
 */
@Component
public class StationDailyDataTransformer {

    public List<StationDailyDataRepresentation> transform(List<StationDailyData> stationDailyData) {
        if (CollectionUtils.isEmpty(stationDailyData)) {
            return Collections.emptyList();
        }

        return stationDailyData.stream().map(this::transform).collect(toList());
    }

    private StationDailyDataRepresentation transform(StationDailyData stationDailyData) {
        return StationDailyDataRepresentation.builder().ngelId(stationDailyData.getNgelId())
                .occurred(stationDailyData.getOccurred()).pm25Mean(stationDailyData.getPm25Mean())
                .tau3Mean(stationDailyData.getTau3Mean()).build();
    }
}
