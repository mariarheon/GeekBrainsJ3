import java.util.ArrayList;
import java.util.Arrays;
/**
 *
 * @author Maria Mikhaleva
 * @version dated May 16 2019
*/
public class Race {
    private ArrayList<Stage> stages;

    public ArrayList<Stage> getStages() {
        return stages;
    }

    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }
}