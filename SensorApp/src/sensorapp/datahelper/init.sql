CREATE DATABASE simpleweatherstation
  WITH OWNER = stationuser
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'en_US.UTF-8'
       LC_CTYPE = 'en_US.UTF-8'
       CONNECTION LIMIT = -1;


CREATE TABLE sensors
(
  sensor_name character varying(44),
  sensor_type character varying(55),
  sensor_location character varying(44),
  sensor_update_time integer,
  sensor_id serial NOT NULL,
  CONSTRAINT "sensID" PRIMARY KEY (sensor_id),
  CONSTRAINT name UNIQUE (sensor_name)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE sensors
  OWNER TO stationuser;


CREATE TABLE sensors_info
(
  sensor_data character varying(10),
  sensor_date date,
  id integer NOT NULL DEFAULT nextval('sensors_sesnor_id_seq'::regclass),
  sensor_time time without time zone,
  sensor_id serial NOT NULL,
  CONSTRAINT id_sensor_info_pkey PRIMARY KEY (id),
  CONSTRAINT "senID" FOREIGN KEY (sensor_id)
      REFERENCES sensors (sensor_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE sensors_info
  OWNER TO stationuser;

-- Index: "fki_senID"

-- DROP INDEX "fki_senID";

CREATE INDEX "fki_senID"
  ON sensors_info
  USING btree
  (sensor_id);

