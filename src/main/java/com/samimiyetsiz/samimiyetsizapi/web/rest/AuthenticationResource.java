package com.samimiyetsiz.samimiyetsizapi.web.rest;


import com.samimiyetsiz.samimiyetsizapi.entity.Memer;
import com.samimiyetsiz.samimiyetsizapi.service.MemerService;
import com.samimiyetsiz.samimiyetsizapi.service.dto.LoginDto;
import com.samimiyetsiz.samimiyetsizapi.service.dto.RegisterDto;
import com.samimiyetsiz.samimiyetsizapi.web.rest.errors.AccountResourceException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import static com.samimiyetsiz.samimiyetsizapi.config.Constants.AUTHORIZATION_HEADER;


@RestController
@RequestMapping("api/auth")
public class AuthenticationResource {

    @Autowired
    MemerService memerService;

    @PostMapping("register")
    public ResponseEntity<Memer> registerMemer(@RequestBody RegisterDto registerDTO) throws URISyntaxException, RuntimeException {
        Memer memer = memerService.registerMemer(registerDTO);
        return ResponseEntity.created(new URI("v1/memers/" + memer.getId())).body(memer);
    }

    @PostMapping("/login")
    public ResponseEntity<JWTToken> loginMemer(@RequestBody LoginDto loginDTO) {
        String token = memerService.login(loginDTO.getLogin(), loginDTO.getPassword(), loginDTO.isRememberMe());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(AUTHORIZATION_HEADER, "Bearer " + token);
        return new ResponseEntity<>(new JWTToken(token), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<Memer> getMe() {
        return memerService.getUserWithAuthorities().
                map(memer -> ResponseEntity.ok().body(memer))
                .orElseThrow(() -> new AccountResourceException("User could not be found"));
    }


    @AllArgsConstructor
    static class JWTToken {

        @Getter
        @Setter
        private String token;
    }
}