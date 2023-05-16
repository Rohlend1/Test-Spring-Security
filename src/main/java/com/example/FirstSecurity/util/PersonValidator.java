package com.example.FirstSecurity.util;


import com.example.FirstSecurity.entities.Person;
import com.example.FirstSecurity.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PersonService personService;

    @Autowired
    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if(!personService.checkIfPersonExist(person.getUsername())){
            errors.rejectValue("username","","This username has already been used");
        }
    }
}
