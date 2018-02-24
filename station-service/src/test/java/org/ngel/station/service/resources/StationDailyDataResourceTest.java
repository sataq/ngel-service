package org.ngel.station.service.resources;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.ngel.station.common.exception.InvalidDateRangeException;
import org.ngel.station.common.representation.StationDailyDataRepresentation;
import org.ngel.station.common.representation.StationDailyDataRepresentationCollection;
import org.ngel.station.core.service.StationDailyDataService;
import org.springframework.http.HttpStatus;

/**
 * @author vgarg
 */
@RunWith(MockitoJUnitRunner.class)
public class StationDailyDataResourceTest {

    private static final String NGEL_ID = "ngelId";

    @InjectMocks
    private StationDailyDataResource stationDailyDataResource;

    @Mock
    private StationDailyDataService stationDailyDataService;

    @Test
    public void getStationDailyDataNotFound() throws Exception {
        when(stationDailyDataService.getStationDailyDataForLast60Days(anyString())).thenReturn(null);
        assertThat(stationDailyDataResource.getStationDailyData(NGEL_ID, null, null).getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
    }

    @Test
    public void getStationDailyData() throws Exception {
        when(stationDailyDataService.getStationDailyDataForLast60Days(anyString())).thenReturn(new StationDailyDataRepresentationCollection(Collections.singletonList(StationDailyDataRepresentation.builder().build()), null, null));
        StationDailyDataRepresentationCollection collection = stationDailyDataResource.getStationDailyData(NGEL_ID, null, null).getBody().getContent();
        assertThat(collection, notNullValue());
        assertThat(collection.getStationDailyData(), hasSize(1));
    }

    @Test(expected = InvalidDateRangeException.class)
    public void validateDateRangeStartAfterEnd() {
        stationDailyDataResource.validateDateRange(LocalDate.now().toDate().getTime(), LocalDate.now().minusDays(2).toDate().getTime());
    }

    @Test(expected = InvalidDateRangeException.class)
    public void validateDateRangeMoreThan60() {
        stationDailyDataResource.validateDateRange(LocalDate.now().minusDays(62).toDate().getTime(), LocalDate.now().toDate().getTime());
    }

    @Test
    public void getStationDailyDataWithDates() throws Exception {
        when(stationDailyDataService.getStationDailyData(anyString(), any(LocalDate.class), any(LocalDate.class))).thenReturn(new StationDailyDataRepresentationCollection(Collections.singletonList(StationDailyDataRepresentation.builder().build()), null, null));
        StationDailyDataRepresentationCollection collection = stationDailyDataResource.getStationDailyData(NGEL_ID, LocalDate.now().minusDays(2).toDate().getTime(), LocalDate.now().toDate().getTime()).getBody().getContent();
        assertThat(collection, notNullValue());
        assertThat(collection.getStationDailyData(), hasSize(1));
    }
}
