package com.coffee.spring.mastering.controller;

import com.coffee.spring.mastering.domain.Ranch;
import com.coffee.spring.mastering.service.RanchService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/ranches")
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class RanchController {

    private final RanchService ranchService;

    @GetMapping
    public List<Ranch> getRanches(@PathVariable String name) {
        return ranchService.retrieveRanches(name);
    }

    @GetMapping("/name/{name}")
    public Ranch getRanchByUserName(@PathVariable String name) {
        return ranchService.getRanchByUser(name);
    }

    @GetMapping("/{id}")
    public Ranch getRanchById(@PathVariable int id) {
        return ranchService.getRanchById(id);
    }

    @PostMapping
    ResponseEntity<?> addRanch(@RequestBody Ranch ranch) {

        Ranch ranchResponse = ranchService.addRanch(ranch.getName(), ranch.getCity());

        if(ranchResponse == null) {
            return ResponseEntity.noContent().build();
        }

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(ranchResponse.getId()).toUri();

        return ResponseEntity.created(uri).build();

    }

}
