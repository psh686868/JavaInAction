package lambdasinaction.chap4;

import java.util.*;
import java.util.stream.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import static lambdasinaction.chap4.Dish.menu;

public class StreamBasic {

    public static void main(String...args){
        // Java 7
        //getLowCaloricDishesNamesInJava7(Dish.menu).forEach(System.out::println);

        //System.out.println("---");

        // Java 8
        //getLowCaloricDishesNamesInJava8(Dish.menu).forEach(System.out::println);

        //psh的兰布达表达式
        getLowCaloricDishesNamesInPSH(Dish.menu).forEach(System.out::println);

    }

    public static List<String> getLowCaloricDishesNamesInJava7(List<Dish> dishes){

        List<Dish> lowCaloricDishes = new ArrayList<>();
        for(Dish d: dishes){
            if(d.getCalories() < 400){
                lowCaloricDishes.add(d);
            }
        }
        List<String> lowCaloricDishesName = new ArrayList<>();
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            public int compare(Dish d1, Dish d2){
                return Integer.compare(d1.getCalories(), d2.getCalories());
            }
        });
        for(Dish d: lowCaloricDishes){
            lowCaloricDishesName.add(d.getName());
        }
        return lowCaloricDishesName;
    }

    //获取价格高于500的并且排序，并且返回这个集合的名称加+psh

    public static List<String> getLowCaloricDishesNamesInPSH(List<Dish> dishes) {
        if(dishes==null){
            return Collections.emptyList();
        }
       return dishes.stream()
                .filter(dish -> {
                            System.out.println("##################filter##########"+dish.getName());
                        return dish.getCalories()>500;}
                        )
                .sorted(comparing(Dish::getCalories))
                .map(dish ->{
                            System.out.println("!!!!!!!!!Map!!!!!!!!!!"+dish.getName());
                            String name = dish.getName().replaceAll("fr","psh");
                            return name + "!!!psh"; }
                    )
                .limit(3)
                .collect(toList());
    }



    public static List<String> getLowCaloricDishesNamesInJava8(List<Dish> dishes) {
        return dishes.stream()
                .filter(d -> d.getCalories() < 400)
                .sorted(comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(toList());
    }


}