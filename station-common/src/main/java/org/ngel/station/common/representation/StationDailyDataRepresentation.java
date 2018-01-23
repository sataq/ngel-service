package org.ngel.station.common.representation;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import org.joda.time.LocalDate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author vgarg
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class StationDailyDataRepresentation implements Serializable {
    private static final long serialVersionUID = 5252703324607016145L;

    private final String ngelId;
    private final LocalDate occurred;
    private final Double pm25Mean;
    private final Double pm25Std;
    private final Double pm25Median;
    private final Integer pmCount;
    private final String satFilename;
    private final Double satHour;
    private final Double searchRadius;
    private final Double tauNearest;
    private final Double tauPix;
    private final Double tau3Mean;
    private final Double tau3Median;
    private final Double tau3Std;
}
