package org.ngel.station.service.resources;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.ngel.station.common.exception.InvalidDateException;
import org.ngel.station.common.representation.StationRepresentation;
import org.ngel.station.common.representation.StationRepresentationCollection;
import org.ngel.station.core.service.StationService;
import org.springframework.http.HttpStatus;

/**
 * @author vgarg
 */
@RunWith(MockitoJUnitRunner.class)
public class StationResourceTest {

    @InjectMocks
    private StationResource stationResource;

    @Mock
    private StationService stationService;

    @Test
    public void getAllStationsNotFound() throws Exception {
        when(stationService.getStations()).thenReturn(null);
        assertThat(stationResource.getAllStations(null).getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
    }

    @Test
    public void getAllStations() throws Exception {
        when(stationService.getStationsWithPm25Mean()).thenReturn(new StationRepresentationCollection(Collections.singletonList(StationRepresentation.builder().build()), null, null));
        StationRepresentationCollection collection = stationResource.getAllStations(null).getBody().getContent();
        assertThat(collection, notNullValue());
        assertThat(collection.getStations(), hasSize(1));
    }

    @Test(expected = InvalidDateException.class)
    public void getAllStationsInvalidDate() throws Exception {
        when(stationService.getStationsWithPm25Mean()).thenReturn(new StationRepresentationCollection(Collections.singletonList(StationRepresentation.builder().build()), null, null));
        stationResource.getAllStations(LocalDate.now().plusDays(1).toDate().getTime()).getBody().getContent();
    }

}
