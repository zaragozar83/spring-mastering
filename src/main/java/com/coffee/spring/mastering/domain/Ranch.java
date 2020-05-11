package com.coffee.spring.mastering.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ranch {

    private int id;
    private String name;
    private String city;
    private boolean active;

}
