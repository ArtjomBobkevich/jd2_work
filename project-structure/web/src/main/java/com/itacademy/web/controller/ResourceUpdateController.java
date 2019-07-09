package com.itacademy.web.controller;

import com.itacademy.database.entity.BlockResource;
import com.itacademy.database.entity.Person;
import com.itacademy.service.dto.CategoryDtoByUpdate;
import com.itacademy.service.dto.FileDto;
import com.itacademy.service.service.CategoryService;
import com.itacademy.service.service.PersonService;
import com.itacademy.service.service.ResourceService;
import com.itacademy.service.util.UrlPath;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.CREATE;

@Controller
@RequestMapping(UrlPath.RESOURCE_UPDATE)
public class ResourceUpdateController {

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private PersonService personService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String getPage(Model model) {
        model.addAttribute("category", categoryService.findAll());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        model.addAttribute("resources", resourceService.findResourceByLogin(login));

        return "resource-update";
    }

    @PostMapping
    @SneakyThrows
    public String updateResource(FileDto fileDto, BlockResource blockResource, CategoryDtoByUpdate categoryDtoByUpdate) {
        String name = fileDto.getImage().getName();
        File file = new File("d:/" + name);
        byte[] bytes;
        bytes = FileUtils.readFileToByteArray(file);
        Path writePath = Paths.get("D:/jd2_work/project-structure/web/src/main/webapp/upload", name);
        try (OutputStream output = new BufferedOutputStream(Files.newOutputStream(writePath, CREATE))) {
            output.write(bytes);
        }
        blockResource.setFoto(name);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        blockResource.setPerson(personService.findByLogin(login)
        );

        blockResource.setCategory(categoryService.findById(categoryDtoByUpdate.getCategoryId()));
        Person person = personService.findByLogin(login);
        blockResource.setBlock("NO");
        if (blockResource.getPerson().getLogin().equals(person.getLogin())) {
            resourceService.update(blockResource);
            return "redirect:/resource";
        }
        return "redirect:/resource-info?id=" + blockResource.getId();
    }
}
