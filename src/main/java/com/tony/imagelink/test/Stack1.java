package com.tony.imagelink.test;

import org.jetbrains.annotations.NotNull;

import java.util.Stack;

/**
 * @ClassName Description TODO
 * @Author hzf
 * @Date 2021/1/15 17:21
 */
public class Stack1 {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        Integer ret = null;
        while (!stack1.empty()) {
            stack2.push(stack1.pop());
        }
        if (!stack2.empty()) {
            ret = stack2.pop();
            while (!stack2.empty()) {
                stack1.push(stack2.pop());
            }
        }
        return ret;
    }

    public class Solution {
        public boolean Find(int target, int[][] array) {
//            while (true) {
//                try {
//                    tmp = array[i];
//
//                } catch (IndexOutOfBoundsException e) {
//                    break;
//                }
//                ++i;
//            }
            for (int[] tmp : array) {
                for (int i : tmp) {
                    if (i == target) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    //    We .则经过替换之后的字符串为We%20Are%20Happy。
    public String replaceSpace(@NotNull StringBuffer str) {
        String[] middleCharter = str.toString().replaceAll(".","。").split(" ");
        StringBuilder ret = new StringBuilder();
        ret.append(middleCharter[0]);
        if(middleCharter.length == 0){
            return null;
        }
        for (int i = 0; i < middleCharter.length - 1; i++) {
            ret.append("%20");
            ret.append(middleCharter[i + 1]);
        }
        return ret.toString();
    }

    public static void main(String[] args) {
        Stack1 queue = new Stack1();
//        queue.push(1);
//        queue.push(9);
//        queue.push(9);
//        queue.push(7);
//        System.out.println(queue.pop());
//        System.out.println(queue.pop());
//        System.out.println(queue.pop());
//
//        System.out.println(queue.pop());
//        System.out.println(queue.pop());
        System.out.println(queue.replaceSpace(null));
    }
}
