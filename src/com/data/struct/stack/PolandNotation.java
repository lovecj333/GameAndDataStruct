package com.data.struct.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {

    public static void main(String[] args) {

        String expression = "1+((2+3)*4)-5";
        List<String> middleList = toMiddleExpressionList(expression);
        System.out.println("中缀表达式对应的List="+middleList); // ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
        List<String> afterList = toAfterExpressionList(middleList);
        System.out.println("后缀表达式对应的List="+afterList);  // ArrayList [1,2,3,+,4,*,+,5,–]
        System.out.println(calculate(afterList));

        /*
        //(30+4)*5-6 => 30 4 + 5 * 6 - => 164
        //说明 为了方便 逆波兰表达式的数字和符号使用空格隔开
        String suffixExpression = "30 4 + 5 * 6 -";
        //先将 "3 4 + 5 * 6 -" => 放到ArrayList中
        //将ArrayList传递给一个方法遍历ArrayList配合栈完成计算
        List<String> list = getListString(suffixExpression);
        System.out.println(list);
        int result = calculate(list);
        System.out.println(result);
        */
    }

    //将中缀表达式对应的List转换成后缀表达式对应的List
    //ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]  =》 ArrayList [1,2,3,+,4,*,+,5,–]
    public static List<String> toAfterExpressionList(List<String> list){
        Stack<String> s1 = new Stack<>();   //符号栈
        List<String> s2 = new ArrayList<>();    //存储中间结果List
        for(String item : list){
            if(item.matches("\\d+")){   //是一个数加入s2
                s2.add(item);
            }else if(item.equals("(")){ //是左括号入栈s1
                s1.push(item);
            }else if(item.equals(")")){
                //是右括号则依次弹出s1栈顶的运算符加入s2直到遇到左括号为止 删除s1中的左括号
                while(!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();   //将左括号弹出s1栈(删除s1中的左括号)
            }else{
                while(s1.size() != 0 && priority(s1.peek()) >= priority(item)){
                    //栈顶元素是运算符 比较item的优先级是否小于等于s1栈顶运算符 如果小于等于则将s1栈顶的运算符弹出加入到s2中
                    //再次与s1中新的栈顶运算符相比较 做同样的操作
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }
        //将s1中剩余的运算符依次弹出加入s2
        while(s1.size() != 0){
            s2.add(s1.pop());
        }
        return s2;
    }

    //返回运算符的优先级、数字越大、优先级就越高
    public static int priority(String oper){
        if(oper.equals("*") || oper.equals("/")){
            return 1;
        }else if(oper.equals("+") || oper.equals("-")){
            return 0;
        }
        return -1;
    }

    //将中缀表达式转成对应的List
    //s="1+((2+3)×4)-5";
    public static List<String> toMiddleExpressionList(String s){
        //定义一个List,存放中缀表达式 对应的内容
        List<String> list = new ArrayList<>();
        int index = 0;  //用于遍历中缀表达式字符串的指针
        String str = "";    //对多位数进行拼接
        while(index < s.length()){
            char c = s.charAt(index);
            if(c < 48 || c > 57){   //如果c是一个非数字 需要加入到ls
                list.add(c + "");
                index++;
            }else{  //如果是一个数，需要考虑多位数
                str = "";
                while(index < s.length()){
                    char c2 = s.charAt(index);
                    if(c2 >= 48 && c2 <= 57){   //判断c2是否为1个数
                        str += c2;
                        index++;
                    }else{
                        break;
                    }
                }
                list.add(str);
            }
        }
        return list;
    }

    //将一个逆波兰表达式依次将数据和运算符 放入到ArrayList中
    public static List<String> getListString(String suffixExpression){
        String[] strArr = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for(String str : strArr){
            list.add(str);
        }
        return list;
    }

    //完成对逆波兰表达式的运算
    //1)从左至右扫描，将3和4压入堆栈；
    //2)遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素），计算出3+4的值，得7，再将7入栈；
    //3)将5入栈；
    //4)接下来是×运算符，因此弹出5和7，计算出7×5=35，将35入栈；
    //5)将6入栈；
    //6)最后是-运算符，计算出35-6的值，即29，由此得出最终结果
    public static int calculate(List<String> list){
        Stack<String> stack = new Stack<>();
        for(String item : list){
            if(item.matches("\\d+")){   //匹配的是多位数
                stack.push(item);
            }else{
                // pop出两个数运算结果再入栈
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int res = 0;
                if(item.equals("+")){
                    res = num2 + num1;
                }else if(item.equals("-")){
                    res = num2 - num1;
                }else if(item.equals("*")){
                    res = num2 * num1;
                }else if(item.equals("/")){
                    res = num2 / num1;
                }else{
                    throw new RuntimeException("运算符有误");
                }
                stack.push(res+"");
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
