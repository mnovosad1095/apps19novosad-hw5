package ua.edu.ucu.iterator;

import java.util.Iterator;

import ua.edu.ucu.function.IntUnaryOperator;

public class MapIterator implements Iterator<Integer> {
    private Iterator<Integer> parentIterator;
    private final IntUnaryOperator mapper;

    public MapIterator(Iterator<Integer> iterator, IntUnaryOperator mapper) {
        this.mapper = mapper;
        parentIterator = iterator;
    }

    @Override
    public Integer next() {
        if (!parentIterator.hasNext()){
            return null;
        }
        return mapper.apply(parentIterator.next());
    }

    @Override
    public boolean hasNext() {
        return parentIterator.hasNext();
    }

}