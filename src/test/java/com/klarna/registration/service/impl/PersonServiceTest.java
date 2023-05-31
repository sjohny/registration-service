package com.klarna.registration.service.impl;

import com.klarna.registration.domain.Person;
import com.klarna.registration.exception.PlayerServiceException;
import com.klarna.registration.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PersonServiceTest {

    private PersonService personService;

    @BeforeEach
    public void setUp() {
        personService = new PersonServiceImpl();
    }

    @Test
    public void testSaveAndGetPerson() {
        Map<String, Integer> children = new HashMap<>();
        children.put("John", 5);
        Person person = new Person("123456", "Test", "Mary", children);
        personService.save(person);

        Person retrievedPerson = personService.getPerson("123456");
        assertEquals(person, retrievedPerson);
        assertTrue(retrievedPerson.getSpouseName().isPresent());
        assertEquals("Mary", retrievedPerson.getSpouseName().get());
    }

    @Test
    public void testSaveAndGetPersonWithoutSpouse() {
        Map<String, Integer> children = new HashMap<>();
        children.put("John", 5);
        Person person = new Person("123456", "Test", null, children);
        personService.save(person);

        Person retrievedPerson = personService.getPerson("123456");
        assertEquals(person, retrievedPerson);
        assertFalse(retrievedPerson.getSpouseName().isPresent());
    }

    @Test
    public void testGetOldestChild() {
        Map<String, Integer> children = new HashMap<>();
        children.put("John", 5);
        children.put("Jane", 10);
        Person person = new Person("123456", "Test", "Mary", children);
        personService.save(person);

        String oldestChild = personService.getOldestChild("123456");
        assertEquals("Jane", oldestChild);
    }

    @Test
    public void testGetOldestChildNoChildren() {
        Person person = new Person("123456", "Test", "Mary", Collections.emptyMap());
        personService.save(person);

        String oldestChild = personService.getOldestChild("123456");
        assertNull(oldestChild);
    }

    @Test
    public void testPersonNotFoundException() {
        Exception exception = assertThrows(PlayerServiceException.class, () -> personService.getPerson("123456"));
        assertEquals("Person with ssn: 123456 does not exist.", exception.getMessage());
    }

    @Test
    public void testDuplicateSSNException() {
        Person person1 = new Person("123456", "Test1", "Mary", new HashMap<>());
        personService.save(person1);
        Person person2 = new Person("123456", "Test2","Mary", new HashMap<>());

        Exception exception = assertThrows(PlayerServiceException.class, () -> personService.save(person2));
        assertEquals("Person with SSN: 123456 already exists.", exception.getMessage());
    }

    @Test
    public void testSavePersonWithNullName() {
        Map<String, Integer> children = new HashMap<>();
        children.put("John", 5);
        Person person = new Person("123456", null, "Spouse", children);

        assertThrows(IllegalArgumentException.class, () -> personService.save(person));
    }

    @Test
    public void testSavePersonWithNullSsn() {
        Map<String, Integer> children = new HashMap<>();
        children.put("John", 5);
        Person person = new Person(null, "Test", "Spouse", children);

        assertThrows(IllegalArgumentException.class, () -> personService.save(person));
    }

    @Test
    public void testSavePersonWithNullSsnAndName() {
        Map<String, Integer> children = new HashMap<>();
        children.put("John", 5);
        Person person = new Person(null, null, "Spouse", children);

        assertThrows(IllegalArgumentException.class, () -> personService.save(person));
    }

}
