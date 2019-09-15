package com.liuyubobobo.stackdemo;

import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * 示例 1: 输入: "()" 输出: true
 * 示例 2: 输入: "()[]{}" 输出: true
 * 示例 3: 输入: "(]" 输出: false
 * 示例 4: 输入: "([)]" 输出: false
 * 示例 5: 输入: "{[]}" 输出: true
 */

public class Leetcode20 {

    public boolean isValid(String s) {
        //定义一个字符栈
        Stack<Character> stack = new Stack<>();
        //遍历字符串得到每一个字符
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '(' || c == '{' || c == '['){
                //如果是左括号就入栈
                stack.push(c);
            }else{
                //如果是右括号先判断栈是否为空
                if(stack.isEmpty()){
                    return false;
                }
                //不为空取出栈顶元素进行匹配
                char top = stack.pop();
                if(c == ')' && top != '('){
                    return false;
                }
                if(c == '}' && top != '{'){
                    return false;
                }
                if(c == ']' && top != '['){
                    return false;
                }
            }
        }
        //如果是有效的括号则最后栈应为空栈
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Leetcode20 lc = new Leetcode20();
        System.out.println(lc.isValid("()"));
        System.out.println(lc.isValid("()[]{}"));
        System.out.println(lc.isValid("(]"));
        System.out.println(lc.isValid("([)]"));
        System.out.println(lc.isValid("{[]}"));
    }
}
