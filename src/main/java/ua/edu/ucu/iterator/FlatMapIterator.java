package ua.edu.ucu.iterator;

import java.util.Iterator;

import ua.edu.ucu.function.IntToIntStreamFunction;

public class FlatMapIterator implements Iterator<Integer> {
    private Iterator<Integer> parentIterator;
    private final IntToIntStreamFunction func;
    private int[] lastStream;
    private int position;

    public FlatMapIterator(Iterator<Integer> iterator, IntToIntStreamFunction func) {
        this.func = func;
        parentIterator = iterator;
    }

    @Override
    public Integer next() {
        updateLastStream();
        if (lastStream == null) return null;
        return lastStream[position++];
    }

    @Override
    public boolean hasNext() {
        return parentIterator.hasNext() || isLastStream();
    }

    private void updateLastStream() {
        if (!isLastStream())
        {
            if (!hasNext()) lastStream = null;
            lastStream = func.applyAsIntStream(parentIterator.next()).toArray();
            position = 0;
        }
    }

    private boolean isLastStream() {
        return lastStream != null && position < lastStream.length;
    }
}