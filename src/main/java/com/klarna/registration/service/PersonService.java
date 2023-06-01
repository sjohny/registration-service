package com.klarna.registration.service;

import com.klarna.registration.domain.Person;
import com.klarna.registration.exception.PlayerServiceException;

public interface PersonService {
    void save(Person person) throws PlayerServiceException;

    Person getPerson(String ssn) throws PlayerServiceException;

    String getOldestChild(String ssn) throws PlayerServiceException;
}
