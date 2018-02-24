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
public class StationRepresentationCollection implements Serializable {
    private static final long serialVersionUID = -6502556879367884194L;

    private final List<StationRepresentation> stations;
    private final LocalDate minOccurred;
    private final LocalDate maxOccurred;
}
