package com.example.FirstSecurity.services;

import com.example.FirstSecurity.entities.Person;
import com.example.FirstSecurity.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PersonService {
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public PersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean checkIfPersonExist(String username){
        return personRepository.findByUsername(username).isEmpty();
    }

    @Transactional
    public void addPerson(Person person){
       person.setPassword(passwordEncoder.encode(person.getPassword()));
       person.setRole("ROLE_USER");
       personRepository.save(person);
    }
}
