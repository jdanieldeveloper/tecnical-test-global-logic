-------------------------------------------------------------------------------------------------------------------
--  DATA MODEL IS A EXTRACT OF PARTICIPANTS FROM THE DATA MODEL RESOURCE BOOK VOLUME 1
-------------------------------------------------------------------------------------------------------------------

-------------------------------------------------------------------------------------------------------------------
--  SCHEMA FOR TABLE USER_LOGIN
--  THIS TABLE CONTAINS THE REGISTERS USERS AT THE SYSTEM.
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
                LAST_LOGIN_DATE_TIME TIMESTAMP,
                DISABLED_DATE_TIME TIMESTAMP,
                SUCCESSIVE_FAILED_LOGINS NUMERIC(20,0),
                EXTERNAL_AUTH_ID VARCHAR(255),
                USER_LDAP_DN VARCHAR(255),
                DISABLED_BY VARCHAR(255),
                PARTY_ID BIGINT,
  CONSTRAINT USER_LOGIN_PK PRIMARY KEY (PARTY_ID));
   --
   -- COMMENT ON COLUMN USER_LOGIN.USER_LOGIN_ID IS 'User identifier';

-------------------------------------------------------------------------------------------------------------------
--  SCHEMA FOR TABLE PARTY_TYPE
--  THIS TABLE CONTAINS THE PARTY TYPES FOR EXAMPLE PERSON, CORPORATION, TEAM ETC.
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
-- THIS TABLE CONTAINS THE PARTY(PARTICIPANTS) OF THE SYSTEM.
-- EVERYTHING THAT CAN INTERACT WITH THE SYSTEM CAN BE A PARTY FROM A USER TO A GOVERNMENT ETC.
-------------------------------------------------------------------------------------------------------------------
  CREATE TABLE  PARTY (
				PARTY_ID BIGINT NOT NULL,
				PARTY_UUID VARCHAR(36),
				PARTY_TYPE_ID VARCHAR(20),
				PARTY_NAME VARCHAR(255),
				EXTERNAL_ID VARCHAR(20), 
				DESCRIPTION VARCHAR(255), 
				STATUS_ID VARCHAR(20) NOT NULL, 
				CREATED_DATE TIMESTAMP NOT NULL, 
				CREATED_BY_USER_LOGIN VARCHAR(255) NOT NULL,
				LAST_MODIFIED_DATE TIMESTAMP, 
				LAST_MODIFIED_BY_USER_LOGIN VARCHAR(255), 				
	CONSTRAINT PARTY_PK PRIMARY KEY (PARTY_ID),
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
--  THIS TABLE CONTAINS THE ROLES OF PARTIES. FOR EXAMPLE ADMIN, VISITOR_READ, VISITOR_CREATE ETC.
-------------------------------------------------------------------------------------------------------------------
  CREATE TABLE ROLE_TYPE (
  				ROLE_TYPE_ID VARCHAR(20) NOT NULL, 
				PARENT_TYPE_ID VARCHAR(20),
				DESCRIPTION VARCHAR(255),
  CONSTRAINT ROLE_TYPE_PK PRIMARY KEY (ROLE_TYPE_ID),
  CONSTRAINT ROLE_TYPE_PAR FOREIGN KEY (PARENT_TYPE_ID)
			REFERENCES ROLE_TYPE (ROLE_TYPE_ID));

-------------------------------------------------------------------------------------------------------------------
--  SCHEMA FOR TABLE PARTY_ROLE
--  THIS TABLE CONTAINS THE RELATIONSHIP BETWEEN PARTIES AND ROLES.
-------------------------------------------------------------------------------------------------------------------
  CREATE TABLE PARTY_ROLE (
  				PARTY_ID BIGINT NOT NULL, 
				ROLE_TYPE_ID VARCHAR(20) NOT NULL,
				DESCRIPTION VARCHAR(255),
  CONSTRAINT PARTY_ROLE_PK PRIMARY KEY (PARTY_ID, ROLE_TYPE_ID));

-------------------------------------------------------------------------------------------------------------------
--  SCHEMA FOR TABLE TELECOM_NUMBER
--  THIS TABLE CONTAINS THE PHONE DATA OF THE PARTIES.
-------------------------------------------------------------------------------------------------------------------
CREATE TABLE  TELECOM_NUMBER (
				TELECOM_NUMBER_ID BIGINT NOT NULL,
				COUNTRY_CODE VARCHAR(2),
				CITY_CODE INTEGER,
				CONTACT_NUMBER VARCHAR(20),
	CONSTRAINT TELECOM_NUMBER_PK PRIMARY KEY (TELECOM_NUMBER_ID));

-------------------------------------------------------------------------------------------------------------------
-- SCHEMA FOR TABLE CONTACT_MECH
-- THIS TABLE CONTAINS THE CONTACT MECHANISM FROM THE PARTIES.
-- IT'S A RELATIONSHIP BETWEEN PARTIES AN TELECOM NUMBER OR POSTAL ADDRESS BUT FOR THIS EXERCISE WAS OMITTED.
-------------------------------------------------------------------------------------------------------------------
CREATE TABLE  CONTACT_MECH (
				CONTACT_MECH_ID BIGINT NOT NULL,
				CONTACT_MECH_TYPE_ID VARCHAR(20) NOT NULL,
				TELECOM_NUMBER_ID BIGINT NOT NULL,
				PARTY_ID BIGINT NOT NULL,
	CONSTRAINT CONTACT_MECH_PK PRIMARY KEY (CONTACT_MECH_ID),
	CONSTRAINT TELECOM_NUMBER_CONTACT_MECH_FK FOREIGN KEY (TELECOM_NUMBER_ID)
			   REFERENCES TELECOM_NUMBER(TELECOM_NUMBER_ID),
	CONSTRAINT PARTY_CONTACT_MECH_FK FOREIGN KEY (PARTY_ID)
    		   REFERENCES PARTY(PARTY_ID));