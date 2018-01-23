package org.ngel.station.core.transformers;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.ngel.station.common.representation.StationRepresentation;
import org.ngel.station.core.TestConstants;

/**
 * @author vgarg
 */
public class StationTransformerTest {

    private StationTransformer stationTransformer;

    @Before
    public void setup() {
        stationTransformer = new StationTransformer();
    }

    @Test
    public void transform() throws Exception {
        assertThat(stationTransformer.transform(null), hasSize(0));

        List<StationRepresentation> stations = stationTransformer.transform(Collections.singletonList(TestConstants.getStation()));
        assertThat(stations, hasSize(1));
        assertThat(stations.get(0).getNgelId(), equalTo(TestConstants.NGEL_ID));
    }

}
