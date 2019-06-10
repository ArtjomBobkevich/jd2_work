package com.itacademy.service.service;

import com.itacademy.database.dao.PersonDao;
import com.itacademy.database.entity.Person;
import com.itacademy.service.dto.CreateNewPersonDto;
import com.itacademy.service.dto.ViewPersonFullInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional(readOnly = true)
public class PersonService {

    private final PersonDao personDao;

    public List<ViewPersonFullInfoDto> findAll() {
        return personDao.getAll().stream()
                .map(it -> new ViewPersonFullInfoDto(it.getId(), it.getAvatar(), it.getLogin(), it.getIdentification(), it.getAge(),
                        it.getMail(), it.getPassword(), it.getPersonRole().getNameOfRole()))
                .collect(Collectors.toList());
    }

    public ViewPersonFullInfoDto findById(Long id) {
        return personDao.get(id).map(it -> new ViewPersonFullInfoDto(it.getAvatar(), it.getLogin(),
                it.getIdentification(), it.getAge(), it.getMail(), it.getPassword(), it.getPersonRole().getNameOfRole()))
                .orElse(null);
    }

    public Person findByIdEntity(Long id) {
        return personDao.get(id).orElse(null);
    }

    @Transactional
    public Long savePerson(CreateNewPersonDto viewPersonFullInfoDto) {
        return personDao.save(Person.builder()
                .avatar(viewPersonFullInfoDto.getAvatar())
                .login(viewPersonFullInfoDto.getLogin())
                .identification(viewPersonFullInfoDto.getIdentification())
                .age(viewPersonFullInfoDto.getAge())
                .mail(viewPersonFullInfoDto.getMail())
                .password(viewPersonFullInfoDto.getMail())
                .personRole(viewPersonFullInfoDto.getPersonRole())
                .build());
    }

    @Transactional
    public void delete(Person person) {
        personDao.delete(person);
    }

    @Transactional
    public void update(Person person) {
        personDao.update(person);
    }
}