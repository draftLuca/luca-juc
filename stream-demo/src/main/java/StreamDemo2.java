import java.util.*;
import java.util.stream.Collectors;

/**
 * stream流的eg
 */
public class StreamDemo2 {

    public static void main(String[] args) {

        User tom = new User().setAge(1).setName("tom");
        User jack = new User(1, "jack");
        User lucy = new User(3, "lucy");
        User bobe = new User(4, "bobe");

        List<User> users = Arrays.asList(tom, jack, lucy, bobe);
        Set<Object> collect = users.stream().filter(a -> a.getAge() !=0).map(a -> a.getName()).collect(Collectors.toSet());
        Set<Object> collect1 = users.stream().filter(a -> a.getAge() !=0).map(User::getName).collect(Collectors.toSet());
        System.out.println(collect1);

        users.stream().forEach(a -> System.out.println(a));

        //分组
        Map<Integer, List<User>> collect3 = users.stream().collect(Collectors.groupingBy(User::getAge));
        System.out.println(collect3);

        //排序
        List<User> collect2 = users.stream().sorted((a, b) -> {
            int i = b.getAge() - a.getAge();
            return i;
        }).collect(Collectors.toList());
        System.out.println(collect2);
    }
}
