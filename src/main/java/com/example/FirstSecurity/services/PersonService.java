package com.example.FirstSecurity.services;

import com.example.FirstSecurity.entities.Person;
import com.example.FirstSecurity.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public boolean checkIfPersonExist(String username){
        return personRepository.findByUsername(username).isEmpty();
    }

    @Transactional
    public void addPerson(Person person){
        System.out.println("Saving");
        personRepository.save(person);
    }
}
