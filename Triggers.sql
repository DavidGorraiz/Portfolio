-- Create audit table
create table audit_tab(
table_name VARCHAR2(20), 
operation VARCHAR2(10), 
user_name VARCHAR2(100), 
change_date DATE
);

-- Trigger cases table
create or replace TRIGGER CASES_AUDIT
AFTER UPDATE OR DELETE
ON CASES
FOR EACH ROW
DECLARE v_operation audit_tab.operation%TYPE;
BEGIN
    IF UPDATING THEN
        v_operation := 'UPDATE';
    ELSE
        v_operation := 'DELETE';
    END IF;
    INSERT INTO AUDIT_TAB(table_name, operation, user_name, change_date)
    VALUES('CASES',v_operation,USER,SYSDATE);
END;
/

-- Trigger countries table
create or replace TRIGGER COUNTRIES_AUDIT
AFTER UPDATE OR DELETE
ON COUNTRIES
FOR EACH ROW
DECLARE v_operation audit_tab.operation%TYPE;
BEGIN
    IF UPDATING THEN
        v_operation := 'UPDATE';
    ELSE
        v_operation := 'DELETE';
    END IF;
    INSERT INTO AUDIT_TAB(table_name, operation, user_name, change_date)
    VALUES('COUNTRIES',v_operation,USER,SYSDATE);
END;
/

-- Trigger regions table
create or replace TRIGGER REGIONS_AUDIT
AFTER UPDATE OR DELETE
ON REGIONS
FOR EACH ROW
DECLARE v_operation audit_tab.operation%TYPE;
BEGIN
    IF UPDATING THEN
        v_operation := 'UPDATE';
    ELSE
        v_operation := 'DELETE';
    END IF;
    INSERT INTO AUDIT_TAB(table_name, operation, user_name, change_date)
    VALUES('REGIONS',v_operation,USER,SYSDATE);
END;
/