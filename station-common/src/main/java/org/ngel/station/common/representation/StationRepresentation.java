package org.ngel.station.common.representation;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author vgarg
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class StationRepresentation implements Serializable {
    private static final long serialVersionUID = -2627960646681679922L;

    private final String ngelId;
    private final String name;
    private final String city;
    private final String country;
    private final Double latitude;
    private final Double longitude;

    private Double latestPM25Mean;
}
