-------------------------------------------------------------------------------------------------------------------
-- SEED DATA FOR TABLE PARTY_TYPE
-------------------------------------------------------------------------------------------------------------------
INSERT INTO PARTY_TYPE(PARTY_TYPE_ID, PARENT_TYPE_ID, DESCRIPTION ) VALUES ('VISITOR', NULL, 'Default party type when to do some user');

-------------------------------------------------------------------------------------------------------------------
-- SEED DATA FOR TABLE ROLE_TYPE
-------------------------------------------------------------------------------------------------------------------
INSERT INTO ROLE_TYPE(ROLE_TYPE_ID, PARENT_TYPE_ID, DESCRIPTION ) VALUES ('ADMIN', NULL, 'Role Administrator with all grant');
INSERT INTO ROLE_TYPE(ROLE_TYPE_ID, PARENT_TYPE_ID, DESCRIPTION ) VALUES ('VISITOR_READ', NULL, 'Role Visitor only read content');
INSERT INTO ROLE_TYPE(ROLE_TYPE_ID, PARENT_TYPE_ID, DESCRIPTION ) VALUES ('VISITOR_CREATE', NULL, 'Role Visitor only create content');
INSERT INTO ROLE_TYPE(ROLE_TYPE_ID, PARENT_TYPE_ID, DESCRIPTION ) VALUES ('VISITOR_UPDATE', NULL, 'Role Visitor only update content');
INSERT INTO ROLE_TYPE(ROLE_TYPE_ID, PARENT_TYPE_ID, DESCRIPTION ) VALUES ('VISITOR_DELETE', NULL, 'Role Visitor only delete content');



