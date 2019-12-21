package ua.edu.ucu.stream;


public class ShittyTest{
    public static void main(String[] args) {
        IntStream z = AsIntStream.of(-1,0, 1, 2, 3);
        // System.out.println(z.average());
        // System.out.println(z.max());
        IntStream k = z.flatMap(x -> AsIntStream.of(x - 1, x, x + 1));
        StringBuilder str = new StringBuilder();
        k.forEach(x -> str.append(x));
        System.out.println(str.toString());
        IntStream y = z.filter(x -> x < 3);
        // System.out.println(y.min());
        System.out.println(z.count());
        y.forEach(System.out::println);
        // y.forEach(System.out::println);

        // Stream<Integer> kek = Stream.of(1,2,3);
        // kek.forEach(System.out::println);
        // IntStream newKek = kek.mapToInt(x -> x*2);
        // IntStream another = kek.mapToInt(x -> x*10);
        // kek.forEach(System.out::println);
        // // newKek.forEach(System.out::println);


    }
}