package org.ngel.station.core.service;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.ngel.station.core.TestConstants;
import org.ngel.station.core.domain.model.StationDailyData;
import org.ngel.station.core.domain.model.StationDailyDataRepository;
import org.ngel.station.core.transformers.StationDailyDataTransformer;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * @author vgarg
 */
@RunWith(MockitoJUnitRunner.class)
public class DefaultStationDailyDataServiceTest {

    @InjectMocks
    private DefaultStationDailyDataService stationDailyDataService;

    @Mock
    private StationDailyDataRepository stationDailyDataRepository;

    @Before
    public void setup() {
        ReflectionTestUtils.setField(stationDailyDataService, "stationDailyDataTransformer", new StationDailyDataTransformer());
    }

    @Test
    public void getLatestPm25Mean() throws Exception {
        when(stationDailyDataRepository.findLastOccurred(anyString())).thenReturn(null).thenReturn(StationDailyData.builder().ngelId(TestConstants.NGEL_ID).pm25Mean(TestConstants.PM25_MEAN).build());

        assertThat(stationDailyDataService.getLatestPm25Mean(TestConstants.NGEL_ID), equalTo(StationDailyDataService.DEFAULT_PM25_MEAN));
        assertThat(stationDailyDataService.getLatestPm25Mean(TestConstants.NGEL_ID), equalTo(TestConstants.PM25_MEAN));
    }

    @Test
    public void getStationDailyDataForLast60Days() throws Exception {
        when(stationDailyDataRepository.findMinAndMaxOccurred(anyString())).thenReturn(Arrays.asList(LocalDate.now(), LocalDate.now()));
        when(stationDailyDataRepository.findLast60DaysStationData(anyString())).thenReturn(Collections.singletonList(TestConstants.getStationDailyData()));
        assertThat(stationDailyDataService.getStationDailyDataForLast60Days(TestConstants.NGEL_ID).getStationDailyData(), hasSize(1));
    }

    @Test
    public void getStationDailyData() throws Exception {
        when(stationDailyDataRepository.findMinAndMaxOccurred(anyString())).thenReturn(Arrays.asList(LocalDate.now(), LocalDate.now()));
        when(stationDailyDataRepository.findStationData(anyString(), any(LocalDate.class), any(LocalDate.class))).thenReturn(Collections.singletonList(TestConstants.getStationDailyData()));
        assertThat(stationDailyDataService.getStationDailyData(TestConstants.NGEL_ID, LocalDate.now().minusDays(2), LocalDate.now()).getStationDailyData(), hasSize(1));
    }
}
