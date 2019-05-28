package com.itacademy.service.service;

import com.itacademy.database.dao.PersonDao;
import com.itacademy.database.entity.Person;
import com.itacademy.service.dto.ViewPersonFullInfoDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonService {
    private static final PersonService PERSON_SERVICE = new PersonService();

    public List<ViewPersonFullInfoDto> findAll() {
        return PersonDao.getPersonDao().findAll().stream()
                .map(it -> new ViewPersonFullInfoDto(it.getId(), it.getAvatar(), it.getLogin(), it.getIdentification(), it.getAge(),
                        it.getMail(), it.getPassword(), it.getPersonRole().getNameOfRole()))
                .collect(Collectors.toList());
    }

    public List<ViewPersonFullInfoDto> findById(Long id) {
        return PersonDao.getPersonDao().findById(id).stream()
                .map(it -> new ViewPersonFullInfoDto(it.getId(), it.getAvatar(), it.getLogin(), it.getIdentification(), it.getAge(),
                        it.getMail(), it.getPassword(), it.getPersonRole().getNameOfRole()))
                .collect(Collectors.toList());
    }

    public Query delete(Person person) { /*переписать по 1-2 полям определённым*/
        return PersonDao.getPersonDao().delete(person);
    }

    public static PersonService getPersonService() {
        return PERSON_SERVICE;
    }
}