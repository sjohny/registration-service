package com.klarna.registration.domain;

import java.util.Map;
import java.util.Optional;

public class Person {
    //Social Security Number
    private String ssn;
    private String name;
    private String spouseName;

    //To store name and age of the children
    private Map<String, Integer> children;

    public Person(String ssn, String name, String spouseName,  Map<String, Integer> children) {
        this.ssn = ssn;
        this.name = name;
        this.spouseName = spouseName;
        this.children = children;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Optional<String> getSpouseName() {
        return Optional.ofNullable(spouseName);
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    public Map<String, Integer> getChildren() {
        return children;
    }

    public void setChildren(Map<String, Integer> children) {
        this.children = children;
    }
}
