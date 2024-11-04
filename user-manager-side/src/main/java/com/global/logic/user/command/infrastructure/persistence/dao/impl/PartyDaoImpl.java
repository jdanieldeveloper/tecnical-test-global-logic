package com.global.logic.user.command.infrastructure.persistence.dao.impl;

import com.global.logic.user.command.infrastructure.dto.PartyDto;
import com.global.logic.user.command.infrastructure.dto.UserRoleDto;
import com.global.logic.user.command.infrastructure.enums.UserStatusEnum;
import com.global.logic.user.command.infrastructure.enums.UserTypeEnum;
import com.global.logic.user.command.infrastructure.exception.DatabaseException;
import com.global.logic.user.command.infrastructure.persistence.dao.PartyDao;
import com.global.logic.user.command.infrastructure.persistence.mybatis.mapper.PartyMapper;
import com.global.logic.user.query.infraestructure.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author daniel.carvajal
 */
@Slf4j
@Component
public class PartyDaoImpl implements PartyDao {

    @Autowired
    private PartyMapper partyMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public Long nexValueForIdentifier() {
        long partyId = 0;
        try {
            partyId = partyMapper.nexValueForIdentifier();
            if (partyId > 0) {
                log.info("PartyId identifier was created correctly!!!");

            } else {
                // defensing programming
                throw new IllegalStateException("Error partyId can be larger than of 0!!!");
            }
        } catch (Exception e) {
            log.error("Error while created partyId identifier!!!", e);
            //
            throw new DatabaseException(e.getMessage(), e);
        }
        return partyId;
    }

    @Override
    public PartyDto saveParty(PartyDto partyDto) {
        int rowAffected;
        try {
            // default values
            partyDto.setPartyType(UserTypeEnum.VISITOR.getTypeId());
            partyDto.setCreatedDate(LocalDateTime.now());
            partyDto.setCreateByUserLogin(partyDto.getUserLoginId());
            partyDto.setLastModifiedDate(LocalDateTime.now());
            partyDto.setStatusId(UserStatusEnum.ENABLED.getStatusId());

            // save party
            rowAffected = partyMapper.saveParty(partyDto);
            if (rowAffected == 1) {
                log.info("Party was created correctly!!!");

            } else {
                // defensing programming
                throw new IllegalStateException("Error row affected more than 1 when party was created!!!");
            }
        } catch (Exception e) {
            log.error("Error while created party!!!", e);
            //
            throw new DatabaseException(e.getMessage(), e);
        }
        return partyDto;
    }

    @Override
    public PartyDto saveUserLogin(PartyDto partyDto) {
        int rowAffected;
        try {
            // default values
            partyDto.setLastLoginDate(LocalDateTime.now());
            // encode pass for security
            partyDto.setCurrentPassword(passwordEncoder.encode(partyDto.getCurrentPassword()));

            rowAffected = partyMapper.saveUserLogin(partyDto);
            if (rowAffected == 1) {
                log.info("User was created correctly!!!");

            } else {
                // defensing programming
                throw new IllegalStateException("Error row affected more than 1 when user was created!!!");
            }
        } catch (Exception e) {
            log.error("Error while created user!!!", e);
            //
            throw new DatabaseException(e.getMessage(), e);
        }
        return partyDto;
    }

    @Override
    public UserRoleDto saveUserRole(UserRoleDto userRoleDto) {
        int rowAffected;
        try {
            rowAffected = partyMapper.saveUserRole(userRoleDto);
            if (rowAffected == 1) {
                log.info("Role of user was created correctly!!!");

            } else {
                // defensing programming
                throw new IllegalStateException("Error row affected more than 1 when user role was created!!!");
            }
        } catch (Exception e) {
            log.error("Error while created role user!!!", e);
            //
            throw new DatabaseException(e.getMessage(), e);
        }
        return userRoleDto;
    }

    @Override
    @Transactional
    public PartyDto saveUserWithRoles(PartyDto partyDto) {
        return Optional.ofNullable(partyDto)
                .map(this::saveUserLogin)
                .map(this::saveParty)
                .map(party -> {
                    List<UserRoleDto> userRoleDtos =
                            Optional.of(party)
                                    .map(PartyDto::getUserRolesDtos)
                                    .stream()
                                    .flatMap(Collection::stream)
                                    .map(this::saveUserRole)
                                    .collect(Collectors.toList());
                    log.info("[{}] Roles was associated to user [{}]", userRoleDtos.size(), party.getPartyName());

                    return party;
                })
                // defensing programming
                .orElseThrow(() -> new DatabaseException("User wasn't save correctly in the database!!!"));
    }

    @Override
    public PartyDto findPartyByUserLoginId(String userLoginId) {
        PartyDto partyDto;
        try {
            partyDto = partyMapper.findPartyByUserLoginId(userLoginId);

            if (Objects.nonNull(partyDto)) {
                List<UserRoleDto> userRolesDtos = findRoleByUserLoginId(userLoginId);
                partyDto.setUserRolesDtos(userRolesDtos);
                //
                log.info("The party by user login was found!!!");
            } else {
                log.info("The party by user login wasn't found!!!");
                //
                throw new UserNotFoundException("The party by user login wasn't found!!!");
            }
        } catch (Exception e) {
            log.error("Error while find party by user login!!!", e);
            //
            throw new UserNotFoundException(e.getMessage(), e);
        }
        return partyDto;
    }

    @Override
    public List<UserRoleDto> findRoleByUserLoginId(String userLoginId) {
        List<UserRoleDto> userRoleDtos;
        try {
            userRoleDtos = partyMapper.findRoleByUserLoginId(userLoginId);
            if (Objects.nonNull(userRoleDtos) && !userRoleDtos.isEmpty()) {
                log.info("The roles by user login was found!!!");
            } else {
                log.info("The roles by user login wasn't found!!!");

                userRoleDtos = List.of();
            }

        } catch (Exception e) {
            log.error("Error while find roles by user login!!!", e);
            //
            throw new DatabaseException(e.getMessage(), e);
        }
        return userRoleDtos;
    }
}
