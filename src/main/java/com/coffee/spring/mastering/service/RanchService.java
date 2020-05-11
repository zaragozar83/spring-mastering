package com.coffee.spring.mastering.service;

import com.coffee.spring.mastering.domain.Ranch;

import java.util.List;

public interface RanchService {

    public List<Ranch> retrieveRanches(String name);
    public void addRanch(String name, String city);
    public Ranch getRanchByUser(String name);
}
