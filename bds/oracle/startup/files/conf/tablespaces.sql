ALTER SESSION SET container = XEPDB1;
CREATE TABLESPACE development DATAFILE '/opt/oracle/scripts/setup/development_tablespace.dbf' SIZE 100M AUTOEXTEND ON MAXSIZE 1G;