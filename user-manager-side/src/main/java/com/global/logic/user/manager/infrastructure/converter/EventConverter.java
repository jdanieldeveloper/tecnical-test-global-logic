package com.global.logic.user.manager.infrastructure.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.global.logic.user.manager.application.event.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * Created by daniel.carvajal on 25-06-2018.
 */
@Slf4j
@Component
public class EventConverter implements Converter<Event, String> {

    @Override
    public String convert(Event event) {
        String to = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            to = objectMapper.writeValueAsString(event);

        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return to;
    }
}