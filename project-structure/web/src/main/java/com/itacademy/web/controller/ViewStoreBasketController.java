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
@RequestMapping(UrlPath.VIEW_STORE_BASKET)
public class ViewStoreBasketController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public String getPage (Model model, @RequestParam("login") String login) { /*как то передать логин из сессии*/
        model.addAttribute("resources", personService.allResourcesAtBasket(login));
        return "view-store-basket";
    }
}
