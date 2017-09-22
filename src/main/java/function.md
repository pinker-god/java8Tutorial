# transformation--intermediate operation

  - filter
  - map
  - mapToInt
  - mapToDouble
  - mapToLong
  - flatMapToDouble
  - flatMap
  - flatMapToInt
  - flatMapToLong
  - distinct
  - peek(consumer)
  - limit
  - skip

# action--terminal operation(核心就是collect和reduce,两个原理又相同,不过一个用的是consumer接口,一个用的是function接口,可能有人说第一个参数差距很大,是的!是有区别,但是不至于把它分成两种操作;collect的accumulator是把stream流的元素不断的用到第一个supper里面的操作;因此看起来它的结果不是那么明显是后一个的入参.reduce则很明显,前一个的计算结果是后一个的入参,看后面的example很明显.我不想这样分类的原因是其实两者都是后一个的入参,其结果一个不明显参与后者的计算(collect),一个参与了(reduce)!)
  - foreach(注意collection也有此方法,但不一定是流)
  - reduce的处理方式一般是每次都产生新的数据集
  - collect是在原数据集的基础上进行更新，过程中不产生新的数据集
  - toArray
  - allMatch
  - anyMatch
  - noneMatch
  - findAny(不如scala中的take操作)
  - findFirst
  - max
  - min
  - sum
  - average
  - count


**`scala` 的 `aggerate` 函数感觉是reduce和collect的鼻祖,也就是原型!**

### reduce
```java
<U> U reduce(U identity,
                 BiFunction<U, ? super T, U> accumulator,
                 BinaryOperator<U> combiner);

Optional<T> reduce(BinaryOperator<T> accumulator);

T reduce(T identity, BinaryOperator<T> accumulator);
```
```java
@FunctionalInterface
public interface BinaryOperator<T> extends BiFunction<T,T,T> {
```
reduce操作返回单个的结果值，并且reduce操作每处理一个元素总是创建一个新值
可以看出reduce接受两个参数,一个是初始化的数据,另外一个是累积处理器.
常用的方法有average, sum, min, max, count，使用reduce方法都可实现.
注意:
  - reduce的第一个形式这个比较全,接受一个初始化的数据和累积处理器和一个并行处理器.但是直接返回初始化数据相同的类型,不是Option<T>(这个是最全的)
  - reduce的无第一个参数的形式直接返回的是Option形式的处理结果.

**example:**
```java
  System.out.println(Arrays.asList(1, 2, 3, 4, 5).stream().reduce(10,(sum, num2) ->
    {
      System.out.println(sum + ":" + num2);
      return sum + num2;
    }));//10:1	11:2	13:3	16:4	20:5	25
```

### collection
 ```java
 <R> R collect(Supplier<R> supplier,
        BiConsumer<R, ? super T> accumulator,
        BiConsumer<R, R> combiner);
```
collect同reduce,也重载了三个参数,其实他们都是抄自scala里面的一样的功能.
1. Supplier supplier是一个工厂函数，用来生成一个新的容器；
2. BiConsumer accumulator累积处理器，用来把Stream中的元素添加到结果容器中；
3. BiConsumer combiner并行处理器，用来把中间状态的多个结果容器合并成为一个（并发的时候会用到）

**example**(collect的列子我们把我们宿舍的各大god按照后面的得分来分到一起并打印出来!)
```
 Arrays.asList(
      new God("hs", 100),
      new God("hs1", 100),
      new God("hs2", 100),
      new God("hs3", 100),
      new God("ys", 99),
      new God("ys1", 99),
      new God("ys2", 99),
      new God("ws", 98),
      new God("ws1", 98),
      new God("ws2", 98),
      new God("ws3", 98),
      new God("ws4", 98),
      new God("zs", 97)
    ).stream().collect(HashMap<Integer, List<God>>::new, (map, god) -> {
      List<God> list = Optional.ofNullable(map.get(god.point)).orElseGet(ArrayList::new);
      list.add(god);
      map.put(god.point, list);
    }, HashMap::putAll).forEach((key, value) -> System.out.println(key + ":" + value));
```
//这里可能有人说为啥不用groupingBy方法呢?一样的效果,抱歉,这个是我自己实现的,而且更能反映原理.
```
 class God {
    public String name;
    public int point;

    public God(String name, int point) {
      this.name = name;
      this.point = point;
    }

    @Override
    public String toString() {
      return String.format("[%s,%d]", name, point);
    }
  }
```
**result**
```
97:[[zs,97]]
98:[[ws,98], [ws1,98], [ws2,98], [ws3,98], [ws4,98]]
99:[[ys,99], [ys1,99], [ys2,99]]
100:[[hs,100], [hs1,100], [hs2,100], [hs3,100]]
```
