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
