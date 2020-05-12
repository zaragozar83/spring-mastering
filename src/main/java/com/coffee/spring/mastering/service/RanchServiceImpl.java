package com.coffee.spring.mastering.service;

import com.coffee.spring.mastering.domain.Ranch;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RanchServiceImpl implements RanchService{

    private List<Ranch> ranches = new ArrayList<>();

    public RanchServiceImpl() {
        ranches.add(Ranch.builder()
                        .id(1)
                        .active(true)
                        .name("Ricardo")
                        .city("Pittsburgh")
                        .build());
        ranches.add(Ranch.builder()
                        .id(2)
                        .active(true)
                        .name("Mullika")
                        .city("Bangkok")
                        .build());
        ranches.add(Ranch.builder()
                        .id(3)
                        .active(true)
                        .name("Teresa")
                        .city("Pachuca")
                        .build());
    }

    @Override
    public List<Ranch> retrieveRanches(String name) {
        return ranches;
    }

    @Override
    public Ranch addRanch(String name, String city) {

        Ranch ranch = Ranch.builder()
                .id(ranches.size() + 1)
                .active(true)
                .city(city)
                .name(name)
                .build();

        System.out.println(ranch.toString());
        ranches.add(ranch);

        return ranch;
    }

    @Override
    public Ranch getRanchByUser(String name) {
        return ranches.stream()
                .filter(ranch -> ranch.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("There are no ranch for " + name));
    }

    @Override
    public Ranch getRanchById(int id) {
        return ranches.stream()
                .filter(r -> r.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("There is no ranch Id for " + id));
    }


}
