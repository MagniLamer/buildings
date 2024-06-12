INSERT INTO  building.buildings(id, name, address_id, description)
VALUES ('d215b5f8-0249-4dc5-89a3-51fd148cfb42', 'first building', 1, 'First');

INSERT INTO  building.buildings(id, name, address_id, description)
VALUES ('3315b5f8-0249-4dc5-89a3-51fd148cfb33', 'second building', 2, 'First');

INSERT INTO  building.address(id, street, number, postal_code, city,country ,coordinate_x, coordinate_y, building_id)
VALUES (1, 'Street 1', 11, 'postal code 1', 'New York', 'USA', '0.1111', '0.1234', 'd215b5f8-0249-4dc5-89a3-51fd148cfb42');

INSERT INTO  building.address(id, street, number, postal_code, city,country ,coordinate_x, coordinate_y, building_id)
VALUES (2, 'Street 1', 22, 'postal code 2', 'New York', 'USA', '0.1111', '0.1234', '3315b5f8-0249-4dc5-89a3-51fd148cfb33');