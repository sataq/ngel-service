package org.ngel.station.core.transformers;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.ngel.station.common.representation.StationDailyDataRepresentation;
import org.ngel.station.core.TestConstants;

/**
 * @author vgarg
 */
public class StationDailyDataTransformerTest {

    private StationDailyDataTransformer stationDailyDataTransformer;

    @Before
    public void setup() {
        stationDailyDataTransformer = new StationDailyDataTransformer();
    }

    @Test
    public void transform() throws Exception {
        assertThat(stationDailyDataTransformer.transform(null), hasSize(0));

        List<StationDailyDataRepresentation> representations = stationDailyDataTransformer.transform(Collections.singletonList(TestConstants.getStationDailyData()));
        assertThat(representations, hasSize(1));
    }

}
