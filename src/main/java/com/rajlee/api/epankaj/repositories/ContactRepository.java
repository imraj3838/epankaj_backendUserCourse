package com.rajlee.api.epankaj.repositories;

import com.rajlee.api.epankaj.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact , Long> {

}
