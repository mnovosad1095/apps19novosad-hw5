package ua.edu.ucu.iterator;

import java.util.Iterator;

public class StreamIterator implements Iterator<Integer> {
    private int[] items;
    private int currentPosition;

    public StreamIterator(int[] collection) {
        items = collection.clone();
    }
    
    @Override
    public Integer next() {
        if (!hasNext()) {
            return null;
        }
        
        return items[currentPosition++];
    }

    @Override
    public boolean hasNext() {
        return currentPosition < items.length;
    }
}