package org.ngel.station.service.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.ngel.station.core.service.StationDailyDataService;
import org.ngel.station.core.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * @author vgarg
 */
@RestController
@RequestMapping(value = "/upload", produces = {"application/hal+json", "application/json"})
@Slf4j
public class UploadDailyDataResource {

    @Autowired
    private StationService stationService;

    @Autowired
    private StationDailyDataService stationDailyDataService;

    @RequestMapping(value = "/daily-data", method = RequestMethod.POST)
    public void readCSVFile() throws Exception {
        Reader in = null;
        try {
            URL url = UploadDailyDataResource.class.getResource("/data/2016.tau.pm.1d.csv");
            in = new InputStreamReader(new FileInputStream(new File(url.toURI())), "UTF-8");
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
            int i = 0;
            for (CSVRecord record : records) {
                String ngelId = record.get(0);
                if (StringUtils.equals(ngelId, "NGEL_ID")) {
                    i++;
                    continue;
                }

                log.info("Processing {} record.", i);
                stationDailyDataService.loadStationDailyData(record);
                i++;
            }
            log.info("Inserted {} records.", i);
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    @PostMapping(value = "/stations")
    public void uploadStations() throws Exception {
        Reader in = null;
        try {
            URL url = UploadDailyDataResource.class.getResource("/data/Global_Locations_NGEL_PM.csv");
            in = new InputStreamReader(new FileInputStream(new File(url.toURI())), "UTF-8");
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
            int i = 0;
            for (CSVRecord record : records) {
                String ngelId = record.get(0);
                if (StringUtils.equals(ngelId, "NGELID")) {
                    i++;
                    continue;
                }
                log.info("NGEL Id: {}", ngelId);
                stationService.loadStation(record);
                i++;
            }
            log.info("Inserted {} records.", i);
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }
}
