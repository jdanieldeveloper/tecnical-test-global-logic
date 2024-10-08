-------------------------------------------------------------------------------------------------------------------
--                                                TABLES
-------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------------
--  SCHEMA FOR TABLE USER_LOGIN
--  THIS TABLE CONTAINS THE REGISTERS USERS AT THE SYSTEM
-------------------------------------------------------------------------------------------------------------------
  CREATE TABLE USER_LOGIN (
  				USER_LOGIN_ID VARCHAR(20) NOT NULL,
                CURRENT_PASSWORD VARCHAR(255),
                PASSWORD_HINT VARCHAR(255),
                IS_SYSTEM CHAR(1),
                ENABLED CHAR(1),
                HAS_LOGGED_OUT CHAR(1),
                REQUIRE_PASSWORD_CHANGE CHAR(1),
                LAST_CURRENCY_UOM VARCHAR(20),
                LAST_LOCALE VARCHAR(10),
                LAST_TIME_ZONE VARCHAR(60),
                DISABLED_DATE_TIME TIMESTAMP,
                SUCCESSIVE_FAILED_LOGINS NUMERIC(20,0),
                EXTERNAL_AUTH_ID VARCHAR(255),
                USER_LDAP_DN VARCHAR(255),
                DISABLED_BY VARCHAR(255),
                PARTY_ID BIGINT,
  CONSTRAINT USER_LOGIN_PK PRIMARY KEY (PARTY_ID));

-------------------------------------------------------------------------------------------------------------------
--  SCHEMA FOR TABLE PARTY_TYPE
-------------------------------------------------------------------------------------------------------------------
  CREATE TABLE PARTY_TYPE (
  				PARTY_TYPE_ID VARCHAR(20) NOT NULL, 
				PARENT_TYPE_ID VARCHAR(20), 
				DESCRIPTION VARCHAR(255), 				
  CONSTRAINT PARTY_TYPE_PK PRIMARY KEY (PARTY_TYPE_ID),
  CONSTRAINT PARTY_TYPE_PAR FOREIGN KEY (PARENT_TYPE_ID)
			REFERENCES PARTY_TYPE (PARTY_TYPE_ID));

-------------------------------------------------------------------------------------------------------------------
-- SCHEMA FOR TABLE PARTY 
-------------------------------------------------------------------------------------------------------------------
  CREATE TABLE  PARTY (
				PARTY_ID BIGINT NOT NULL, 				
				PARTY_TYPE_ID VARCHAR(20),  				
				EXTERNAL_ID VARCHAR(20), 
				DESCRIPTION VARCHAR(255), 
				STATUS_ID VARCHAR(20) NOT NULL, 
				CREATED_DATE TIMESTAMP NOT NULL, 
				CREATED_BY_USER_LOGIN VARCHAR(255) NOT NULL,
				LAST_MODIFIED_DATE TIMESTAMP, 
				LAST_MODIFIED_BY_USER_LOGIN VARCHAR(255), 				
	CONSTRAINT PARTY_PK PRIMARY KEY ("PARTY_ID"),
	CONSTRAINT USER_LOGIN_PARTY_FK FOREIGN KEY (PARTY_ID)
			   REFERENCES USER_LOGIN(PARTY_ID),
	CONSTRAINT PARTY_PTY_TYP_FK FOREIGN KEY (PARTY_TYPE_ID)
			REFERENCES PARTY_TYPE (PARTY_TYPE_ID)); 			
   --
   -- COMMENT ON COLUMN PARTY.PAATTY_ID IS 'Party identifier';

-------------------------------------------------------------------------------------------------------------------
--  SEQUENCE FOR TABLE PARTY
-------------------------------------------------------------------------------------------------------------------
CREATE SEQUENCE PARTY_ID_SEQ;

-----------------------------------------------------------------------------------------------------------------
--  SCHEMA FOR TABLE ROLE_TYPE
-------------------------------------------------------------------------------------------------------------------
  CREATE TABLE ROL,E_TYPE (
  				ROLE_TYPE_ID VARCHAR(20) NOT NULL, 
				PARENT_TYPE_ID VARCHAR(20),
				DESCRIPTION VARCHAR(255),
  CONSTRAINT ROLE_TYPE_PK PRIMARY KEY (ROLE_TYPE_ID),
  CONSTRAINT ROLE_TYPE_PAR FOREIGN KEY (PARENT_TYPE_ID)
			REFERENCES ROLE_TYPE (ROLE_TYPE_ID));

-------------------------------------------------------------------------------------------------------------------
--  SCHEMA FOR TABLE PARTY_ROLE
-------------------------------------------------------------------------------------------------------------------
  CREATE TABLE PARTY_ROLE (
  				PARTY_ID BIGINT NOT NULL, 
				ROLE_TYPE_ID VARCHAR(20) NOT NULL,
				DESCRIPTION VARCHAR(255),
  CONSTRAINT PARTY_ROLE_PK PRIMARY KEY (PARTY_ID, ROLE_TYPE_ID));

-------------------------------------------------------------------------------------------------------------------
-- SCHEMA FOR TABLE TELECOM_NUMBER
-------------------------------------------------------------------------------------------------------------------
CREATE TABLE  TELECOM_NUMBER (
				TELECOM_NUMBER_ID BIGINT NOT NULL,
				COUNTRY_CODE VARCHAR(2),
				CITY_CODE INTEGER,
				CONTACT_NUMBER VARCHAR(20),
	CONSTRAINT TELECOM_NUMBER_PK PRIMARY KEY ("TELECOM_NUMBER_ID");

-------------------------------------------------------------------------------------------------------------------
-- SCHEMA FOR TABLE CONTACT_MECH
-------------------------------------------------------------------------------------------------------------------
CREATE TABLE  CONTACT_MECH (
				CONTACT_MECH_ID BIGINT NOT NULL,
				CONTACT_MECH_TYPE_ID VARCHAR(20) NOT NULL,
				TELECOM_NUMBER_ID BIGINT NOT NULL,
				PARTY_ID BIGINT NOT NULL,
	CONSTRAINT CONTACT_MECH_PK PRIMARY KEY ("CONTACT_MECH_ID"),
	CONSTRAINT TELECOM_NUMBER_CONTACT_MECH_FK FOREIGN KEY (TELECOM_NUMBER_ID)
			   REFERENCES TELECOM_NUMBER(TELECOM_NUMBER_ID);
	CONSTRAINT PARTY_CONTACT_MECH_FK FOREIGN KEY (PARTY_ID)
    			   REFERENCES PARTY(PARTY_ID);