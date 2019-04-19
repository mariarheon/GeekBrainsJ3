
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Java 3. Lesson 1. Generics
 *
 * @author Maria Mikhaleva
 * @dated April 18 2019
 */

public class HomeTask1 {

    public static void main(String[] args) {
        System.out.println("Test 1 for method 1:");
        Integer[] arr1 = new Integer[]{1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(arr1));
        method1(1, 4, arr1);
        System.out.println(Arrays.toString(arr1));

        System.out.println("Test 2 for method 1:");
        String[] arr2 = new String[]{"one", "two", "three", "four", "five"};
        System.out.println(Arrays.toString(arr2));
        method1(0, 3, arr2);
        System.out.println(Arrays.toString(arr2));

        System.out.println("Test 1 for method 2:");
        ArrayList<Integer> a1 = method2(arr1);
        System.out.println(a1 + " " + a1.get(0).getClass());

        System.out.println("Test 2 for method 2:");
        ArrayList<String> a2 = method2(arr2);
        System.out.println(a2 + " " + a2.get(0).getClass());

        System.out.println("Test for task 3");
        Box<Apple> box1a = new Box<>();
        Box<Orange> box1o = new Box<>();
        Box<Apple> box2a = new Box<>();

        box1a.put(new Apple[]{new Apple((float) 2.0), new Apple((float) 1.0)});
        box2a.put(new Apple[]{new Apple((float) 1.0), new Apple((float) 1.5)});
        box1o.put(new Orange[]{new Orange((float) 1.5), new Orange((float) 1.0)});
        System.out.println(box1a.toString());
        System.out.println(box2a.toString());
        System.out.println(box1o.toString());
        if (box2a.compare(box1o)) {
            System.out.println("Weights of boxes are the same");
        } else{
            System.out.println("Weights of boxes are different");
        }
        
        box1a.giveMeMore(box2a);
        System.out.println(box1a.toString());
        System.out.println(box2a.toString());
        
    }

    private static <T> void method1(int index1, int index2, T[] a) {
        T temp = a[index2];
        a[index2] = a[index1];
        a[index1] = temp;
    }

    private static <T> ArrayList<T> method2(T[] arr) {
        ArrayList<T> a = new ArrayList<T>();
        a.addAll(Arrays.asList(arr));
        return a;
    }


}
