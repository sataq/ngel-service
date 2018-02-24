package org.ngel.station.core.service;

import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.ngel.station.common.representation.StationDailyDataRepresentation;
import org.ngel.station.common.representation.StationDailyDataRepresentationCollection;
import org.ngel.station.core.domain.model.StationDailyData;
import org.ngel.station.core.domain.model.StationDailyDataRepository;
import org.ngel.station.core.persistence.SpringDataStationDailyDataRepository;
import org.ngel.station.core.transformers.StationDailyDataTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author vgarg
 */
@Service
public class DefaultStationDailyDataService implements StationDailyDataService {

    @Autowired
    private SpringDataStationDailyDataRepository springDataStationDailyDataRepository;

    @Autowired
    private StationDailyDataRepository stationDailyDataRepository;

    @Autowired
    private StationDailyDataTransformer stationDailyDataTransformer;

    @Override
    public void loadStationDailyData(CSVRecord csvRecord) {
        if (csvRecord == null) {
            return;
        }

        StationDailyData.StationDailyDataBuilder builder = StationDailyData.builder();
        setNgelId(builder, csvRecord.get(0));
        setOccurred(builder, csvRecord.get(1));
        setPm25Mean(builder, csvRecord.get(2));
        setPm25Std(builder, csvRecord.get(3));
        setPm25Median(builder, csvRecord.get(4));
        setPmCount(builder, csvRecord.get(5));
        setSatFilename(builder, csvRecord.get(6));
        setSatHour(builder, csvRecord.get(7));
        setSearchRadius(builder, csvRecord.get(8));
        setTauNearest(builder, csvRecord.get(9));
        setTauPix(builder, csvRecord.get(10));
        setTau3Mean(builder, csvRecord.get(11));
        setTau3Median(builder, csvRecord.get(12));
        setTau3Std(builder, csvRecord.get(13));

        springDataStationDailyDataRepository.save(builder.build());
    }

    @Override
    public Double getLatestPm25Mean(String ngelId) {
        StationDailyData stationDailyData = stationDailyDataRepository.findLastOccurred(ngelId);
        return getPm25Mean(stationDailyData);
    }

    @Override
    public Double getPm25Mean(String ngelId, LocalDate occurred) {
        StationDailyData stationDailyData = stationDailyDataRepository.findStationDailyData(ngelId, occurred);
        return getPm25Mean(stationDailyData);
    }

    @Override
    public StationDailyDataRepresentationCollection getStationDailyDataForLast60Days(String ngelId) {
        List<StationDailyData> stationDailyData = stationDailyDataRepository.findLast60DaysStationData(ngelId);
        List<StationDailyDataRepresentation> representations = stationDailyDataTransformer.transform(stationDailyData);
        List<LocalDate> minAndMaxOccurred = stationDailyDataRepository.findMinAndMaxOccurred(ngelId);
        return new StationDailyDataRepresentationCollection(representations, minAndMaxOccurred.get(0), minAndMaxOccurred.get(1));
    }

    @Override
    public StationDailyDataRepresentationCollection getStationDailyData(String ngelId, LocalDate startDate, LocalDate endDate) {
        List<StationDailyData> stationDailyData = stationDailyDataRepository.findStationData(ngelId, startDate, endDate);
        List<StationDailyDataRepresentation> representations = stationDailyDataTransformer.transform(stationDailyData);
        List<LocalDate> minAndMaxOccurred = stationDailyDataRepository.findMinAndMaxOccurred(ngelId);
        return new StationDailyDataRepresentationCollection(representations, minAndMaxOccurred.get(0), minAndMaxOccurred.get(1));
    }

    private Double getPm25Mean(StationDailyData stationDailyData) {
        if (stationDailyData != null) {
            return stationDailyData.getPm25Mean();
        }
        return DEFAULT_PM25_MEAN;
    }

    private void setNgelId(StationDailyData.StationDailyDataBuilder builder, String ngelId) {
        builder.ngelId(ngelId);
    }

    private void setOccurred(StationDailyData.StationDailyDataBuilder builder, String dateStr) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("M/d/yy");
        LocalDate date = LocalDate.parse(dateStr, formatter);
        builder.occurred(date);
    }

    private void setPm25Mean(StationDailyData.StationDailyDataBuilder builder, String pm25Mean) {
        builder.pm25Mean(Double.parseDouble(pm25Mean));
    }

    private void setPm25Std(StationDailyData.StationDailyDataBuilder builder, String pm25Std) {
        builder.pm25Std(Double.parseDouble(pm25Std));
    }

    private void setPm25Median(StationDailyData.StationDailyDataBuilder builder, String pm25Median) {
        builder.pm25Median(Double.parseDouble(pm25Median));
    }

    private void setPmCount(StationDailyData.StationDailyDataBuilder builder, String pmCount) {
        builder.pmCount(Integer.parseInt(pmCount));
    }

    private void setSatFilename(StationDailyData.StationDailyDataBuilder builder, String fileName) {
        builder.satFilename((StringUtils.equals(fileName, "-1") ? "" : fileName));
    }

    private void setSatHour(StationDailyData.StationDailyDataBuilder builder, String satHour) {
        builder.satHour(Double.parseDouble(satHour));
    }

    private void setSearchRadius(StationDailyData.StationDailyDataBuilder builder, String searchRadius) {
        builder.searchRadius(Double.parseDouble(searchRadius));
    }

    private void setTauNearest(StationDailyData.StationDailyDataBuilder builder, String tauNearest) {
        builder.tauNearest(Double.parseDouble(tauNearest));
    }

    private void setTauPix(StationDailyData.StationDailyDataBuilder builder, String tauPix) {
        builder.tauPix(Double.parseDouble(tauPix));
    }

    private void setTau3Mean(StationDailyData.StationDailyDataBuilder builder, String tau3Mean) {
        builder.tau3Mean(Double.parseDouble(tau3Mean));
    }

    private void setTau3Median(StationDailyData.StationDailyDataBuilder builder, String tau3Median) {
        builder.tau3Median(Double.parseDouble(tau3Median));
    }

    private void setTau3Std(StationDailyData.StationDailyDataBuilder builder, String tau3Std) {
        builder.tau3Std(Double.parseDouble(tau3Std));
    }
}
