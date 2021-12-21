CREATE TABLE IF NOT EXISTS contact_persons
(
    id UUID PRIMARY KEY,
    email_address UUID NOT NULL UNIQUE,
    phone_number VARCHAR(20),
    mobile_number VARCHAR(20),
    CHECK (phone_number IS NOT NULL OR mobile_number IS NOT NULL),
    FOREIGN KEY (email_address) REFERENCES email_addresses(id)
);

CREATE TABLE IF NOT EXISTS parking_lots
(
    id UUID PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    category VARCHAR(50) NOT NULL,
    max_capacity INTEGER NOT NULL,
    price_per_hour MONEY CHECK (price_per_hour >= 0::MONEY) NOT NULL,
    contact_person UUID NOT NULL,
    address UUID NOT NULL,
    division UUID NOT NULL,
    FOREIGN KEY (contact_person) REFERENCES contact_persons(id),
    FOREIGN KEY (address) REFERENCES addresses(id),
    FOREIGN KEY (division) REFERENCES divisions(id)
);