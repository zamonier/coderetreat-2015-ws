package ru.mercuriev.game.of.life.groovy

import ru.mercuriev.Fixture
import spock.lang.Specification

class NeighboursCounterSpec extends Specification implements Fixture {

    def "neighbours counter test"() {

        given:
        def counter = new NeighboursCounter(generation);
        def size = neighbours.length

        expect:
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                counter.cellNeighborsAmount(i, j) == neighbours[i][j]
            }
        }

        where:
        generation      | neighbours
        GENERATION      | NEIGHBOURS
        NEXT_GENERATION | NEXT_NEIGHBOURS
    }

}
