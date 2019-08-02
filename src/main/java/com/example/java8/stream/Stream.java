package com.example.java8.stream;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Stream流：
 * 筛选， 排序，聚合
 * +--------------------+       +------+   +------+   +---+   +-------+
 * | stream of elements +-----> |filter+-> |sorted+-> |map+-> |collect|
 * +--------------------+       +------+   +------+   +---+   +-------+
 *
 *  数据源： 流的来源。 可以是集合，数组，I/O channel， 产生器generator 等。
 *  聚合操作： 类似SQL语句一样的操作， 比如filter, map, reduce, find, match, sorted等。
 *
 * 集合的两个创建流方法：
 * stream() − 为集合创建串行流。
 * parallelStream() − 为集合创建并行流。
 * @Author 彬
 * @Date 2019/8/2
 */
public class Stream {

    public static void main(String[] args) {

        //示例一：过滤集合 filter:用于通过设置的条件过滤出元素,Collectors 类:实现了很多归约操作，例如将流转换成集合和聚合元素。Collectors 可用于返回列表或字符串：
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println(Arrays.toString(filtered.toArray()));

        //示例二：foreach 内部迭代流中的数据（方法引用 :: ），以前是外部迭代foreach在外层。limit: 限制流的的数量 sorted(): 对流的数据排序
        Random random = new Random();
        random.ints().limit(10).sorted().forEach(System.out::println);

        //示例三： map 方法用于映射每个元素到对应的结果
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        // 获取对应的平方数
        List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
        System.out.println(Arrays.toString(squaresList.toArray()));

        //示例四： 并行流
        List<String> string2 = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        // 获取空字符串的数量
        long count = string2.parallelStream().filter(string -> string.isEmpty()).count();
        System.out.println(count);


        //示例五：统计
        List<Integer> numberList = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        // IntSummaryStatistics 用于收集统计数据（如计数，最小值，最大值，总和和平均值）的状态对象。
        IntSummaryStatistics stats = numberList.stream().mapToInt((x) -> x).summaryStatistics();

        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
    }
}
