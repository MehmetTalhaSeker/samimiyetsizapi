package com.samimiyetsiz.samimiyetsizapi.service;


import com.samimiyetsiz.samimiyetsizapi.entity.Authority;
import com.samimiyetsiz.samimiyetsizapi.entity.Memer;
import com.samimiyetsiz.samimiyetsizapi.exception.BadRequest;
import com.samimiyetsiz.samimiyetsizapi.repository.AuthorityRepository;
import com.samimiyetsiz.samimiyetsizapi.repository.MemerRepository;
import com.samimiyetsiz.samimiyetsizapi.security.AuthoritiesConstants;
import com.samimiyetsiz.samimiyetsizapi.security.SecurityUtils;
import com.samimiyetsiz.samimiyetsizapi.security.jwt.TokenProvider;
import com.samimiyetsiz.samimiyetsizapi.service.dto.RegisterDto;
import com.samimiyetsiz.samimiyetsizapi.util.RandomUtil;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class MemerService {

    private static final int PASSWORD_MIN_LENGTH = 6;
    private static final int PASSWORD_MAX_LENGTH = 34;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MemerRepository memerRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    TokenProvider tokenProvider;

    public Memer registerMemer(RegisterDto registerDto) throws RuntimeException {
        if (!checkPasswordLength(registerDto.getPassword())) {
            throw new BadRequest("password length not valid.[ 6 <= password <= 34 ]");
        }

        if (memerRepository.findOneByUsername(registerDto.getUsername()).isPresent()) {
            throw new BadRequest("username already used!");
        }
        if (new EmailValidator().isValid(registerDto.getEmail(), null)
                && memerRepository.findOneByEmail(registerDto.getEmail()).isPresent()) {
            throw new BadRequest("email already used!");
        }

        Memer memer = new Memer();
        memer.setUsername(registerDto.getUsername());
        memer.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        memer.setEmail(registerDto.getEmail().toLowerCase());
        // todo changed after email activation
        memer.setActivated(true);
        memer.setActivationKey(RandomUtil.generateActivationKey());

        Set<Authority> authorities = new HashSet<>();
        authorityRepository.findById(AuthoritiesConstants.USER).ifPresent(authorities::add);
        memer.setAuthorities(authorities);
        return memerRepository.save(memer);
    }

    public String login(String login, String password, boolean rememberMe) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new
                UsernamePasswordAuthenticationToken(login, password);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return tokenProvider.createToken(authentication, rememberMe);
    }


    private static boolean checkPasswordLength(String password) {
        return !StringUtils.isEmpty(password) &&
                password.length() >= PASSWORD_MIN_LENGTH &&
                password.length() <= PASSWORD_MAX_LENGTH;
    }

    public Memer createMemer(Memer memer) {
        memer.setPassword(passwordEncoder.encode(RandomUtil.generatePassword()));
        return memerRepository.save(memer);
    }

    @Transactional(readOnly = true)
    public Optional<Memer> getUserWithAuthorities() {
        return SecurityUtils.getCurrentUserLogin().flatMap(memerRepository::findOneWithAuthoritiesByUsername);
    }
}