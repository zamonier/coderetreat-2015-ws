package ru.mercuriev.game.of.life.no.condition.worlds;

import ru.mercuriev.game.of.life.no.condition.cells.BorderCell;
import ru.mercuriev.game.of.life.no.condition.cells.Cell;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Eugene on 02.12.15.
 */
class WrappedWorldBuilder {

    private World world;

    public WrappedWorldBuilder(World world) {
        this.world = world;
    }

    protected WrappedWorld build() {
        WrappedWorld wrappedWorld = new WrappedWorld();

        wrappedWorld.cells.add(borderRow());
        wrappedWorld.cells.addAll(world.getRows().map(this::wrappedRow).collect(Collectors.toList()));
        wrappedWorld.cells.add(borderRow());

        return wrappedWorld;
    }

    private List<Cell> wrappedRow(List<Cell> row) {
        return Stream.concat(
                Stream.concat(
                        Stream.of(new BorderCell()),
                        row.stream()
                ),
                Stream.of(new BorderCell())).collect(Collectors.toList());
    }

    private List<Cell> borderRow() {
        return Stream.generate(BorderCell::new).limit(world.size() + 2 * WrappedWorld.BORDER_OFFSET).collect(Collectors.toList());
    }

}
