-- Create table of control
CREATE TABLE TAB_CONTROL (
    No_registers NUMBER(10),
    execution_date DATE,
    operation_type VARCHAR2(6),
    table_name VARCHAR2(50)
);

-- anonymous stored procedure for inserting data into regions
DECLARE
    CURSOR r IS 
        SELECT DISTINCT NVL(region,'NR') AS id,
            CASE 
                    WHEN region='AFRO' THEN 'África'
                    WHEN region='EMRO' THEN 'Mediterráneo Oriental'
                    WHEN region='AMRO' THEN 'Américas'
                    WHEN region='OTHER' THEN 'Otras Regiones'
                    WHEN region='WPRO' THEN 'Pacífico Occidental'
                    WHEN region='EURO' THEN 'Europa'
                    WHEN region='SEARO' THEN 'Asia Sudoriental'
                    WHEN region IS NULL THEN 'No tiene region'
                END AS name
        FROM temp;
    v_count_rows NUMBER;
    v_duplicate NUMBER;
    error_number CONSTANT NUMBER := -20001;
BEGIN
    v_count_rows := 0;
    for v_datos in r loop
        exit when r%notfound;
        -- Check if there are values duplicate
        select COUNT(1) into v_duplicate
        from regions
        where id = v_datos.id;

        IF v_duplicate > 0 THEN
            -- Inform the error of duplicate data
            RAISE_APPLICATION_ERROR(error_number, 'Existen datos duplicados.');
        ELSE
            INSERT INTO regions VALUES(v_datos.id, v_datos.name);
            v_count_rows := v_count_rows + 1;
        END IF;
    end loop;
    DBMS_OUTPUT.PUT_LINE(v_count_rows || ' rows inserted into regions.');
    INSERT INTO tab_control VALUES(v_count_rows, sysdate, 'INSERT', 'REGIONS');
    DBMS_OUTPUT.PUT_LINE('1 row inserted into tab_control.');
END;
/

-- anonymous stored procedure for inserting data into countries
DECLARE
    CURSOR c IS
    select distinct country_code, country, nvl(region, 'NR') as region from temp;
    v_count NUMBER;
    v_duplicate NUMBER;
    error_number CONSTANT NUMBER := -20001;
BEGIN
    v_count := 0;
    FOR v_datos IN c LOOP
        EXIT WHEN c%NOTFOUND;
        -- Check if there are values duplicate
        select COUNT(1) into v_duplicate
        from countries
        where code = v_datos.country_code;

        IF v_duplicate > 0 THEN
            -- Inform the error of duplicate data
            RAISE_APPLICATION_ERROR(error_number, 'Existen datos duplicados.');
        ELSE
            INSERT INTO COUNTRIES VALUES(v_datos.country_code, v_datos.country, v_datos.region);
            v_count := v_count + 1;
        END IF;
    END LOOP;
    DBMS_OUTPUT.PUT_LINE(v_count || ' rows inserted into countries.');
    INSERT INTO tab_control VALUES(v_count, sysdate, 'INSERT', 'COUNTRIES');
    DBMS_OUTPUT.PUT_LINE('1 row inserted into tab_control.');
END;
/

-- function for date validation
CREATE OR REPLACE FUNCTION check_date(p_date IN DATE)
RETURN BOOLEAN IS
v_check BOOLEAN;
BEGIN    
    IF p_date > sysdate or p_date < '01/01/20' THEN
        v_check := false;
    ELSE
        v_check := true;
    END IF;
    RETURN v_check;
END check_date;
/

-- verify the behavior of the function
DECLARE
    p_date DATE := '05/01/14';
BEGIN    
    IF check_date(p_date) THEN
        DBMS_OUTPUT.PUT_LINE('Fecha correcta');
    ELSE 
        DBMS_OUTPUT.PUT_LINE('Fecha incorrecta');
    END IF;
END;
/

