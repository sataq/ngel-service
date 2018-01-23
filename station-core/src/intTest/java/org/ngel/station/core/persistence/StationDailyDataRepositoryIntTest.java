package org.ngel.station.core.persistence;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.ngel.station.core.config.SpringTestContextInitializer;
import org.ngel.station.core.domain.model.StationDailyData;
import org.ngel.station.core.domain.model.StationDailyDataRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author vgarg
 */
public class StationDailyDataRepositoryIntTest extends SpringTestContextInitializer {

    private static final String NGEL_ID1 = "ngelId1";
    private static final String NGEL_ID2 = "ngelId2";
    private static final String NGEL_ID3 = "ngelId3";
    private static final String NGEL_ID4 = "ngelId4";
    private static final LocalDate EXPECTED_OCCURRED = LocalDate.now().minusDays(1);

    @Autowired
    private StationDailyDataRepository stationDailyDataRepository;

    @Autowired
    private SpringDataStationDailyDataRepository springDataStationDailyDataRepository;

    @Before
    public void setup() {
        setupStationDailyData();
    }

    @After
    public void teardown() {
        removeStationDailyData();
    }

    @Test
    public void findLastOccurred() {
        StationDailyData stationDailyData = stationDailyDataRepository.findLastOccurred(NGEL_ID1);
        assertThat(stationDailyData.getNgelId(), equalTo(NGEL_ID1));
        assertThat(stationDailyData.getOccurred(), equalTo(EXPECTED_OCCURRED));
    }

    @Test
    public void findStationDailyData() {
        StationDailyData stationDailyData = stationDailyDataRepository.findStationDailyData(NGEL_ID1, EXPECTED_OCCURRED);
        assertThat(stationDailyData.getNgelId(), equalTo(NGEL_ID1));
        assertThat(stationDailyData.getOccurred(), equalTo(EXPECTED_OCCURRED));
        assertThat(stationDailyData.getTau3Mean(), equalTo(1.0));
    }

    @Test
    public void findLast60DaysStationData() {
        List<StationDailyData> last60DaysData = stationDailyDataRepository.findLast60DaysStationData(NGEL_ID3);
        assertThat(last60DaysData, hasSize(60));
        assertThat(last60DaysData.get(0).getOccurred(), equalTo(EXPECTED_OCCURRED.minusDays(0)));
        assertThat(last60DaysData.get(0).getPm25Mean(), equalTo(0.0));
        assertThat(last60DaysData.get(0).getTau3Mean(), equalTo(0.0));

        last60DaysData = stationDailyDataRepository.findLast60DaysStationData(NGEL_ID1);
        assertThat(last60DaysData, hasSize(2));
    }

    @Test
    public void findLast60DaysStationDataEmpty() {
        List<StationDailyData> last60DaysData = stationDailyDataRepository.findLast60DaysStationData(NGEL_ID4);
        assertThat(last60DaysData, hasSize(0));
    }

    @Test
    public void findStationData() {
        List<StationDailyData> dailyData = stationDailyDataRepository.findStationData(NGEL_ID3, LocalDate.now().minusDays(50), LocalDate.now().minusDays(30));
        assertThat(dailyData, hasSize(21));
    }

    @Test
    public void findStationDataNoData() {
        List<StationDailyData> dailyData = stationDailyDataRepository.findStationData(NGEL_ID3, LocalDate.now().minusDays(110), LocalDate.now().minusDays(100));
        assertThat(dailyData, hasSize(0));
    }

    private void setupStationDailyData() {
        List<StationDailyData> dailyDataList = new ArrayList<>();
        dailyDataList.add(StationDailyData.builder().ngelId(NGEL_ID1).pm25Mean(2.0).tau3Mean(2.0).occurred(EXPECTED_OCCURRED.minusDays(2)).build());
        dailyDataList.add(StationDailyData.builder().ngelId(NGEL_ID2).occurred(EXPECTED_OCCURRED).build());
        dailyDataList.add(StationDailyData.builder().ngelId(NGEL_ID1).pm25Mean(3.0).tau3Mean(1.0).occurred(EXPECTED_OCCURRED).build());

        for (int i = 0; i < 70; i++) {
            dailyDataList.add(StationDailyData.builder().ngelId(NGEL_ID3).pm25Mean(1.0 * i).tau3Mean(1.0 * i).occurred(EXPECTED_OCCURRED.minusDays(i)).build());
        }

        springDataStationDailyDataRepository.save(dailyDataList);
    }

    private void removeStationDailyData() {
        springDataStationDailyDataRepository.deleteAll();
    }
}
