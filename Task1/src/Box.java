
import java.util.ArrayList;
import java.util.Arrays;

public class Box<T extends Fruit> {

    private ArrayList<T> listOfObj = new ArrayList<>();

    public void put(T[] obj) {
        listOfObj.addAll(Arrays.asList(obj));
    }

    public ArrayList<T> get() {
        return listOfObj;
    }

    private double getWeight() {
        double sum = 0;
        for (T obj : listOfObj) {
            sum += obj.getWeight();
        }

        return sum;
    }

    public <V extends Fruit> boolean compare(Box<V> box2) {
        return (getWeight() == box2.getWeight());
    }

    public void giveMeMore(Box<T> box2) {
        this.listOfObj.addAll(box2.get());
        System.out.println(getWeight());
        box2.get().clear();

    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        if (!this.listOfObj.isEmpty()) {
            str.append(this.listOfObj.get(0).getClass() + ", weight: " + this.getWeight());
        } else {
            str.append("Box is empty");
        }
        return str.toString();
    }

}
