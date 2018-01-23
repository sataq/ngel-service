CREATE TABLE station (
  id                  BIGINT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
  ngel_id             VARCHAR(36)     NOT NULL,
  station             VARCHAR(36)     NOT NULL,
  city                CHAR(24)        NOT NULL,
  country             VARCHAR(36)     NOT NULL,
  latitude            FLOAT(10, 6)    NOT NULL,
  longitude           FLOAT(10, 6)    NOT NULL,

  UNIQUE KEY `unique_ngel_id` (ngel_id),
  INDEX `station_idx` (station)
);
