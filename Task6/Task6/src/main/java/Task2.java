
/** Java 3. Lesson 6. Developing kit
 *
 * @author Maria Mikhaleva
 * @version dated May 20 2019
 */
public class Task2 {

    public boolean method2(int[] arr) {
        if (arr.length == 0) {
            return false;
        }
        int countOne = 0;
        int countFour = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 1 && arr[i] != 4) {
                return false;
            }
            if (arr[i] == 1) {
                countOne++;
            }else if (arr[i] == 4){
                countFour++;
            }
        }
        return countOne != 0 && countFour != 0;

    }

}
