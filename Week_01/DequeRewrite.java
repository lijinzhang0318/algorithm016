package com.ljz.win.future.util;

import java.util.Deque;
import java.util.LinkedList;

public class DequeRewrite {
    public static void main(String[] args) {
//        Deque<String> deque = new LinkedList<>();
//        deque.push("a");
//        deque.push("b");
//        deque.push("c");
//        System.out.println(deque);
//
//        String str = deque.peek();
//        System.out.println(str);
//        System.out.println(deque);
//
//        while(deque.size() > 0) {
//            System.out.println(deque.pop());
//        }
//        System.out.println(deque);

        /** api改写 **/
        Deque<String> deque = new LinkedList<>();
        deque.addFirst("a");
        deque.addFirst("b");
        deque.addFirst("c");
        System.out.println(deque);

        String str = deque.getFirst();
        System.out.println(str);
        System.out.println(deque);

        while(deque.size() > 0) {
            System.out.println(deque.removeFirst());
        }
        System.out.println(deque);
    }
}
