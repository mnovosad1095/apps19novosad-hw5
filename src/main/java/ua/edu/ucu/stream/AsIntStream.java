package ua.edu.ucu.stream;

import java.util.ArrayList;
import java.util.Iterator;

import ua.edu.ucu.function.*;
import ua.edu.ucu.iterator.*;


public class AsIntStream implements IntStream {
    private Iterator<Integer> iterator;
    private boolean isClosed;

    private AsIntStream(Iterator<Integer> iterator) {
        this.iterator = iterator;
        isClosed = false;
    }

    public static IntStream of(int... values) {
        return new AsIntStream(new StreamIterator(values));
    }
    

    @Override
    public Double average() {
        checkIsClosed();
        
        double sum = 0;
        int counter = 0;

        while (iterator.hasNext()) {
            sum += iterator.next();
            counter++;
        }

        return  sum / counter;
    }

    @Override
    public Integer max() {
        return getMinMax(false);
    }

    @Override
    public Integer min() {
        return getMinMax(true);
    }

    @Override
    public long count() {
        return reduce(0, (sum, x) -> sum += 1);
    }

    @Override
    public Integer sum() {
        return reduce(0, (sum, x) -> sum += x);
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        checkIsClosed();

        return new AsIntStream(new FilterIterator(iterator, predicate));
    }

    @Override
        
    public void forEach(IntConsumer action) {
        checkIsClosed();
        
        while (iterator.hasNext()) {
            action.accept(iterator.next());
        }
        
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        checkIsClosed();

        return new AsIntStream(new MapIterator(iterator, mapper));
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        checkIsClosed();

        return new AsIntStream(new FlatMapIterator(iterator, func));
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        checkIsClosed();
        
        while (iterator.hasNext()) {
            identity = op.apply(identity, iterator.next());
        }

        return identity;
    }

    @Override
    public int[] toArray() {
        checkIsClosed();

        ArrayList<Integer> temp = new ArrayList<Integer>();
        while (iterator.hasNext()) {
            temp.add(iterator.next());
        }
        int[] items = new int[temp.size()];
        for (int i = 0; i < items.length; i++) {
            items[i] = (int) temp.get(i);
        }
        return items;
    }

    private Integer getMinMax(boolean min) {
        checkIsClosed();

        if (!iterator.hasNext()) {
            return null;
        }
        
        Integer value = iterator.next();

        while (iterator.hasNext()) {
            Integer nextVal = iterator.next();
            if (nextVal < value == min) {
                value = nextVal;
            }
        }

        return value;
    }

    private void checkIsClosed() {
        if (isClosed) {
            throw new IllegalStateException("Stream was already operated upon or closed");
        }
        isClosed = true;
    }

}
