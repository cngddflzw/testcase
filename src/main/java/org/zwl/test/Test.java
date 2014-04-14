package org.zwl.test;

/**
 * @author zhenweiliu created on 3/20/14
 */
public class Test {

    public static void main(String[] args) {
        F1 s = new S1();
        System.out.println(s.c);
    }

    private static class F1 {
        public Class c = getClass();
    }

    private static class S1 extends F1 {

    }
}
