package ua.edu.ucu;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.*;

import ua.edu.ucu.stream.*;

public class AsIntStreamTest {
    
    private IntStream intStream;

    @Before
    public void init() {
        int[] intArr = {-1, 0, 1, 2, 3};
        intStream = AsIntStream.of(intArr);
    }

    @Test
    public void testAverage() {
        double expRes = 1;
        double result = intStream.average();
        assertEquals(expRes, result, 0.01);
    }

    @Test
    public void testMax() {
        Integer expected = 3;
        Integer result = intStream.max();
        assertEquals(expected, result);
    }

    @Test
    public void testMin() {
        Integer expected = -1;
        Integer result = intStream.min();
        assertEquals(expected, result);
    }

    @Test
    public void testFlatMap() {
        StringBuilder str = new StringBuilder();
        IntStream newStream = intStream.flatMap(x -> AsIntStream.of(x - 1, x, x + 1));
        newStream.forEach(x -> str.append(x));
        String expected = "-2-10-101012123234";
        assertEquals(expected, str.toString());
    }

    @Test
    public void testCount() {
        long expected = 5;
        long result = intStream.count();
        assertEquals(expected, result);
    }

    @Test
    public void testFilter() {
        StringBuilder str = new StringBuilder();
        IntStream newStream = intStream.filter(x -> x > 0);
        newStream.forEach(x -> str.append(x));
        String expected = "123";
        assertEquals(expected, str.toString());
    }
    
    @Test
    public void testForEach() {
        StringBuilder str = new StringBuilder();
        intStream.forEach(x -> str.append(x));
        assertEquals("-10123", str.toString());
    }

    @Test
    public void testMap() {
        IntStream newStream = intStream.map(x -> x*2);
        StringBuilder str = new StringBuilder();
        newStream.forEach(x -> str.append(x));
        assertEquals("-20246", str.toString());
    }

    @Test
    public void testReduce() {
        int res = intStream.reduce(12, (sum, x) -> sum -= x);
        assertEquals(7, res);
    }

    @Test
    public void testSum() {
        int res = intStream.sum();
        assertEquals(5, res);
    }

    @Test
    public void testToArray() {
        int[] res = intStream.toArray();

        assertArrayEquals(new int[]{-1,0,1,2,3}, res);
    }

    @Test(expected = IllegalStateException.class)
    public void testForClosingAfterTerminalMethod() {
        intStream.average();
        intStream.sum();
    }
    
    @Test(expected = IllegalStateException.class)
    public void testForClosingAfterTerminalMethod2() {
        intStream.average();
        intStream.filter(x -> x < 0).map(x -> x*10);
    }

    @Test
    public void testReduceForEmptyStream(){
        int res = AsIntStream.of().reduce(0, (sum, x) -> sum += x);
        assertEquals(0, res);
    }
}