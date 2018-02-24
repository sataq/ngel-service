package org.ngel.station.common.representation;

import java.io.Serializable;
import java.util.List;

import org.joda.time.LocalDate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author vgarg
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor
public class StationDailyDataRepresentationCollection implements Serializable {
    private static final long serialVersionUID = -4251643567043028429L;

    private final List<StationDailyDataRepresentation> stationDailyData;
    private final LocalDate minOccurred;
    private final LocalDate maxOccurred;
}
