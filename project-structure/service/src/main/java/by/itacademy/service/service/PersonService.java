package by.itacademy.service.service;

import by.itacademy.database.dao.PersonDao;
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
                .map(it -> new ViewPersonFullInfoDto(it.getAvatar(), it.getLogin(), it.getFirstName(), it.getLastName(), it.getAge(),
                        it.getMail(), it.getPassword(), it.getPersonRole().getNameOfRole()))
                .collect(Collectors.toList());
    }

    public ViewPersonFullInfoDto findById(Long id) {
        return PersonDao.getPersonDao().findById(id)
                .map(it -> ViewPersonFullInfoDto.builder()
                        .avatar(it.getAvatar())
                        .login(it.getLogin())
                        .firstName(it.getFirstName())
                        .lastName(it.getLastName())
                        .age(it.getAge())
                        .mail(it.getMail())
                        .password(it.getPassword())
                        .personRole(it.getPersonRole().getNameOfRole())
                        .build())
                .orElse(null);
    }

    public static PersonService getPersonService() {
        return PERSON_SERVICE;
    }
}