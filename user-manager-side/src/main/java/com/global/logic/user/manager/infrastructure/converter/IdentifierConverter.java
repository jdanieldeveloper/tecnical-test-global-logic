package com.global.logic.user.manager.infrastructure.converter;

import com.global.logic.user.manager.domain.aggregate.user.Identifier;
import com.global.logic.user.manager.domain.aggregate.user.UserIdentifier;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class IdentifierConverter implements Converter<Long, Optional<Identifier>>{

	public Optional<Identifier> convert(Long id) {
		return Optional.of(new UserIdentifier(id));
	}	

}
