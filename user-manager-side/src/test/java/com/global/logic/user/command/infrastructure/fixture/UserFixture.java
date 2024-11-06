package com.global.logic.user.command.infrastructure.fixture;

import com.global.logic.user.command.domain.user.Email;
import com.global.logic.user.command.domain.user.Password;
import com.global.logic.user.command.domain.user.Phone;
import com.global.logic.user.command.domain.user.Role;
import com.global.logic.user.command.domain.user.Type;
import com.global.logic.user.command.domain.user.User;
import com.global.logic.user.command.domain.user.UserId;
import com.global.logic.user.command.domain.user.UserUuid;
import com.global.logic.user.command.infrastructure.enums.RoleEnum;
import com.global.logic.user.command.infrastructure.enums.UserTypeEnum;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class UserFixture {

    public static User getUserWithAllFieldsToAdd() {
        // userId
        UserId userId = new UserId(1L);
        // userUuid
        UserUuid userUuid = new UserUuid(UUID.randomUUID().toString());
        // name can be optional
        Optional<String> name = Optional.ofNullable("Daniel Carvajal");
        // user type
        Type userType = new Type(UserTypeEnum.VISITOR.getTypeId());
        // email
        Email email = new Email("dcarvajal3@gmail.com");
        // password
        Password password = new Password("m1Passw2rd");

        // roles can be optional but in this case assign by default
        Role read = new Role(RoleEnum.VISITOR_READ.getRoleId(), RoleEnum.VISITOR_READ.getDescription());
        Role create = new Role(RoleEnum.VISITOR_CREATE.getRoleId(), RoleEnum.VISITOR_CREATE.getDescription());
        Optional<Set<Role>> roles = Optional.of(Set.of(read, create));

        // phones can be optional
        Optional<Set<Phone>> phones =
                Optional.ofNullable(Set.of(new Phone("56", 9 , 12345678L)));

        return new User(userId, userUuid, userType, name, email, password, roles, phones);
    }

    public static User getUserWithUserIdNullField() {
        // userId
        UserId userId = null;
        // userUuid
        UserUuid userUuid = new UserUuid(UUID.randomUUID().toString());
        // name can be optional
        Optional<String> name = Optional.ofNullable("Daniel Carvajal");
        // user type
        Type userType = new Type(UserTypeEnum.VISITOR.getTypeId());
        // email
        Email email = new Email("dcarvajal3@gmail.com");
        // password
        Password password = new Password("m1Passw2rd");

        // roles can be optional but in this case assign by default
        Role read = new Role(RoleEnum.VISITOR_READ.getRoleId(), RoleEnum.VISITOR_READ.getDescription());
        Role create = new Role(RoleEnum.VISITOR_CREATE.getRoleId(), RoleEnum.VISITOR_CREATE.getDescription());
        Optional<Set<Role>> roles = Optional.of(Set.of(read, create));

        // phones can be optional
        Optional<Set<Phone>> phones =
                Optional.ofNullable(Set.of(new Phone("56", 9 , 12345678L)));

        return new User(userId, userUuid, userType, name, email, password, roles, phones);
    }

    public static User getUserWithUserUuidNullField() {
        // userId
        UserId userId = new UserId(1L);
        // userUuid
        UserUuid userUuid = null;
        // name can be optional
        Optional<String> name = Optional.ofNullable("Daniel Carvajal");
        // user type
        Type userType = new Type(UserTypeEnum.VISITOR.getTypeId());
        // email
        Email email = new Email("dcarvajal3@gmail.com");
        // password
        Password password = new Password("m1Passw2rd");

        // roles can be optional but in this case assign by default
        Role read = new Role(RoleEnum.VISITOR_READ.getRoleId(), RoleEnum.VISITOR_READ.getDescription());
        Role create = new Role(RoleEnum.VISITOR_CREATE.getRoleId(), RoleEnum.VISITOR_CREATE.getDescription());
        Optional<Set<Role>> roles = Optional.of(Set.of(read, create));

        // phones can be optional
        Optional<Set<Phone>> phones =
                Optional.ofNullable(Set.of(new Phone("56", 9 , 12345678L)));

        return new User(userId, userUuid, userType, name, email, password, roles, phones);
    }



    public static User getUserWithUserTypeNullField() {
        // userId
        UserId userId = new UserId(1L);
        // userUuid
        UserUuid userUuid = new UserUuid(UUID.randomUUID().toString());
        // name can be optional
        Optional<String> name = Optional.ofNullable("Daniel Carvajal");
        // user type
        Type userType = null;
        // email
        Email email = new Email("dcarvajal3@gmail.com");
        // password
        Password password = new Password("m1Passw2rd");

        // roles can be optional but in this case assign by default
        Role read = new Role(RoleEnum.VISITOR_READ.getRoleId(), RoleEnum.VISITOR_READ.getDescription());
        Role create = new Role(RoleEnum.VISITOR_CREATE.getRoleId(), RoleEnum.VISITOR_CREATE.getDescription());
        Optional<Set<Role>> roles = Optional.of(Set.of(read, create));

        // phones can be optional
        Optional<Set<Phone>> phones =
                Optional.ofNullable(Set.of(new Phone("56", 9 , 12345678L)));

        return new User(userId, userUuid, userType, name, email, password, roles, phones);
    }

    public static User getUserWithUserEmailNullField() {
        // userId
        UserId userId = new UserId(1L);
        // userUuid
        UserUuid userUuid = new UserUuid(UUID.randomUUID().toString());
        // name can be optional
        Optional<String> name = Optional.ofNullable("Daniel Carvajal");
        // user type
        Type userType = new Type(UserTypeEnum.VISITOR.getTypeId());
        // email
        Email email = null;
        // password
        Password password = new Password("m1Passw2rd");

        // roles can be optional but in this case assign by default
        Role read = new Role(RoleEnum.VISITOR_READ.getRoleId(), RoleEnum.VISITOR_READ.getDescription());
        Role create = new Role(RoleEnum.VISITOR_CREATE.getRoleId(), RoleEnum.VISITOR_CREATE.getDescription());
        Optional<Set<Role>> roles = Optional.of(Set.of(read, create));

        // phones can be optional
        Optional<Set<Phone>> phones =
                Optional.ofNullable(Set.of(new Phone("56", 9 , 12345678L)));

        return new User(userId, userUuid, userType, name, email, password, roles, phones);
    }

    public static User getUserWithUserPasswordNullField() {
        // userId
        UserId userId = new UserId(1L);
        // userUuid
        UserUuid userUuid = new UserUuid(UUID.randomUUID().toString());
        // name can be optional
        Optional<String> name = Optional.ofNullable("Daniel Carvajal");
        // user type
        Type userType = new Type(UserTypeEnum.VISITOR.getTypeId());
        // email
        Email email = new Email("dcarvajal3@gmail.com");
        // password
        Password password = null;

        // roles can be optional but in this case assign by default
        Role read = new Role(RoleEnum.VISITOR_READ.getRoleId(), RoleEnum.VISITOR_READ.getDescription());
        Role create = new Role(RoleEnum.VISITOR_CREATE.getRoleId(), RoleEnum.VISITOR_CREATE.getDescription());
        Optional<Set<Role>> roles = Optional.of(Set.of(read, create));

        // phones can be optional
        Optional<Set<Phone>> phones =
                Optional.ofNullable(Set.of(new Phone("56", 9 , 12345678L)));

        return new User(userId, userUuid, userType, name, email, password, roles, phones);
    }

    public static User getUserWithUserPasswordNullAndInvalidEmail() {
        // userId
        UserId userId = new UserId(1L);
        // userUuid
        UserUuid userUuid = new UserUuid(UUID.randomUUID().toString());
        // name can be optional
        Optional<String> name = Optional.ofNullable("Daniel Carvajal");
        // user type
        Type userType = new Type(UserTypeEnum.VISITOR.getTypeId());
        // email
        Email email = new Email("dcarvajal3@gm"); //fail email
        // password
        Password password = null;

        // roles can be optional but in this case assign by default
        Role read = new Role(RoleEnum.VISITOR_READ.getRoleId(), RoleEnum.VISITOR_READ.getDescription());
        Role create = new Role(RoleEnum.VISITOR_CREATE.getRoleId(), RoleEnum.VISITOR_CREATE.getDescription());
        Optional<Set<Role>> roles = Optional.of(Set.of(read, create));

        // phones can be optional
        Optional<Set<Phone>> phones =
                Optional.ofNullable(Set.of(new Phone("56", 9 , 12345678L)));

        return new User(userId, userUuid, userType, name, email, password, roles, phones);
    }
}
