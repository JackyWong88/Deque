/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

/**
 *
 * @author Jacky
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] a;         // array of items
    private int N;            // number of elements on stack

    public RandomizedQueue() {                // construct an empty randomized queue
        N = 0;
        a = (Item[]) new Object[2];
    }

    public boolean isEmpty() {                // is the queue empty?
        return N == 0;
    }

    public int size() {                       // return the number of items on the queue
        return N;
    }

    public void enqueue(Item item) {          // add the item
        if (item == null) {
            throw new java.lang.NullPointerException();
        }
        if (N == a.length) {
            resize(2 * a.length);    // double size of array if necessary
        }
        a[N++] = item;                            // add item
    }

    public Item dequeue() {                   // remove and return a random item
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        int idx = StdRandom.uniform(N);
        Item item = a[idx];
        N--;
        a[idx] = a[N];                              // to avoid loitering
        a[N] = null;
        // shrink size of array if necessary
        if (N > 0 && N == a.length / 4) {
            resize(a.length / 2);
        }
        return item;
    }

    public Item sample() {                    // return (but do not remove) a random item
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        return a[StdRandom.uniform(N)];
    }

    // resize the underlying array holding the elements
    private void resize(int capacity) {
        assert capacity >= N;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    public Iterator<Item> iterator() {        // return an independent iterator over items in random order
        return new ReverseArrayIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ReverseArrayIterator implements Iterator<Item> {
        private int i;

        public ReverseArrayIterator() {
            i = N-1;
            if(N != 0) StdRandom.shuffle(a, 0, N-1);
        }

        public boolean hasNext() {
            return i >= 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return a[i--];
        }
    }

    public static void main(String[] args) {  // unit testing

    }
}
