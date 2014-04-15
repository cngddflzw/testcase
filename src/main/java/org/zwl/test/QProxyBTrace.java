//package org.zwl.test;
//
//import com.sun.btrace.annotations.BTrace;
//import com.sun.btrace.annotations.OnMethod;
//
//import static com.sun.btrace.BTraceUtils.*;
//
///**
// * @author zhenweiliu created on 3/4/14
// */
//@BTrace
//public class QProxyBTrace {
//
//    @OnMethod(
//            clazz = "java.nio.ByteBuffer",
//            method = "allocateDirect"
//    )
//    public static void check(int capacity) {
//        println("===============");
//        println(strcat("Thread: ", strcat(str(threadId(currentThread())), strcat(" ", name(currentThread())))));
//        println(strcat(timestamp(), strcat(" size: ", str(capacity))));
//        jstack();
//    }
//}