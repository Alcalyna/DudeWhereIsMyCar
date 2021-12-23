CREATE TABLE IF NOT EXISTS allocations
(
    id UUID PRIMARY KEY,
    member_id UUID NOT NULL,
    parking_lot_id UUID NOT NULL,
    license_plate VARCHAR(25) NOT NULL,
    FOREIGN KEY (member_id) REFERENCES members(id),
    FOREIGN KEY (parking_lot_id) REFERENCES parking_lots(id),
    FOREIGN KEY (license_plate) REFERENCES license_plates(license_plate)
    );