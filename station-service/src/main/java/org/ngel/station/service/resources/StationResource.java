package org.ngel.station.service.resources;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.joda.time.LocalDate;
import org.ngel.station.common.exception.InvalidDateException;
import org.ngel.station.common.representation.StationRepresentation;
import org.ngel.station.common.representation.StationRepresentationCollection;
import org.ngel.station.core.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @author vgarg
 */
@RestController
@RequestMapping(value = "/stations", produces = {"application/hal+json", "application/json"})
@Slf4j
public class StationResource {

    @Autowired
    private StationService stationService;

    @GetMapping
    public ResponseEntity<Resource<StationRepresentationCollection>> getAllStations(@RequestParam(value = "occurred", required = false) Long date) {
        List<StationRepresentation> stationRepresentations;
        if (date == null) {
            stationRepresentations = stationService.getStationsWithPm25Mean();
        } else {
            validateDate(date);
            stationRepresentations = stationService.getStationsWithPm25Mean(new LocalDate(date));
        }
        if (CollectionUtils.isEmpty(stationRepresentations)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new Resource<>(new StationRepresentationCollection(stationRepresentations)), HttpStatus.OK);
    }

    private void validateDate(Long date) {
        try {
            LocalDate valid = new LocalDate(date);
            if (valid.isAfter(LocalDate.now())) {
                log.error("Invalid date provided.");
                throw new InvalidDateException();
            }
        } catch (IllegalArgumentException ex) {
            log.error(ex.getMessage());
            throw new InvalidDateException();
        }
    }
}
