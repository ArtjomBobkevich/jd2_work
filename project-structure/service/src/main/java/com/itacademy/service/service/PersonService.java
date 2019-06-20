package com.itacademy.service.service;

import com.itacademy.database.dao.PersonDao;
import com.itacademy.database.dao.ResourceDao;
import com.itacademy.database.entity.Person;
import com.itacademy.service.dto.CreateNewPersonDto;
import com.itacademy.service.dto.CreateResourceDto;
import com.itacademy.service.dto.ResourceFullDto;
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
    private final ResourceDao resourceDao;


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

    public Person findByLogin(String login) {
        return personDao.findByName(login);
    }

    @Transactional
    public Long savePerson(CreateNewPersonDto viewPersonFullInfoDto) {
        return personDao.save(Person.builder()
                .avatar(viewPersonFullInfoDto.getAvatar())
                .login(viewPersonFullInfoDto.getLogin())
                .identification(viewPersonFullInfoDto.getIdentification())
                .age(viewPersonFullInfoDto.getAge())
                .mail(viewPersonFullInfoDto.getMail())
                .password(viewPersonFullInfoDto.getPassword())
                .personRole(viewPersonFullInfoDto.getPersonRole())
                .build());
    }

    @Transactional
    public void delete(Person person) {
        personDao.delete(person);
    }

    @Transactional
    public void update(CreateNewPersonDto viewPersonFullInfoDto) {
        personDao.update(Person.builder()
                .id(viewPersonFullInfoDto.getId())
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
    public void addResourceAtBasket(CreateNewPersonDto personDto, CreateResourceDto createResourceDto) {

        personDao.addResource(personDao.get(personDto.getId()).orElse(null),
                resourceDao.get(createResourceDto.getId()).orElse(null));
    }

    @Transactional
    public void deleteResourceAtBasket(CreateNewPersonDto personDto, CreateResourceDto createResourceDto) {

        personDao.deleteResourceAtBasket(personDao.get(personDto.getId()).orElse(null),
                resourceDao.get(createResourceDto.getId()).orElse(null));
    }

    public List<ResourceFullDto> allResourcesAtBasket (String login) {

        return personDao.allResourcesAtBasket(personDao.findByName(login)).stream()
                .map(it->new ResourceFullDto(it.getId(), it.getResourceName(), it.getFoto(), it.getCategory().getCategoryName(),
                        it.getPerson().getLogin(), it.getPrice(), it.getText())).collect(Collectors.toList());
    }
}