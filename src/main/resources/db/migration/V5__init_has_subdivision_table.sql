CREATE TABLE IF NOT EXISTS has_subdivision
(
    id_parent UUID NOT NULL,
    id_subdivision UUID NOT NULL UNIQUE,
    CHECK(id_parent != id_subdivision),
    FOREIGN KEY (id_parent) REFERENCES divisions(id),
    FOREIGN KEY (id_subdivision) REFERENCES divisions(id),
    PRIMARY KEY(id_parent, id_subdivision)
)