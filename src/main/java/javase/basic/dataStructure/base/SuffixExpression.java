package javase.basic.dataStructure.base;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 后缀表达式的解析例如：AB+C* 以逗号作为一个数的分隔 分号结束 4,5,+,3,*,;
 */
public class SuffixExpression {
    public static int explain(String suffixExpression) {
        int tmp = 0;
        Stack stack = new Stack();
        // 接受输入的后缀表达式，逗号,分割每个字段 分号;结束
        String[] s = suffixExpression.split(",");
        for (int i = 0; i < s.length; i++) {
            if (isNumeric(s[i])) {// 1.如果是数字，一直入栈
                stack.add(s[i]);
            }
            else if (isComputer(s[i])) {// 2.如果是计算符，执行运算，出栈两个数
                int x = Integer.parseInt((String) stack.remove());
                int y = Integer.parseInt((String) stack.remove());
                tmp = computer(x, y, s[i]);
                stack.add(tmp + "");

            }
            else if (";".equals(s[i])) {// 3.遇到;分号结束符
                return Integer.parseInt((String) stack.remove());
            }
        }
        return tmp;
    }


    /**
     * 加减乘除计算
     */
    private static int computer(int x, int y, String key) {
        switch (key) {// switch 条件判断
        case "*":
            return x * y;

        case "+":
            return x + y;

        case "/":
            return x / y;

        case "-":
            return y - x;
        }
        return 0;
    }


    /**
     * 判断是否是数字
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }


    /**
     * 判断是不是+-/\*
     */
    public static boolean isComputer(String str) {
        Pattern pattern = Pattern.compile("[\\+\\-\\*/]");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }


    public static void main(String[] args) {
        int i = explain("4,5,+,3,*");
        System.out.println(i);
    }
}
