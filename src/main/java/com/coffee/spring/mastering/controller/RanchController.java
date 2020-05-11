package com.coffee.spring.mastering.controller;

import com.coffee.spring.mastering.domain.Ranch;
import com.coffee.spring.mastering.service.RanchService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{name}")
    public Ranch getRanchByUserName(@PathVariable String name) {
        return ranchService.getRanchByUser(name);
    }

}
