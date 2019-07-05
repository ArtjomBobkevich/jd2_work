package com.itacademy.web.controller;

import com.itacademy.service.service.PersonService;
import com.itacademy.service.util.UrlPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(UrlPath.PERSON_INFO)
public class PersonInfoController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public String getPage(Model model, @RequestParam("id") Long id) {
        model.addAttribute("person", personService.findById(id));
//        File file = new File("D:/GitHub/jd2_work/project-structure/web/src/main/images/3.jpg");
//        String name = file.getName();
//        System.out.println(name);
//        byte [] bFile = new byte[(int) file.length()];
////        Path writePath = Paths.get("resources", "Task2New.java");
//        try(BufferedWriter bufferedWriter = new BufferedWriter(Writer)) {
//            fileOuputStream.write(bFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
//                new FileOutputStream("filename.txt"), "utf-8"))) {
//            writer.write("something");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(writePath))) {
//            bufferedWriter.write();
//            }
//        System.out.println(bFile.length);
//        model.addAttribute("file",file);
        return "person-info";
    }
}