package com.klarna.registration.service.impl;

import com.klarna.registration.domain.Person;
import com.klarna.registration.exception.PlayerServiceException;
import com.klarna.registration.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Assumptions:
 *
 * The social security number is unique for each person.
 * The social security number is always valid and not null when provided.
 * Children's ages are always valid and not null when provided.
 * The age of a child will never be updated, so the oldest child will remain the oldest.
 */
@Service
public class PersonServiceImpl implements PersonService {

    //Registry repository
    private Map<String, Person> personRegistry = new HashMap<>();

    @Override
    public void save(Person person) {
        if (person.getSsn() == null || person.getName() == null) {
            throw new IllegalArgumentException("SSN and Name must not be null.");
        }

        if(personRegistry.containsKey(person.getSsn())) {
            throw new PlayerServiceException("Person with SSN: " + person.getSsn() + " already exists.");
        }
        personRegistry.put(person.getSsn(), person);

    }

    @Override
    public Person getPerson(String ssn) {
        Person person = personRegistry.get(ssn);
        if (person == null) {
            throw new PlayerServiceException("Person with ssn: " + ssn + " does not exist.");
        }
        return personRegistry.get(ssn);
    }

    @Override
    public String getOldestChild(String ssn) {
        Person person = getPerson(ssn);
        if (person != null && person.getChildren() != null && !person.getChildren().isEmpty()) {
            return Collections.max(person.getChildren().entrySet(),
                            Map.Entry.comparingByValue())
                    .getKey();
        }
        return null;
    }
}
