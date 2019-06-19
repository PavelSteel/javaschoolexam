package com.tsystems.javaschool.tasks.calculator;

import java.util.Stack;

public class Calculator {

    /**
     * Evaluate statement represented as string.
     *
     * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement is invalid
     */
    public String evaluate(String statement) {
        // TODO: Implement the logic here

        Stack<Integer> values = new Stack<Integer>();
        Stack<Double> dValues = new Stack<Double>();
        Stack<Character> ops = new Stack<Character>();
        Stack<Character> opsM = new Stack<Character>();

        if (statement!=null){
            char[] tokens = statement.toCharArray();

            if (tokens.length == 0)
                return null;
            else if(corBrack(tokens) == false)
                return null;
            else {
                for (int i = 0; i < tokens.length; i++)
                {
                    if (tokens[i] == ' ')
                        continue;
                    if (tokens[i] >= '0' && tokens[i] <= '9' && !statement.contains("."))
                    {
                        int j = i;
                        StringBuffer sBuf = new StringBuffer();
                        while (j < tokens.length && tokens[j] >= '0' && tokens[j] <= '9')
                            sBuf.append(tokens[j++]);
                        if(sBuf.length()>1)
                            i=j-1;
                        values.push(Integer.parseInt(sBuf.toString()));

                    }
                    else if (tokens[i] >= '0' && tokens[i] <= '9' && statement.contains("."))
                    {
                        int j = i;
                        StringBuffer sBuf = new StringBuffer();
                        while (j < tokens.length && ((tokens[j] >= '0' && tokens[j] <= '9')||tokens[j]=='.'))
                            sBuf.append(tokens[j++]);
                        if(sBuf.length()>1)
                            i=j-1;
                        dValues.push(Double.parseDouble(sBuf.toString()));

                    }
                    else if (tokens[i] == '('){
                        ops.push(tokens[i]);
                        opsM.push(tokens[i]);
                    }
                    else if (tokens[i] == ')')
                    {
                        if (opsM.empty()){
                            ops.clear();
                            values.clear();
                            break;
                        }
                        else {
                            while (ops.peek() != '(')
                                if (!statement.contains(".")) {
                                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                                } else
                                    dValues.push(applyOpD(ops.pop(), dValues.pop(), dValues.pop()));
                            ops.pop();
                        }
                    }
                    else if (tokens[i] == '+' || tokens[i] == '-' ||
                            tokens[i] == '*' || tokens[i] == '/')
                    {
                        if (i < tokens.length && tokens[i] == tokens[i+1])
                        {
                            ops.clear();
                            values.clear();
                            break;
                        }
                        else {
                            while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
                                if (!statement.contains(".")) {
                                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                                } else
                                    dValues.push(applyOpD(ops.pop(), dValues.pop(), dValues.pop()));

                            ops.push(tokens[i]);
                        }
                    }
                }
                while (!ops.empty())
                {
                    try {
                        if (!statement.contains(".")) {
                            values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                        } else
                            dValues.push(applyOpD(ops.pop(), dValues.pop(), dValues.pop()));
                    } catch (UnsupportedOperationException e)
                    {
                        ops.clear();
                        values.clear();
                        dValues.clear();
                        break;
                    }
                }

                if (!dValues.empty()){
                    String val = String.valueOf(dValues.pop());
                    return val;
                }
                else if (dValues.empty() && !values.empty()){
                    String val = String.valueOf(values.pop());
                    return val;
                }
                else
                    return null;
            }
        } else
            return null;
    }

    // Возвращает true, если «op2» имеет более высокий или тот же приоритет, что и «op1»,
    // в противном случае возвращает false
    public static boolean hasPrecedence(char op1, char op2)
    {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }
    //Проверка правильного кол-ва скобок наличие посторонних знаков в выражении
    public static boolean corBrack(char[] tok){
        String t1 = "";
        String t2 = "";

        for (int i = 0; i < tok.length; i++){
            if(tok[i]=='('){
                t1 += tok[i];
            } else if(tok[i]==')')
                t2 += tok[i];
            else if(tok[i]==',')
            {
                return false;
            }
            else if (tok[i] == '.')
            {
                if (i < tok.length && tok[i] == tok[i+1])
                {
                    return false;
                }
            }
        }

        if (t1.length() == t2.length()){
            return true;
        } else
            return false;
    }

    // Служебный метод для применени оператора к операндам
    public static int applyOp(char op, int b, int a)
    {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0)
                    throw new
                            UnsupportedOperationException("Cannot divide by zero");
                return a / b;
        }

            return 0;
    }
    public static double applyOpD(char op, double b, double a)
    {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0)
                    throw new
                            UnsupportedOperationException("Cannot divide by zero");
                return a / b;
        }

        return 0;
    }
}

