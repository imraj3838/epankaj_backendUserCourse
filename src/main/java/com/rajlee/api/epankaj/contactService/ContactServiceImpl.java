package com.rajlee.api.epankaj.contactService;

import com.rajlee.api.epankaj.dtos.ContactDTO;
import com.rajlee.api.epankaj.models.Contact;
import com.rajlee.api.epankaj.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactRepository contactRepository;


    @Override
    public String addMesssage(ContactDTO contactDTO) {
        Contact contact = new Contact(contactDTO.getName(),contactDTO.getEmail(),contactDTO.getMessage());
        contactRepository.save(contact);
        return "Success";
    }
}
