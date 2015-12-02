package ru.mercuriev.game.of.life.no.condition.worlds;

import ru.mercuriev.game.of.life.no.condition.cells.BorderCell;
import ru.mercuriev.game.of.life.no.condition.cells.Cell;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class WrappedWorldBuilder {

    private World world;

    public WrappedWorldBuilder(World world) {
        this.world = world;
    }

    WrappedWorld build() {
        WrappedWorld wrappedWorld = new WrappedWorld();

        wrappedWorld.cells.add(borderRow());
        wrappedWorld.cells.addAll(world.cells.stream().map(this::wrappedRow).collect(Collectors.toList()));
        wrappedWorld.cells.add(borderRow());

        return wrappedWorld;
    }

    private List<Cell> borderRow() {
        return Stream.generate(BorderCell::new).limit(world.cells.size() + WrappedWorld.BORDER_OFFSET * 2).collect(Collectors.toList());
    }

    private List<Cell> wrappedRow(List<Cell> row) {
        LinkedList<Cell> wrappedRow = new LinkedList<>(row);
        wrappedRow.addFirst(new BorderCell());
        wrappedRow.addLast(new BorderCell());
        return wrappedRow;
    }

}
