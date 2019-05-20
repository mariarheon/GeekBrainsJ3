
/** Java 3. Lesson 6. Developing kit
 *
 * @author Maria Mikhaleva
 * @version dated May 20 2019
 */
public class Task1 {

    int[] method1(int[] arr) {
        if (arr.length == 0) {
            return null;
        }
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 4) {
                break;
            }
            if (i == arr.length - 1) {
                throw new RuntimeException("There are no 4 in the array");
            }
        }
        
        int[] res = new int[0];
        
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == 4) {;
                res = new int[arr.length - i - 1];
                for (int j = 0; j < res.length; j++) {
                    res[j] = arr[i + j + 1];
                }
                break;
            }
        }
        return res;
    }
}
