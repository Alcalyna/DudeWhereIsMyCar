ALTER TABLE members
    ADD COLUMN fk_license_plate VARCHAR(25);

ALTER TABLE members
    ADD CONSTRAINT fk_member_license_plate FOREIGN KEY(fk_license_plate) REFERENCES license_plates(license_plate);