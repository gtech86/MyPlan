package com.grabowski.MyPlan.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StarCreateResponse {
    private Integer id;
    private String name;
    private Long distance;
}
