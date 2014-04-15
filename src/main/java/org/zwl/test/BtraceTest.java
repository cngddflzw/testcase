//package org.zwl.test;
//
//import com.sun.btrace.BTraceUtils;
//import com.sun.btrace.annotations.*;
//
//import static com.sun.btrace.BTraceUtils.jstack;
//import static com.sun.btrace.BTraceUtils.print;
//import static com.sun.btrace.BTraceUtils.println;
//
//@BTrace
//public class BtraceTest {
//
//    @Export
//    private static long count;
//
//    @OnMethod(
//            clazz = "org.zwl.test.Counter",
//            method = "add"
//    )
//    public static void test(int param1, int param2, @Self Object self, @ProbeClassName String pcn, @ProbeMethodName String pmn) {
//        BTraceUtils.timeMillis();
//        println(param1);
//        println(param2);
//        jstack();
//    }
//}
