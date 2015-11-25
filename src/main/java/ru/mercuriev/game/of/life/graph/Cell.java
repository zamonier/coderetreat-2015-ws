package ru.mercuriev.game.of.life.graph;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * works only with cells,
 * do not know anything about arrays
 *
 * @author paul
 */
final class Cell {

    private Integer isAlive = -1;
    private Cell left = null;
    private Cell top = null;
    private Cell right = null;
    private Cell bottom = null;

    // TODO in the aims of debugging create private String tag for the cell ?

    public void setBottom(Cell bottom) {
        this.bottom = bottom;
    }

    public void setIsAlive(Integer isAlive) {
        this.isAlive = isAlive;
    }

    public void setLeft(Cell left) {
        this.left = left;
    }

    public void setRight(Cell right) {
        this.right = right;
    }

    public void setTop(Cell top) {
        this.top = top;
    }

    /**
     * initializes current generation of the world
     */
    static Cell newInstance(Integer[][] inputData) {

        Objects.nonNull(inputData);

        // TODO this will not work of course :) but idea was good
        Arrays.stream(inputData).forEach(
                integers -> {
                    Cell leftCell = Arrays.stream(integers)
                                          .collect(new Collector<Integer, CellHolder, Cell>() {
                                              @Override
                                              public Supplier<CellHolder> supplier() {
                                                  return CellHolder::new;
                                              }

                                              @Override
                                              public BiConsumer<CellHolder, Integer> accumulator() {
                                                  return (line, integer) -> {
                                                      Cell next = new Cell();
                                                      next.left = line.current;
                                                      line.current.isAlive = integer;
                                                      line.current.right = next;
                                                      line.current = next;
                                                  };
                                              }

                                              // TODO combiner should glue part of the line, but not two lines
                                              @Override
                                              public BinaryOperator<CellHolder> combiner() {

                                                  // this code should be moved to the outer stream processing
                                                  return (top, bottom) -> {

                                                      while (top.current.left != null)
                                                          top.current = top.current.left;

                                                      while (bottom.current.left != null)
                                                          bottom.current = bottom.current.left;


                                                      while (top.current.right != null && bottom.current.right != null) {

                                                          // glue the top and bottom cell in one column
                                                          top.current.bottom = bottom.current;
                                                          bottom.current.top = top.current;

                                                          // move to the next cell in line
                                                          top.current = top.current.right;
                                                          bottom.current = bottom.current.right;

                                                      }

                                                      return bottom;


                                                  };
                                              }

                                              @Override
                                              public Function<CellHolder, Cell> finisher() {
                                                  return cellHolder -> {

                                                      // rewind to the most left
                                                      while (cellHolder.current.left != null) {
                                                          cellHolder.current = cellHolder.current.left;
                                                      }

                                                      // rewind to the most top
                                                      while (cellHolder.current.top != null) {
                                                          cellHolder.current = cellHolder.current.top;
                                                      }

                                                      // get the left top cell
                                                      return cellHolder.current;

                                                  };
                                              }

                                              @Override
                                              public Set<Characteristics> characteristics() {
                                                  return null;
                                              }
                                          });
                                                  
                                                  
                                                  
                }
        );


       // Cell leftTop = new Cell(isAlive,null,null,null,null);

        // 1. produce FIRST Cell linked by Cell1.right = Cell2.left with length "lineLength"
        // 2. produce NEXT Cell linked by Cell1.right = Cell2.left with length "lineLength"
        // 3. merge FIRST.bottom and NEXT.up cells,
        // 4. repeat (lineLength - 2) times
        return null;
    }

    /**
     * produces the next generation of World
     *
     * @return left top cell pointer
     */
    Cell nextGeneration() {
        // 1. for each cell in world line by line count neighbohoods and decide will it be alive
        // 2. put values it to the IntStream
        // 3. call newInstance
        return null;
    }

}

