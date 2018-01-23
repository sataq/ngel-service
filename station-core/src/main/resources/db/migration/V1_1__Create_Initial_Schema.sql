CREATE TABLE station (
  id                  BIGINT            NOT NULL PRIMARY KEY AUTO_INCREMENT,
  ngel_id             VARCHAR(36)       NOT NULL,
  station             VARCHAR(256)      NOT NULL,
  city                VARCHAR(50)       NOT NULL DEFAULT '',
  country             VARCHAR(50)       NOT NULL DEFAULT '',
  latitude            DECIMAL(14,12)    NOT NULL DEFAULT -1,
  longitude           DECIMAL(15,12)    NOT NULL DEFAULT -1,

  UNIQUE KEY `unique_ngel_id` (ngel_id),
  INDEX `idx_station` (station),
  INDEX `idx_country` (country)
);

CREATE TABLE station_daily_data (
  id                  BIGINT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
  ngel_id             VARCHAR(36)     NOT NULL,
  occurred            DATETIME        NOT NULL,
  pm25_mean           DECIMAL(10,6)   NOT NULL DEFAULT -1,
  pm25_std            DECIMAL(10,6)   NOT NULL DEFAULT -1,
  pm25_median         DECIMAL(10,6)   NOT NULL DEFAULT -1,
  pm_count            DECIMAL(10,6)   NOT NULL DEFAULT -1,
  sat_filename        VARCHAR(256)    NOT NULL DEFAULT '',
  sat_hour            DECIMAL(10,6)   NOT NULL DEFAULT -1,
  search_radius       DECIMAL(10,6)   NOT NULL DEFAULT -1,
  tau_nearest         DECIMAL(10,6)   NOT NULL DEFAULT -1,
  tau_pix             DECIMAL(10,6)   NOT NULL DEFAULT -1,
  tau3_mean           DECIMAL(10,6)   NOT NULL DEFAULT -1,
  tau3_median         DECIMAL(10,6)   NOT NULL DEFAULT -1,
  tau3_std            DECIMAL(10,6)   NOT NULL DEFAULT -1,

  INDEX `idx_ngel_id` (ngel_id),
  INDEX `idx_ngel_id_occurred` (ngel_id ASC, occurred DESC)
);
