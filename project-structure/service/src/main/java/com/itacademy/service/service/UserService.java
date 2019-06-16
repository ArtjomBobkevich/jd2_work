package com.itacademy.service.service;

import com.itacademy.database.dao.PersonDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements BasePersonService {

    @Autowired
    private PersonDao personDao;


    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return Optional.of(name)
                .map(personDao::findByName)
                .map(user -> User.builder()
                        .username(user.getLogin())
                        .password(user.getPassword())
                        .authorities(user.getPersonRole().getNameOfRole())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}