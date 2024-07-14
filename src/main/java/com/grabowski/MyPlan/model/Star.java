package com.grabowski.MyPlan.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public class Star {
    private String name;
    private Long distance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Star star = (Star) o;
        return Objects.equals(name, star.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