-- anonymous stored procedure for inserting data into cases
DECLARE
    CURSOR ca IS
    select date_reported, nvl(new_cases, 0) as new_cases, cumulative_cases, 
        nvl(new_deaths, 0) as new_deaths, cumulative_deaths, country_code 
    from temp;
    
    v_count NUMBER;
    v_duplicate NUMBER;
    error_number CONSTANT NUMBER := -20001;
BEGIN
    v_count := 0;
    FOR v_datos IN ca LOOP
        EXIT WHEN ca%NOTFOUND;
        -- Check if there are values duplicate
        select COUNT(1) into v_duplicate
        from cases
        where date_reported = v_datos.date_reported and new_cases = v_datos.new_cases 
        and cumulative_cases = v_datos.cumulative_cases and new_deaths = v_datos.new_deaths
        and cumulative_deaths = v_datos.cumulative_deaths and id_country = v_datos.country_code;
        
        IF v_duplicate > 0 THEN
            -- Inform the error of duplicate data
            RAISE_APPLICATION_ERROR(error_number, 'Existen datos duplicados.');
        ELSE
            IF check_date(v_datos.date_reported) THEN
                -- Make the insert with a dinamic execution
                EXECUTE IMMEDIATE
                'INSERT INTO CASES (date_reported, new_cases, cumulative_cases, new_deaths, cumulative_deaths, id_country) 
                VALUES (:1, :2, :3, :4, :5, :6)'
                USING v_datos.date_reported, v_datos.new_cases, v_datos.cumulative_cases, v_datos.new_deaths, v_datos.cumulative_deaths, v_datos.country_code;
                v_count := v_count+ 1;
            ELSE
                RAISE_APPLICATION_ERROR(error_number, 'Alguna fecha es incorrecta');
            END IF;  
        END IF;
    END LOOP;
    DBMS_OUTPUT.PUT_LINE(v_count || ' rows inserted into cases.');
    INSERT INTO tab_control VALUES(v_count, sysdate, 'INSERT', 'CASES');
    DBMS_OUTPUT.PUT_LINE('1 row inserted into tab_control.');
END;
/

-- Create a package to store all the logic
create or replace PACKAGE load_data IS
    FUNCTION check_date(p_date DATE) RETURN BOOLEAN;
    PROCEDURE insert_cases;
    PROCEDURE insert_countries;
    PROCEDURE insert_regions;
END;

