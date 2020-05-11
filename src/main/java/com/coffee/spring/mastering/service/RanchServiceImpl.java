package com.coffee.spring.mastering.service;

import com.coffee.spring.mastering.domain.Ranch;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RanchServiceImpl implements RanchService{

    private static List<Ranch> ranches;

    static {
        ranches = Arrays.asList(
          Ranch.builder()
                .id(1)
                .active(true)
                .name("Ricardo")
                .city("Pittsburgh")
                .build(),
            Ranch.builder()
                    .id(2)
                    .active(true)
                    .name("Mullika")
                    .city("Bangkok")
                    .build(),
            Ranch.builder()
                    .id(3)
                    .active(true)
                    .name("Teresa")
                    .city("Pachuca")
                    .build()
        );
    }

    @Override
    public List<Ranch> retrieveRanches(String name) {
        return ranches;
    }

    @Override
    public void addRanch(String name, String city) {

        ranches.add(Ranch.builder()
                         .id((int) Math.random() * 10)
                         .active(true)
                         .city(city)
                         .name(name)
                         .build());
    }

    @Override
    public Ranch getRanchByUser(String name) {
        return ranches.stream()
                .filter(ranch -> ranch.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("There are no ranch for " + name));
    }
}
