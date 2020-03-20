import com.alibaba.fastjson.JSON;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@AnnotationExample(name="Apple",value="class",path="com.alibaba.fastjson.JSON")
public class Apple {
    @AnnotationExample(name="getColor",value="function",path="com.alibaba.fastjson.JSON")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Bean
    public Integer getWeight() {
        System.out.print("hello getWeight!\n");
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @AnnotationExample(name="color",value="filed",path="com.alibaba.fastjson.JSON")
    private String color;
    private Integer weight;

    public static boolean isGreenApple(Apple apple){
        return "Green".equals(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple){
        return apple.getWeight()>150;
    }

    //颜色绿色
    public static List<Apple> filterGreenApples(List<Apple> apples) {
        List<Apple> greenApples = new ArrayList<Apple>();
        for (Apple apple : apples) {
            if ("Green".equals(apple.getColor())) {
                greenApples.add(apple);
            }
        }
        return greenApples;
    }

    //重量大于150
    public static List<Apple> filterHeavyApples(List<Apple> apples) {
        List<Apple> heavyApples = new ArrayList<Apple>();
        for (Apple apple : apples) {
            if (apple.getWeight() > 150) {
                heavyApples.add(apple);
            }
        }
        return heavyApples;
    }
}
