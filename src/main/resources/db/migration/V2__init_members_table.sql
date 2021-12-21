CREATE TABLE IF NOT EXISTS cities
(
    zip VARCHAR(10) PRIMARY KEY,
    city VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS addresses
(
    id UUID PRIMARY KEY,
    street_name VARCHAR(50) NOT NULL,
    street_number VARCHAR(6) NOT NULL,
    zip VARCHAR(10) NOT NULL,
    FOREIGN KEY (zip) REFERENCES cities(zip)
);

CREATE TABLE IF NOT EXISTS email_addresses
(
    id UUID PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    domain VARCHAR(25) NOT NULL
);

CREATE TABLE IF NOT EXISTS members
(
    id UUID PRIMARY KEY,
    last_name VARCHAR(50) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    address UUID NOT NULL,
    phone_number VARCHAR(20),
    mobile_number VARCHAR(20),
    email_address UUID,
    registration_date DATE NOT NULL,
    FOREIGN KEY (address) REFERENCES addresses(id),
    FOREIGN KEY (email_address) REFERENCES email_addresses(id)
);

CREATE TABLE IF NOT EXISTS license_plates
(
    member_id UUID NOT NULL UNIQUE,
    license_plate VARCHAR(25) NOT NULL,
    PRIMARY KEY (member_id, license_plate),
    FOREIGN KEY (member_id) REFERENCES members(id)
);