package com.nextstep.racinggame.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static com.nextstep.racinggame.domain.CarsFixtures.ALL_NOT_MOVED_CARS;
import static com.nextstep.racinggame.domain.CarsFixtures.ALL_ONE_MOVED_CARS;
import static com.nextstep.racinggame.domain.GasStationFixtures.FOUR_FUEL_GAS_STATION;
import static com.nextstep.racinggame.domain.GasStationFixtures.THREE_FUEL_GAS_STATION;
import static org.assertj.core.api.Assertions.assertThat;

class CarsTest {
    @DisplayName("Names를 인자로 받아서 객체를 생성할 수 있다.")
    @Test
    void createTest() {
        Names names = new Names(
                Arrays.asList(new Name("test1"), new Name("test2"), new Name("test3")));
        Cars cars = Cars.of(names);

        assertThat(cars).isNotNull();
    }

    @DisplayName("속한 차량들을 한번에 움직일 수 있다.")
    @ParameterizedTest
    @MethodSource("moveAllTestResource")
    void moveAllTest(MovePolicy movePolicy, Cars expectedCars) {
        Names names = new Names(
                Arrays.asList(new Name("test1"), new Name("test2"), new Name("test3")));
        Cars cars = Cars.of(names);

        Cars movedCars = cars.move(movePolicy);

        assertThat(movedCars)
                .isEqualTo(expectedCars);
    }
    public static Stream<Arguments> moveAllTestResource() {
        return Stream.of(
                Arguments.of(FOUR_FUEL_GAS_STATION, ALL_ONE_MOVED_CARS),
                Arguments.of(THREE_FUEL_GAS_STATION, ALL_NOT_MOVED_CARS)
        );
    }

    @DisplayName("속한 차량들의 현재 주행거리를 알 수 있다.")
    @Test
    void calculateCurrentDistanceTest() {
        Names names = new Names(
                Arrays.asList(new Name("test1"), new Name("test2"), new Name("test3")));
        Cars cars = Cars.of(names);
        CurrentDistance expected = new CurrentDistance(Arrays.asList(1, 1, 1));

        Cars movedCars = cars.move(FOUR_FUEL_GAS_STATION);

        assertThat(movedCars.calculateCurrentDistance()).isEqualTo(expected);
    }
}
