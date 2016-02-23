package ru.mercuriev.game.of.life.groovy

import ru.mercuriev.Fixture
import spock.lang.Specification


class GroovyEngineSpec extends Specification implements Fixture {

    def "engine test"() {

        given:
        def engine = new GroovyEngine();
        def size = GENERATION.length

        when:
        def actualNextGeneration = engine.next(GENERATION)

        then:
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                actualNextGeneration[i][j] == NEXT_GENERATION[i][j]
            }
        }

    }

}
