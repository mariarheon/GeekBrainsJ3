/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subtask1;

import java.util.logging.Level;
import java.util.logging.Logger;

/** Java 3. Lesson 4. Threads.
 *
 * @author Maria Mikhaleva
 * @version dated Mar 13 2019
 */
public class SubTask1 {

    static Object lock = new Object();
    static volatile char cur = 'A';
    static class Letter implements Runnable {

        private char letter;
        private char next_letter;

        public Letter(char letter, char next_letter) {
            this.letter = letter;
            this.next_letter = next_letter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                synchronized (lock) {
                    try {
                        while (cur != letter)
                            lock.wait();
                        System.out.println(letter);
                        cur = next_letter;
                        lock.notifyAll();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SubTask1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            }
        }

    }

    public static void main(String[] args) {
        new Thread(new Letter('A', 'B')).start();
        new Thread(new Letter('B', 'C')).start();
        new Thread(new Letter('C', 'A')).start();
    }

}
