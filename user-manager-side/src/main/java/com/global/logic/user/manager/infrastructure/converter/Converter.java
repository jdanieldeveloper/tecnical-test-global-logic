package com.global.logic.user.manager.infrastructure.converter;

public interface Converter<From, To> {
	
	To convert(From from);
	
}
