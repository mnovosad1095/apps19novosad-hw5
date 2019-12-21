package ua.edu.ucu.iterator;

import java.util.Iterator;

import ua.edu.ucu.function.IntPredicate;

public class FilterIterator implements Iterator<Integer>{
    private final Iterator<Integer> parentIterator;
    private final IntPredicate predicate;
    private Integer next;

    public FilterIterator(Iterator<Integer> parentIterator, IntPredicate predicate) {
        this.parentIterator = parentIterator;
        this.predicate = predicate;
    }


    @Override
    public Integer next() {
        Integer res = next;
        next = null;
        
        return res;
    }

    @Override
    public boolean hasNext() {
        createNext();
        return next != null;
    }

    private void createNext() {
        Integer itNext;
        
        if (next != null) { return; }

        while (parentIterator.hasNext()) {
            itNext = parentIterator.next();
            if (predicate.test(itNext)) {
                next = itNext;     
                break;
            }           
        }
    }
}