create or replace PACKAGE BODY load_data IS

    PROCEDURE insert_regions IS
        CURSOR r IS 
            SELECT DISTINCT NVL(region,'NR') AS id,
                CASE 
                        WHEN region='AFRO' THEN 'África'
                        WHEN region='EMRO' THEN 'Mediterráneo Oriental'
                        WHEN region='AMRO' THEN 'Américas'
                        WHEN region='OTHER' THEN 'Otras Regiones'
                        WHEN region='WPRO' THEN 'Pacífico Occidental'
                        WHEN region='EURO' THEN 'Europa'
                        WHEN region='SEARO' THEN 'Asia Sudoriental'
                        WHEN region IS NULL THEN 'No tiene region'
                    END AS name
            FROM temp;
        v_count_rows NUMBER;
        v_duplicate NUMBER;
        error_number CONSTANT NUMBER := -20001;
    BEGIN
        v_count_rows := 0;
        for v_datos in r loop
            exit when r%notfound;
            -- Check if there are values duplicate
            select COUNT(1) into v_duplicate
            from regions
            where id = v_datos.id;

            IF v_duplicate > 0 THEN
                -- Inform the error of duplicate data
                RAISE_APPLICATION_ERROR(error_number, 'Existen datos duplicados.');
            ELSE
                INSERT INTO regions VALUES(v_datos.id, v_datos.name);
                v_count_rows := v_count_rows + 1;
            END IF;
        end loop;
        DBMS_OUTPUT.PUT_LINE(v_count_rows || ' rows inserted into regions.');
        INSERT INTO tab_control VALUES(v_count_rows, sysdate, 'INSERT', 'REGIONS');
        DBMS_OUTPUT.PUT_LINE('1 row inserted into tab_control.');
    END insert_regions;

    PROCEDURE insert_countries IS
        CURSOR c IS
        select distinct country_code, country, nvl(region, 'NR') as region from temp;
        v_count NUMBER;
        v_duplicate NUMBER;
        error_number CONSTANT NUMBER := -20001;
    BEGIN
        v_count := 0;
        FOR v_datos IN c LOOP
            EXIT WHEN c%NOTFOUND;
            -- Check if there are values duplicate
            select COUNT(1) into v_duplicate
            from countries
            where code = v_datos.country_code;

            IF v_duplicate > 0 THEN
                -- Inform the error of duplicate data
                RAISE_APPLICATION_ERROR(error_number, 'Existen datos duplicados.');
            ELSE
                INSERT INTO COUNTRIES VALUES(v_datos.country_code, v_datos.country, v_datos.region);
                v_count := v_count + 1;
            END IF;
        END LOOP;
        DBMS_OUTPUT.PUT_LINE(v_count || ' rows inserted into countries.');
        INSERT INTO tab_control VALUES(v_count, sysdate, 'INSERT', 'COUNTRIES');
        DBMS_OUTPUT.PUT_LINE('1 row inserted into tab_control.');
    END insert_countries;

    FUNCTION check_date(p_date IN DATE)
    RETURN BOOLEAN IS
    v_check BOOLEAN;
    BEGIN    
        IF p_date > sysdate or p_date < '01/01/20' THEN
            v_check := false;
        ELSE
            v_check := true;
        END IF;
        RETURN v_check;
    END check_date;

    PROCEDURE insert_cases IS
        CURSOR ca IS
        select date_reported, nvl(new_cases, 0) as new_cases, cumulative_cases, 
            nvl(new_deaths, 0) as new_deaths, cumulative_deaths, country_code 
        from temp;

        v_count NUMBER;
        v_duplicate NUMBER;
        error_number CONSTANT NUMBER := -20001;
    BEGIN
        v_count := 0;
        FOR v_datos IN ca LOOP
            EXIT WHEN ca%NOTFOUND;
            -- Check if there are values duplicate
            select COUNT(1) into v_duplicate
            from cases
            where date_reported = v_datos.date_reported and new_cases = v_datos.new_cases 
            and cumulative_cases = v_datos.cumulative_cases and new_deaths = v_datos.new_deaths
            and cumulative_deaths = v_datos.cumulative_deaths and id_country = v_datos.country_code;

            IF v_duplicate > 0 THEN
                -- Inform the error of duplicate data
                RAISE_APPLICATION_ERROR(error_number, 'Existen datos duplicados.');
            ELSE
                IF check_date(v_datos.date_reported) THEN
                    -- Make the insert with a dinamic execution
                    EXECUTE IMMEDIATE
                    'INSERT INTO CASES (date_reported, new_cases, cumulative_cases, new_deaths, cumulative_deaths, id_country) 
                    VALUES (:1, :2, :3, :4, :5, :6)'
                    USING v_datos.date_reported, v_datos.new_cases, v_datos.cumulative_cases, v_datos.new_deaths, v_datos.cumulative_deaths, v_datos.country_code;
                    v_count := v_count+ 1;
                ELSE
                    RAISE_APPLICATION_ERROR(error_number, 'Alguna fecha es incorrecta');
                END IF;  
            END IF;
        END LOOP;
        DBMS_OUTPUT.PUT_LINE(v_count || ' rows inserted into cases.');
        INSERT INTO tab_control VALUES(v_count, sysdate, 'INSERT', 'CASES');
        DBMS_OUTPUT.PUT_LINE('1 row inserted into tab_control.');
    END insert_cases;

END;




