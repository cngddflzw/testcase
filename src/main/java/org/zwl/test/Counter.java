package org.zwl.test;

/**
 * @author zhenweiliu created on 3/4/14
 */
public class Counter {

    public int add(int a1, int a2) {
        return a1 + a2;
    }

    public static void main(String[] args) throws InterruptedException {
        Counter c = new Counter();
        while (true) {
            System.out.println(c.add(1, 2));
            Thread.currentThread().sleep(1000);
        }
    }
}
