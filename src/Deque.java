/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
//import java.lang.*;

/**
 *
 * @author Jacky
 */
public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int N;

    private class Node {

        private Item item;
        private Node next;
        private Node previous;
    }

    public Deque() {                           // construct an empty deque
        first = null;
        last = null;
        N = 0;
        assert check();
    }

// check internal invariants
    private boolean check() {

        // check a few properties of instance variable 'first'
        if (N < 0) {
            return false;
        }
        if (N == 0) {
            if (first != null) {
                return false;
            }
        } else if (N == 1) {
            if (first == null) {
                return false;
            }
            if (first.next != null) {
                return false;
            }
        } else {
            if (first == null) {
                return false;
            }
            if (first.next == null) {
                return false;
            }
        }

        // check internal consistency of instance variable N
        int numberOfNodes = 0;
        for (Node x = first; x != null && numberOfNodes <= N; x = x.next) {
            numberOfNodes++;
        }
        if (numberOfNodes != N) {
            return false;
        }

        return true;
    }

    public boolean isEmpty() {                 // is the deque empty?
        return N == 0;
    }

    public int size() {                        // return the number of items on the deque
        return N;
    }

    public void addFirst(Item item) {          // add the item to the front
        if (item == null) {
            throw new java.lang.NullPointerException();
        }
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        first.previous = null;
        if (N == 0) {
            last = first;
        } else {
            oldfirst.previous = first;
        }
        N++;
        assert check();
    }

    public void addLast(Item item) {           // add the item to the end
        if (item == null) {
            throw new java.lang.NullPointerException();
        }
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.previous = oldlast;
        if (N == 0) {
            first = last;
        } else {
            oldlast.next = last;
        }
        N++;
        assert check();
    }

    public Item removeFirst() {                // remove and return the item from the front
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        Item item = first.item;        // save item to return
        first = first.next;            // delete first node
        if (N > 1) first.previous = null;
        else {
            first = null;
            last = null;
        }
        N--;
        assert check();
        return item;                   // return the saved item
    }

    public Item removeLast() {                 // remove and return the item from the end
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        Item item = last.item;        // save item to return
        last = last.previous;            // delete first node
        if (N > 1) last.next = null;
        else {
            first = null;
            last = null;
        }
        N--;
        assert check();
        return item;                   // return the saved item
    }

    public Iterator<Item> iterator() {         // return an iterator over items in order from front to end
        return new ListIterator();
    }
// an iterator, doesn't implement remove() since it's optional

    private class ListIterator implements Iterator<Item> {

        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {   // unit testing
        Deque<String> s = new Deque<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                s.addFirst(item);
            } else if (!s.isEmpty()) {
                StdOut.print(s.removeLast() + " ");
            }
        }
        StdOut.println("(" + s.size() + " left on stack)");
    }
}
