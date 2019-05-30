package com.itacademy.service.service;

import com.itacademy.database.entity.Person;
import com.itacademy.service.dto.CreateNewPersonDto;
import com.itacademy.service.dto.ViewPersonFullInfoDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static com.itacademy.database.dao.PersonDao.getPersonDao;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonService {
    private static final PersonService PERSON_SERVICE = new PersonService();

    public List<ViewPersonFullInfoDto> findAll() {
        return getPersonDao().getAll().stream()
                .map(it -> new ViewPersonFullInfoDto( it.getAvatar(), it.getLogin(), it.getIdentification(), it.getAge(),
                        it.getMail(), it.getPassword(), it.getPersonRole().getNameOfRole()))
                .collect(Collectors.toList());
    }

    public ViewPersonFullInfoDto findById(Long id) {
        return getPersonDao().get(id).map(it -> new ViewPersonFullInfoDto( it.getAvatar(), it.getLogin(),
                it.getIdentification(), it.getAge(), it.getMail(), it.getPassword(), it.getPersonRole().getNameOfRole()))
                .orElse(null);
    }

    public ViewPersonFullInfoDto savePerson(CreateNewPersonDto viewPersonFullInfoDto) {
        Person person = Person.builder()
//                .id(viewPersonFullInfoDto.getId())
                .avatar(viewPersonFullInfoDto.getAvatar())
                .login(viewPersonFullInfoDto.getLogin())
                .identification(viewPersonFullInfoDto.getIdentification())
                .age(viewPersonFullInfoDto.getAge())
                .mail(viewPersonFullInfoDto.getMail())
                .password(viewPersonFullInfoDto.getMail())
                .personRole(viewPersonFullInfoDto.getPersonRole())
                .build();
        return new ViewPersonFullInfoDto(person.getAvatar(),person.getLogin(),person.getIdentification(),
                person.getAge(),person.getMail(),person.getPassword(),person.getPersonRole().getNameOfRole());
    }

    public void delete(Person person) {
        getPersonDao().delete(person);
    }

    public static PersonService getPersonService() {
        return PERSON_SERVICE;
    }
}