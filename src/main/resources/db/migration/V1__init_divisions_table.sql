CREATE TABLE IF NOT EXISTS directors
(
    id UUID PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS divisions
(
    id UUID PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    original_name VARCHAR(50),
    director UUID NOT NULL,
    FOREIGN KEY (director) REFERENCES directors(id)
)