package org.ngel.station.core.service;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.ngel.station.common.representation.StationRepresentation;
import org.ngel.station.core.TestConstants;
import org.ngel.station.core.domain.model.StationRepository;
import org.ngel.station.core.transformers.StationTransformer;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * @author vgarg
 */
@RunWith(MockitoJUnitRunner.class)
public class DefaultStationServiceTest {

    @InjectMocks
    private DefaultStationService stationService;

    @Mock
    private StationRepository stationRepository;

    @Mock
    private StationDailyDataService stationDailyDataService;

    @Before
    public void setup() {
        ReflectionTestUtils.setField(stationService, "stationTransformer", new StationTransformer());
    }

    @Test
    public void getStations() throws Exception {
        when(stationRepository.totalStations()).thenReturn(2L);
        when(stationRepository.findByPage(anyInt())).thenReturn(Collections.singletonList(TestConstants.getStation()));
        List<StationRepresentation> stations = stationService.getStations();
        assertThat(stations, hasSize(1));
    }

    @Test
    public void getStationsWithPm25Mean() throws Exception {
        when(stationRepository.totalStations()).thenReturn(2L);
        when(stationRepository.findByPage(anyInt())).thenReturn(Collections.singletonList(TestConstants.getStation()));
        when(stationDailyDataService.getLatestPm25Mean(anyString())).thenReturn(TestConstants.PM25_MEAN);
        List<StationRepresentation> stations = stationService.getStationsWithPm25Mean();
        assertThat(stations, hasSize(1));
        assertThat(stations.get(0).getLatestPM25Mean(), equalTo(TestConstants.PM25_MEAN));
    }

    @Test
    public void getTotalPages() throws Exception {
        assertThat(stationService.getTotalPages(102L), equalTo(2));
        assertThat(stationService.getTotalPages(100L), equalTo(1));
        assertThat(stationService.getTotalPages(10L), equalTo(1));
    }

}
