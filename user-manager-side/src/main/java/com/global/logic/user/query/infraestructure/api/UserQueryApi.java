package com.global.logic.user.query.infraestructure.api;


import com.global.logic.user.command.infrastructure.api.model.CreateUserReq;
import com.global.logic.user.command.infrastructure.persistence.dao.PartyDao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor // DI by constructor
public class UserQueryApi {

    private PartyDao partyDao;

    @PostMapping(value = "/api/query/user/uuid")
    public ResponseEntity<?> getUserByUuid(@RequestBody CreateUserReq request) {
        return ResponseEntity.ok("Functionaro");
    }
}
