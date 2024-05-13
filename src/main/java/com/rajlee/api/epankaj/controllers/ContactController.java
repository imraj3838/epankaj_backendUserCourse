package com.rajlee.api.epankaj.controllers;

import com.rajlee.api.epankaj.contactService.ContactService;
import com.rajlee.api.epankaj.dtos.ContactDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/epankaj/v.0/users")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/contact")
    public String addContact(@RequestBody ContactDTO contactDTO ){
        return contactService.addMesssage(contactDTO);
    }
}
