package stream;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by xd031 on 2017/9/21.
 */
public class FlatMapDemo {

  private static ArrayList lists;

  public static void main(String[] args) throws FileNotFoundException {
//    handleString();
//    handleFile();
//    intStreamToIntArray();
    lists = new ArrayList() {
      {
        add("1");
        add("2");
        add("3");
        add("4");
        add("5");
      }
    };
    listToStringArray(lists);
//    StreamToStringArray(lists);
  }

  private static void StreamToStringArray(List list) {
    //类似于原来的方式一
    String[] arr1 = (String[]) list.stream().toArray(size -> {
      System.out.println(size);
      return new String[size];
    });
    printArray(arr1);

    //方法引用,简单明了
    String[] arr2 = (String[]) list.stream().toArray(String[]::new);
    printArray(arr2);
  }

  private static <T> void printArray(T[] arr) {
    System.out.println(arr.length);
    System.out.println(Arrays.toString(arr));
  }

  private static void intStreamToIntArray() {
    int[] arr = IntStream.of(1, 2, 3, 4, 5).toArray();
    System.out.println(Arrays.toString(arr));

    Integer[] integers = Stream.of(1, 2, 3, 4, 5).toArray(Integer[]::new);
    printArray(integers);
  }

  private static void listToStringArray(List lists) {
    String[] strs = (String[]) lists.toArray(new String[2]);
    printArray(strs);
  }

  private static void handleString() {
    String poetry = "Where, , ?  before me, are the ages that have gone?\n" +
      "And where, behind me, are the coming generations?\n" +
      "I think of heaven and earth, without limit, without end,\n" +
      "And I am all alone and my tears fall down.";
    Arrays.stream(poetry.split("[^A-Za-z]")).filter(word -> word.length() > 0).forEach(System.out::println);
  }

  private static void handleFile() throws FileNotFoundException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src\\main\\java\\stream\\FlatMapDemo.java")));
    ArrayList list = reader.lines().flatMap(line -> Arrays.stream(line.split("[^A-Za-z]"))).filter(word -> word.length() > 0).collect(ArrayList::new, List::add, List::addAll);
    list.forEach(System.out::println);
  }
}
