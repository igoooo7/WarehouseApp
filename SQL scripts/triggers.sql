USE db_warehouse;

DROP TRIGGER IF EXISTS before_delete_on_users_delete_on_orders;
DROP TRIGGER IF EXISTS before_delete_on_parts_delete_on_orders;
DROP TRIGGER IF EXISTS before_delete_on_users_delete_on_parts;

DELIMITER $$

CREATE TRIGGER before_delete_on_users_delete_on_orders BEFORE DELETE ON users
FOR EACH ROW
BEGIN
DELETE FROM orders WHERE user_id = OLD.user_id;
END; $$

CREATE TRIGGER before_delete_on_parts_delete_on_orders BEFORE DELETE ON parts
FOR EACH ROW
BEGIN
DELETE FROM orders WHERE part_id = OLD.part_id;
END; $$

CREATE TRIGGER before_delete_on_users_delete_on_parts BEFORE DELETE ON users
FOR EACH ROW
BEGIN
DELETE FROM parts WHERE user_id = OLD.user_id;
END; $$

DELIMITER ;