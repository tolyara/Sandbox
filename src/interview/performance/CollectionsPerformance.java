package interview.performance;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class CollectionsPerformance {

//    private static final int iterations = 2_000_000;
    private static final int iterations = 200_000;

    private static final String str = "Hello";

    public static void main(String[] args) {


//        testListsRemoveFromBegin();
        testListsRemoveFromEnd();
    }

    /*
        LinkedList wins
    */
    private static void testListsRemoveFromBegin() {
        // iterations - 20_000
        // linked : 8   5   3
        // array  : 46  48  49

        Consumer<List<String>> removerFromBegin = (list) -> {
            while (!list.isEmpty()) {
                list.remove(0);
            }
        };

        List<String> linkedList = new LinkedList<>();
        for (int i = 0; i < iterations; i++) {
            linkedList.add(String.valueOf(i));
        }
        process(linkedList, "linked", removerFromBegin);

        List<String> arrayList = new ArrayList<>();
        for (int i = 0; i < iterations; i++) {
            arrayList.add(String.valueOf(i));
        }
        process(arrayList, "array", removerFromBegin);
    }

    /*
        LinkedList has a little advantage
     */
    private static void testListsRemoveFromEnd() {
        // iterations - 20_000
        // linked : 4   8   3
        // array  : 3   2   3

        // iterations - 200_000
        // linked : 12   16   13
        // array  : 24   24   22

        Consumer<List<String>> removerFromEnd = (list) -> {
            while (!list.isEmpty()) {
                list.remove(list.size() - 1);
            }
        };

        List<String> linkedList = new LinkedList<>();
        for (int i = 0; i < iterations; i++) {
            linkedList.add(String.valueOf(i));
        }
        List<String> arrayList = new ArrayList<>();
        for (int i = 0; i < iterations; i++) {
            arrayList.add(String.valueOf(i));
        }
        testListsRemove(linkedList, arrayList, removerFromEnd);
    }

    private static void testListsRemove(List<String> linkedList, List<String> arrayList, Consumer<List<String>> consumer) {
        process(linkedList, "linked", consumer);
        process(arrayList, "array", consumer);
    }

    private static void process(List<String> list, String listName, Consumer<List<String>> consumer) {
        long start = System.currentTimeMillis();
        consumer.accept(list);
        long finish = System.currentTimeMillis();
        System.out.println(listName + " : " + (finish - start));
    }

    /*
        LinkedList wins
     */
    private static void testListsInsertToBegin() {
        // iterations - 20_000
        // linked : 4   3   4    etc.
        // array : 50  47  47

        // iterations - 200_000
        // linked : 9
        // array : 5258

        long start = System.currentTimeMillis();
        List<String> linkedList = new LinkedList<>();
        for (int i = 0; i < iterations; i++) {
            linkedList.add(0, str);
        }
        long finish = System.currentTimeMillis();
        System.out.println("linked : " + (finish - start));

        start = System.currentTimeMillis();
        List<String> arrayList = new ArrayList<>();
        for (int i = 0; i < iterations; i++) {
            arrayList.add(0, str);
        }
        finish = System.currentTimeMillis();
        System.out.println("array : " + (finish - start));
    }

    /*
        ArrayList wins
    */
    private static void testListsInsertToEnd() {
        // iterations - 2_000_000
        // linked : 99 107 119 etc.
        //  array : 83 90  85

        long start = System.currentTimeMillis();
        List<String> linkedList = new LinkedList<>();
        for (int i = 0; i < iterations; i++) {
            linkedList.add(str);
        }
        long finish = System.currentTimeMillis();
        System.out.println("linked : " + (finish - start));

        start = System.currentTimeMillis();
        List<String> arrayList = new ArrayList<>();
        for (int i = 0; i < iterations; i++) {
            arrayList.add(str);
        }
        finish = System.currentTimeMillis();
        System.out.println("array : " + (finish - start));
    }

}
