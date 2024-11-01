package com.global.logic.user.query.application.service;

import com.global.logic.user.command.infrastructure.dto.PartyDto;
import com.global.logic.user.command.infrastructure.dto.UserRoleDto;
import com.global.logic.user.command.infrastructure.persistence.dao.PartyDao;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private PartyDao partyDao;

    @Override
    public UserDetails loadUserByUsername(String userLoginId) throws UsernameNotFoundException {
        Try<PartyDto> partyDto = Try.of(() -> partyDao.findPartyByUserLoginId(userLoginId));

        if(partyDto.isSuccess()) {
            // Roles
            Set<SimpleGrantedAuthority> authorities = new HashSet<>();
                for(UserRoleDto roleDto : partyDto.get().getUserRolesDtos()){
                    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(String.format("ROLE_%s", roleDto.getRoleTypeId()));
                    authorities.add(authority);
                }
            return new User(partyDto.get().getUserLoginId(), partyDto.get().getCurrentPassword(), authorities);

        } else {
            throw new UsernameNotFoundException(partyDto.getCause().getMessage(), partyDto.getCause());
        }
    }
}
