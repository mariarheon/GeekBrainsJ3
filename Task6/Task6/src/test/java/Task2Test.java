import org.junit.Assert;
import org.junit.Test;

/** Java 3. Lesson 6. Developing kit
 *
 * @author Maria Mikhaleva
 * @version dated May 20 2019
 */

public class Task2Test {
    Task2 task2 = new Task2();

    @Test
    public void test0(){
        boolean res = task2.method2(new int[]{});
        Assert.assertFalse(res);
    }

    @Test
    public void test1(){
       boolean res = task2.method2(new int[]{2,3,5});
        Assert.assertFalse(res);

    }

    @Test
    public void test2(){
       boolean res = task2.method2(new int[]{1,1,4,4});
        Assert.assertTrue(res);

    }


    @Test
    public void test3(){
        boolean res = task2.method2(new int[]{1,4});
        Assert.assertTrue(res);

    }

}