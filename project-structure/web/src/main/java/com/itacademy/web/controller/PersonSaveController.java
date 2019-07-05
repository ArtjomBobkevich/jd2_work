package com.itacademy.web.controller;

import com.itacademy.database.entity.Identification;
import com.itacademy.database.entity.PersonRole;
import com.itacademy.service.dto.CreateNewPersonDto;
import com.itacademy.service.dto.FileDto;
import com.itacademy.service.service.PersonService;
import com.itacademy.service.service.RoleService;
import com.itacademy.service.util.UrlPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
@RequestMapping(UrlPath.PERSON_SAVE)
public class PersonSaveController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PersonService personService;

    private String fileName;

    @ModelAttribute()
    public void setPersonRole(Model model) {
        model.addAttribute("personRole", roleService.findById(2L));
    }


    public void uploadFile(File file) {

//        String name = null;
//
//        if (!file.isEmpty()) {
//            try {
//                byte[] bytes = file.getBytes();
//
//                name = file.getOriginalFilename();
//                fileName = name;
//                String rootPath = "d:/1/";
//                File dir = new File(rootPath + File.separator + "loadFiles");
//
//                File uploadedFile = new File(dir.getAbsolutePath() + File.separator + name);
//
//                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
//                stream.write(bytes);
//                stream.flush();
//                stream.close();
//
//
//                return "You successfully uploaded file=" + name;
//
//            } catch (Exception e) {
//                return "You failed to upload " + name + " => " + e.getMessage();
//            }
//        } else {
//            return "You failed to upload " + name + " because the file was empty.";
//        }
//        Path writePath = Paths.get("resources", "Task2New.java");
//        try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new BufferedOutputStream(file))) {
//            bufferedWriter.write();
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
    }

    @GetMapping
    public String getPage() {
        return "person-save";
    }

    @PostMapping
    public String savePerson(FileDto file2, MultipartFile file, CreateNewPersonDto createNewGenreDto, Identification identification, PersonRole personRole) {

        file = (MultipartFile) file2.getFile();

//        uploadFile(file);
        createNewGenreDto.setAvatar("d:/1/" + file2.getFile().getName());
        createNewGenreDto.setPersonRole(personRole);
        createNewGenreDto.setIdentification(identification);
        createNewGenreDto.setPassword(new BCryptPasswordEncoder().encode(createNewGenreDto.getPassword()));

        Long aLong = personService.savePerson(createNewGenreDto);
        return "redirect:/person-info?id=" + aLong;
    }
}

//        String rootPath = System.getProperty("user.dir");
//        File file = new File(StringUtils.join(rootPath, "/any/path/from/your/project/root/directory/" , "test.xml"));
////Below commented line is what you wish to do. But I recommend not to do so.
////File file = new File(StringUtils.join(rootPath, "/out/resources/file/" , "test.xml"));
//        file.createNewFile();
//
//        System.out.println(file.getFile().);
//        FileSystemResource fileSystemResource = new FileSystemResource(file.getFile(),"sdfsdf")
//
//
//        FileSystemResource()
//
//        Resource resource = resourceLoader.getResource(file.getFile());