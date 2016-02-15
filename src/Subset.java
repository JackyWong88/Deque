/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 *
 * @author Jacky
 */
public class Subset {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        
        Deque<String> s = new Deque<String>();
        String[] strings = StdIn.readAllStrings();
        StdRandom.shuffle(strings);
        for (int i = 0; i < k; i++) {
                s.addFirst(strings[i]);
        }
        for (int i = 0; i < k; i++) {
                StdOut.println(s.removeLast());
        }
        /*
        for (int i = 0; i < strings.length; i++) {
            if(StdRandom.bernoulli()) s.addFirst(strings[i]);
            else s.addLast(strings[i]);
        }
        
        for (int i = 0; i < k; i++) {
            if(StdRandom.bernoulli()) StdOut.println(s.removeFirst());
            else StdOut.println(s.removeLast());
        }
*/
    }
}
