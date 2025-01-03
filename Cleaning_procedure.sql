-- Create the cleaning procedure
CREATE OR REPLACE PROCEDURE CLEANING_DATA IS
v_count_cases NUMBER;
v_count_countries NUMBER;
v_count_regions NUMBER;
BEGIN
    SELECT COUNT(1) INTO v_count_cases FROM CASES;
    DELETE FROM CASES;
    INSERT INTO tab_control VALUES(v_count_cases, sysdate, 'DELETE', 'CASES');
    DBMS_OUTPUT.PUT_LINE('1 row inserted into tab_control.');
    
    SELECT COUNT(1) INTO v_count_countries FROM COUNTRIES;
    DELETE FROM COUNTRIES;
    INSERT INTO tab_control VALUES(v_count_countries, sysdate, 'DELETE', 'COUNTRIES');
    DBMS_OUTPUT.PUT_LINE('2 rows inserted into tab_control.');
    
    SELECT COUNT(1) INTO v_count_regions FROM REGIONS;
    DELETE FROM REGIONS;
    INSERT INTO tab_control VALUES(v_count_regions, sysdate, 'DELETE', 'REGIONS');
    DBMS_OUTPUT.PUT_LINE('3 rows inserted into tab_control.');
END;
/

--Execute the procedure
begin
    CLEANING_DATA;
end;
/