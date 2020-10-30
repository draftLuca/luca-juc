import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 链式编程demo && 四个函数式接口
 *  1.Function<String, Integer>    有返回有输出 输入T 返回 R
 *  2.Predicate<String>            断定型接口 输入T  返回Boolean
 *  3.Supplier<Object>             供给型接口 输入 无  返回 T
 *  4.Consumer<Object>             消费型接口  输入 T  返回  void
 *
 */
public class StreamDmeo {

    public static void main(String[] args) {
        //简洁 使用类上加@Accessors(chain = true)
        User tom = new User().setAge(1).setName("tom");
        User jack = new User(2, "jack");
        User lucy = new User(3, "lucy");
        User bobe = new User(4, "bobe");

        List<User> users = Arrays.asList(tom, jack, lucy, bobe);
        Set<Object> collect = users.stream().filter(a -> a.getAge() !=0).map(User::getName).collect(Collectors.toSet());

        //todo 四大函数式接口
        //有返回有输出 输入T 返回 R
        /*Function<String, Integer> function = new Function() {
            @Override
            public Object apply(Object o) {
                return null;
            }
        };*/
        Function<String, Integer> function = s -> {return s.length();};
        System.out.println("function:\t"+function.apply("luca"));

        //断定型接口 输入T  返回Boolean
        /*Predicate<String> predicate = new Predicate() {
            @Override
            public boolean test(Object o) {
                return false;
            }
        };*/
        Predicate<String> predicate = s -> { return true;};
        System.out.println("predicate:\t"+predicate.test("luca"));

        //供给型接口 输入 无  返回 T
        /*Supplier<Object> supplier = new Supplier() {
            @Override
            public Object get() {
                return null;
            }
        };*/
        Supplier<String> supplier = () -> {return "luca";};
        System.out.println("supplier:\t"+supplier.get());

        //消费型接口  输入 T  返回  void
        /*Consumer<Object> consumer = new Consumer() {
            @Override
            public void accept(Object o) {
            }
        };*/
        Consumer<String> consumer = s -> {};
        System.out.println("Consumer:\t");

    }
}
