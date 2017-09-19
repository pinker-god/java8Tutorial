package stream;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by xd031 on 2017/9/19.
 */
public class StreamDemo01 {


  public static void main(String[] args) {
    List<Task> tasks = Arrays.asList(
      Task.create(Task::new).get().setStatus(Status.OPEN).setPoints(5),
      Task.create(Task::new).get().setStatus(Status.OPEN).setPoints(13),
      Task.create(Task::new).get().setStatus(Status.CLOSED).setPoints(8)
    );

    //查找status==open的point总和
    int sum1 = tasks.stream().filter(task -> task.getStatus() == Status.OPEN).mapToInt(task -> task.getPoint()).sum();//18
    int sum = tasks.stream().filter(task -> task.getStatus() == Status.OPEN).mapToInt(task -> task.getPoint()).reduce(2, Integer::sum);//20
    System.out.println("status open result is\t" + sum1 + "\t" + sum);

    //按照status聚簇分类
    tasks.stream().collect(Collectors.groupingBy(Task::getStatus)).forEach(((status, task) -> System.out.println(status + "->" + task)));

    //计算所有points的总和(这里不需要boxed更好,因为两个int类型的数相加更快)
    int totalPoints = tasks.stream().mapToInt(task -> task.getPoint()).reduce(0, Integer::sum);

    //计算各部分point所占的比重
//    tasks.stream().mapToInt(task->task.getPoint()).asLongStream().mapToDouble(point->point/totalPoints).boxed()

    List<Integer> list = IntStream.range(1, 100).boxed().collect(Collectors.toList());
    System.out.println(list+"\n"+list.stream().count());
  }
}

enum Status {
  OPEN, CLOSED
}

class Task {
  private Status status;
  private Integer point;

  static Optional<Task> create(Supplier<Task> supplier) {
    return Optional.of(supplier.get());
  }

  public Task() {
  }

  private Task(Status status, Integer point) {
    this.status = status;
    this.point = point;
  }

  public Task setStatus(Status status) {
    return new Task(status, getPoint());
  }

  public Task setPoints(Integer point) {
    return new Task(getStatus(), point);
  }

  public Status getStatus() {
    return status;
  }

  public Integer getPoint() {
    return point;
  }

  @Override
  public String toString() {
    return String.format("[%s,%d]", status, point);
  }
}
