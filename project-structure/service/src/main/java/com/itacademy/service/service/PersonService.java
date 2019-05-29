package com.itacademy.service.service;

import com.itacademy.database.entity.Person;
import com.itacademy.service.dto.ViewPersonFullInfoDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.itacademy.database.dao.PersonDao.getPersonDao;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonService {
    private static final PersonService PERSON_SERVICE = new PersonService();

    public List<ViewPersonFullInfoDto> findAll() {
        return getPersonDao().getAll().stream()
                .map(it -> new ViewPersonFullInfoDto(it.getId(), it.getAvatar(), it.getLogin(), it.getIdentification(), it.getAge(),
                        it.getMail(), it.getPassword(), it.getPersonRole().getNameOfRole()))
                .collect(Collectors.toList());
    }

    public Optional<ViewPersonFullInfoDto> findById(Long id) {
//        return getPersonDao().get(id).map(it -> new ViewPersonFullInfoDto(it.getId(), it.getAvatar(), it.getLogin(),
//                it.getIdentification(), it.getAge(), it.getMail(), it.getPassword(), it.getPersonRole().getNameOfRole()));
        return getPersonDao().get(id).map(it-> ViewPersonFullInfoDto.builder()
                .avatar(it.getAvatar())
                .login(it.getLogin())
                .identification(it.getIdentification())
                .age(it.getAge())
                .mail(it.getMail())
                .password(it.getPassword())
                .personRole(it.getPersonRole().getNameOfRole())
                .build());
    }

    public void delete(Person person) {
        getPersonDao().delete(person);
    }

    public static PersonService getPersonService() {
        return PERSON_SERVICE;
    }
}