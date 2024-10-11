package com.global.logic.user.manager.infrastructure.persistence.dao.impl;

import com.global.logic.user.manager.infrastructure.dto.PartyDto;
import com.global.logic.user.manager.infrastructure.dto.UserRoleDto;
import com.global.logic.user.manager.infrastructure.enums.UserStatusEnum;
import com.global.logic.user.manager.infrastructure.persistence.dao.PartyDao;
import com.global.logic.user.manager.infrastructure.persistence.mybatis.mapper.PartyMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author daniel.carvajal
 */
@Slf4j
@Component
public class PartyDaoImpl implements PartyDao {

    @Autowired
    private PartyMapper partyMapper;


    @Override
    public Long nexValueForIdentifier() {
        long partyId = 0;
        try {
            partyId = partyMapper.nexValueForIdentifier();
            if (partyId > 0) {
                log.info("PartyId identifier was created correctly!!!");
            } else {
                throw new IllegalStateException("Error partyId can be larger than of 0!!!");
            }
        } catch (Exception e) {
            log.error("Error while created partyId identifier!!!", e);
            throw e;
        }
        return partyId;
    }

    @Override
    public boolean saveParty(PartyDto partyDto) {
        int rowAffected;
        boolean isSaved = false;
        try {
            // default values
            partyDto.setCreatedDate(new Date());
            partyDto.setCreateByUserLogin(partyDto.getUserLoginId());
            partyDto.setStatusId(UserStatusEnum.ENABLED.getStatusId());
            // save party
            rowAffected = partyMapper.saveParty(partyDto);
            if (rowAffected == 1) {
                isSaved = true;
                //
                log.info("Party was created correctly!!!");
            } else {
                throw new IllegalStateException("Error row affected more than 1 when party was created!!!");
            }
        } catch (Exception e) {
            log.error("Error while created party!!!", e);
            throw e;
        }
        return isSaved;
    }

    @Override
    public boolean saveUserLogin(PartyDto partyDto) {
        int rowAffected;
        boolean isSaved = false;
        try {

            rowAffected = partyMapper.saveUserLogin(partyDto);
            if (rowAffected == 1) {
                isSaved = true;
                //
                log.info("User was created correctly!!!");
            } else {
                throw new IllegalStateException("Error row affected more than 1 when user was created!!!");
            }
        } catch (Exception e) {
            log.error("Error while created user!!!", e);
            throw e;
        }
        return isSaved;
    }

    @Override
    public boolean saveUserRole(UserRoleDto userRoleDto) {
        int rowAffected;
        boolean isSaved = false;
        try {

            rowAffected = partyMapper.saveUserRole(userRoleDto);
            if (rowAffected == 1) {
                isSaved = true;
                //
                log.info("Role of user was created correctly!!!");
            } else {
                throw new IllegalStateException("Error row affected more than 1 when user role was created!!!");
            }
        } catch (Exception e) {
            log.error("Error while created role user!!!", e);
            throw e;
        }
        return isSaved;
    }

    @Override
    public PartyDto findPartyByUserLoginId(String userLoginId) {
        PartyDto partyDto = null;
        try {
            partyDto = partyMapper.findPartyByUserLoginId(userLoginId);
            if (Objects.nonNull(partyDto)) {
                List<UserRoleDto> userRolesDtos = findRoleByUserLoginId(userLoginId);
                partyDto.setUserRolesDtos(userRolesDtos);
                //
                log.info("The party by user login was found!!!");
            } else {
                log.info("The party by user login wasn't found!!!");
            }
        } catch (Exception e) {
            log.error("Error while find party by user login!!!", e);
            throw e;

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
            }

        } catch (Exception e) {
            log.error("Error while find roles by user login!!!", e);
            throw e;
        }
        return userRoleDtos;
    }
}
