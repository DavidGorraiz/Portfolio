-- Create procedure
create or replace PROCEDURE Generar_Informe_Casos_Dinamico(
    p_fecha_inicio IN DATE,
    p_fecha_fin IN DATE
)
AS
    v_sql_table CLOB;  -- Variable para la sentencia de creación de la tabla
    v_sql_insert CLOB; -- Variable para la sentencia de inserción dinámica
    v_anio_inicio NUMBER; -- Año inicial del rango
    v_anio_fin NUMBER;    -- Año final del rango
BEGIN
    -- Obtener los años del rango
    v_anio_inicio := EXTRACT(YEAR FROM p_fecha_inicio);
    v_anio_fin := EXTRACT(YEAR FROM p_fecha_fin);

    -- Construir dinámicamente la sentencia de creación de la tabla
    v_sql_table := 'CREATE TABLE Reporte_Casos_Dinamico (Pais VARCHAR2(100), ';
    FOR anio IN v_anio_inicio..v_anio_fin LOOP
        v_sql_table := v_sql_table || 'New_Cases_' || anio || ' NUMBER, ' ||
                                        'New_Deaths_' || anio || ' NUMBER, ';
    END LOOP;
    v_sql_table := v_sql_table || 'Total_Casos NUMBER, Total_Deaths NUMBER)';

    -- Eliminar la tabla si ya existe
    BEGIN
        EXECUTE IMMEDIATE 'DROP TABLE Reporte_Casos_Dinamico';
    EXCEPTION
        WHEN OTHERS THEN NULL; -- Ignorar error si la tabla no existe
    END;

    -- Crear la tabla
    EXECUTE IMMEDIATE v_sql_table;

    -- Construir dinámicamente la sentencia de inserción
    v_sql_insert := 'INSERT INTO Reporte_Casos_Dinamico (Pais, ';
    FOR anio IN v_anio_inicio..v_anio_fin LOOP
        v_sql_insert := v_sql_insert || 'New_Cases_' || anio || ', New_Deaths_' || anio || ', ';
    END LOOP;
    v_sql_insert := v_sql_insert || 'Total_Casos, Total_Deaths) SELECT COUNTRY, ';

    -- Agregar las sumas dinámicas por año
    FOR anio IN v_anio_inicio..v_anio_fin LOOP
        v_sql_insert := v_sql_insert || 
            'SUM(CASE WHEN EXTRACT(YEAR FROM DATE_REPORTED) = ' || anio || 
            ' THEN NEW_CASES ELSE 0 END) AS New_Cases_' || anio || ', ' ||
            'SUM(CASE WHEN EXTRACT(YEAR FROM DATE_REPORTED) = ' || anio || 
            ' THEN NEW_DEATHS ELSE 0 END) AS New_Deaths_' || anio || ', ';
    END LOOP;

    -- Agregar las columnas totales
    v_sql_insert := v_sql_insert || 
        'MAX(CUMULATIVE_CASES) AS Total_Casos, MAX(CUMULATIVE_DEATHS) AS Total_Deaths ' ||
        'FROM temp WHERE DATE_REPORTED BETWEEN :1 AND :2 GROUP BY COUNTRY';

    -- Ejecutar la inserción
    EXECUTE IMMEDIATE v_sql_insert USING p_fecha_inicio, p_fecha_fin;

    COMMIT;
END;
/

-- Verify functionality
BEGIN
    Generar_Informe_Casos_Dinamico(TO_DATE('01/01/2020', 'DD/MM/YYYY'), TO_DATE('31/12/2024', 'DD/MM/YYYY'));
END;
/