package com.data.struct.stack;

import java.util.Stack;

public class Calculator {

    public static void main(String[] args) {
        //计算这个表达式的值
        String expression = "10+7*2*2-5+1-5+3+7";
        Stack<Integer> numStack = new Stack<>();    //数栈
        Stack<Character> operStack = new Stack<>(); //符号栈
        int index = 0;  //用于扫描的变量
        String keepNum = "";    //用于拼接、多位数
        while(true){
            //依次得到expression 的每一个字符
            char ch = expression.substring(index, index+1).charAt(0);
            if(isOper(ch)){ //是运算符
                if(!operStack.isEmpty()){   //符号栈不为空
                    if(priority(ch) <= priority(operStack.peek())){ //当前的操作符的优先级小于或者等于栈中的操作符
                        //从数栈中pop出两个数 从符号栈中pop出一个符号 进行运算结果入数栈 然后将当前的操作符入符号栈
                        int num1 = numStack.pop();
                        int num2 = numStack.pop();
                        char oper = operStack.pop();
                        int result = cal(num1, num2, oper);
                        numStack.push(result);
                        operStack.push(ch);
                    }else{  //当前的操作符的优先级大于栈中的操作符就直接入符号栈
                        operStack.push(ch);
                    }
                }else{  //符号栈为空
                    //直接入符号栈
                    operStack.push(ch);
                }
            }else{  //是数字
                //当处理数字时不能发现是一个数就立即入栈因为他可能是多位数
                //需要向expression的表达式的index后再看一位 如果是数就进行扫描 如果是符号才入栈
                //因此我们需要定义一个字符串变量用于拼接
                keepNum += ch;
                //ch已经是expression的最后一位直接入栈
                if(index == expression.length() - 1){
                    numStack.push(Integer.parseInt(keepNum));
                }else{
                    //判断下一个字符是不是数字 是数字就继续扫描 是运算符则入栈
                    if(isOper(expression.substring(index+1, index+2).charAt(0))){
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }
            index++;
            if(index >= expression.length()){
                break;
            }
        }
        //当表达式扫描完毕 顺序的从数栈和符号栈中pop出相应的数和符号运行
        while(true){
            //符号栈为空 数栈中只有一个数字 计算完毕
            if(operStack.isEmpty()){
                break;
            }
            int num1 = numStack.pop();
            int num2 = numStack.pop();
            char oper = operStack.pop();
            int result = cal(num1, num2, oper);
            numStack.push(result);
        }
        System.out.println("计算的结果是: " + numStack.pop());
    }

    //返回运算符的优先级、数字越大、优先级就越高
    public static int priority(char oper){
        if(oper == '*' || oper == '/'){
            return 1;
        }else if(oper == '+' || oper == '-'){
            return 0;
        }
        return -1;
    }

    //判断一个字符是否是运算符
    public static boolean isOper(char oper) {
        return oper == '+' || oper == '-' || oper == '*' || oper == '/';
    }

    //计算方法
    public static int cal(int num1, int num2, char oper){
        int result = 0;
        switch(oper){
            case '+':
                result = num2 + num1;
                break;
            case '-':
                result = num2 - num1;
                break;
            case '*':
                result = num2 * num1;
                break;
            case '/':
                result = num2 / num1;
                break;
        }
        return result;
    }
}
