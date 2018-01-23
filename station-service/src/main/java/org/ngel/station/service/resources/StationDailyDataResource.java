package org.ngel.station.service.resources;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.ngel.station.common.exception.InvalidDateRangeException;
import org.ngel.station.common.representation.StationDailyDataRepresentation;
import org.ngel.station.common.representation.StationDailyDataRepresentationCollection;
import org.ngel.station.core.service.StationDailyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @author vgarg
 */
@RestController
@RequestMapping(value = "/station-daily-data", produces = {"application/hal+json", "application/json"})
@Slf4j
public class StationDailyDataResource {

    @Autowired
    private StationDailyDataService stationDailyDataService;

    @GetMapping("/{stationId}")
    public ResponseEntity<Resource<StationDailyDataRepresentationCollection>> getStationDailyData(@PathVariable("stationId") String stationId,
                                                                                                  @RequestParam(value = "startDate", required = false) Long startDate,
                                                                                                  @RequestParam(value = "endDate", required = false) Long endDate) {
        List<StationDailyDataRepresentation> stationDailyDataRepresentations;
        if (startDate == null || endDate == null) {
            stationDailyDataRepresentations = stationDailyDataService.getStationDailyDataForLast60Days(stationId);
        } else {
            validateDateRange(startDate, endDate);
            stationDailyDataRepresentations = stationDailyDataService.getStationDailyData(stationId, new LocalDate(startDate), new LocalDate(endDate));
        }
        if (CollectionUtils.isEmpty(stationDailyDataRepresentations)) {
            log.warn("No daily data found for given station: {}", stationId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new Resource<>(new StationDailyDataRepresentationCollection(stationDailyDataRepresentations)), HttpStatus.OK);
    }

    void validateDateRange(Long startDate, Long endDate) {
        try {
            LocalDate start = new LocalDate(startDate);
            LocalDate end = new LocalDate(endDate);
            if (start.isAfter(end) || Days.daysBetween(start, end).getDays() > 60) {
                throw new InvalidDateRangeException("Date range is either not valid or more than 60 days.");
            }
        } catch (IllegalArgumentException ex) {
            throw new InvalidDateRangeException("Invalid date provided.");
        }
    }
}
