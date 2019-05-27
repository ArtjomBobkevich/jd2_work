package by.itacademy.service.service;

import by.itacademy.database.dao.PersonDao;
import by.itacademy.database.entity.Person;
import by.itacademy.service.dto.ViewPersonFullInfoDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonService {
    private static final PersonService PERSON_SERVICE = new PersonService();

    public List<ViewPersonFullInfoDto> findAll() {
        return PersonDao.getPersonDao().findAll().stream()
                .map(it -> new ViewPersonFullInfoDto(it.getId(),it.getAvatar(), it.getLogin(), it.getIdentification(), it.getAge(),
                        it.getMail(), it.getPassword(), it.getPersonRole().getNameOfRole()))
                .collect(Collectors.toList());
    }

    public List<ViewPersonFullInfoDto> findById(Long id) {
        return PersonDao.getPersonDao().findById(id).stream()
                .map(it -> new ViewPersonFullInfoDto(it.getId(),it.getAvatar(), it.getLogin(), it.getIdentification(), it.getAge(),
                it.getMail(), it.getPassword(), it.getPersonRole().getNameOfRole()))
                .collect(Collectors.toList());
    }

    public boolean delete (Person person){
        return PersonDao.getPersonDao().delete(person);
    }

    public static PersonService getPersonService() {
        return PERSON_SERVICE;
    }
}