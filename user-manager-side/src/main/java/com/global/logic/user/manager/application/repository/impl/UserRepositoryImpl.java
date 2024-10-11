package com.global.logic.user.manager.application.repository.impl;

import com.global.logic.user.manager.domain.aggregate.user.Identifier;
import com.global.logic.user.manager.domain.aggregate.user.User;
import com.global.logic.user.manager.domain.aggregate.user.UserRepository;
import com.global.logic.user.manager.infrastructure.converter.FromUserConverter;
import com.global.logic.user.manager.infrastructure.converter.IdentifierConverter;
import com.global.logic.user.manager.infrastructure.dto.PartyDto;
import com.global.logic.user.manager.infrastructure.dto.UserRoleDto;
import com.global.logic.user.manager.infrastructure.persistence.dao.PartyDao;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private PartyDao partyDao;

    @Autowired
    IdentifierConverter identifierConverter;

    @Autowired
    private FromUserConverter fromUserConverter;


    @Override
    public Optional<Identifier> getUserId() {
        return identifierConverter.convert(partyDao.nexValueForIdentifier());
    }


    @Override
    public Optional<User> addUser(User user) {

        Optional<User> newUser = Optional.empty();
        //
        boolean isAdd;
        PartyDto partyDto = fromUserConverter.convert(user);

            isAdd = partyDao.saveUserLogin(partyDto);
            if (isAdd) {
                partyDao.saveParty(partyDto);
                // when save
                if(Objects.nonNull(partyDto.getUserRolesDtos()) && !partyDto.getUserRolesDtos().isEmpty()){
                    for(UserRoleDto role : partyDto.getUserRolesDtos()){
                        partyDao.saveUserRole(role);
                    }
                }
                newUser = Optional.of(user);
            }
        return newUser;
    }

    private User saveUser(User user) {

    }



}
