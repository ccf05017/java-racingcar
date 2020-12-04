package com.nextstep.racinggame.domain;

import com.nextstep.racinggame.domain.exceptions.InvalidNameException;

import java.util.Objects;

public class Name {
    private final String value;

    public Name(final String value) {
        validate(value);
        this.value = value;
    }

    private void validate(final String value) {
        if (value.length() > 5) {
            throw new InvalidNameException("이름은 최대 5글자까지만 가능합니다.");
        }
        if (value.isEmpty()) {
            throw new InvalidNameException("이름은 1글자 이상이어야 합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return Objects.equals(value, name.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Name{" +
                "value='" + value + '\'' +
                '}';
    }
}
