CREATE TABLE water_pipeline_description
(
    "idX"    BIGINT NOT NULL,
    "idY"    BIGINT NOT NULL,
    "length" BIGINT NOT NULL,
    CONSTRAINT "ID_P" PRIMARY key ("idY")
);
CREATE TABLE two_point_search_root
(
    "idA" BIGINT NOT NULL,
    "idB" BIGINT NOT NULL,
    CONSTRAINT "ID_T" PRIMARY key ("idB")
);
CREATE TABLE result
(
    "id" BIGINT NOT NULL AUTO_INCREMENT,
    "flag" BOOLEAN NOT NULL,
    "distance" BIGINT ,
    CONSTRAINT "ID_G" PRIMARY key ("id")
);
