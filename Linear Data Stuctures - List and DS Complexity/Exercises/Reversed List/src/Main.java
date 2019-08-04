public class Main {
    @SuppressWarnings(value = "unchecked")
    public static void main(String[] args) {
        ReversedList reversedList = new ReversedList();
        reversedList.add(4);
        reversedList.add(5);
        reversedList.add(1);
        reversedList.add(12);
        reversedList.add(9);
        reversedList.removeAt(0);
        reversedList.removeAt(3);
        reversedList.set(1, 43);
        System.out.println(reversedList.capacity());
        System.out.println(reversedList.count());

        System.out.println();
        reversedList.forEach(e -> System.out.println(e));


    }
}
