package com.epam.rd.autotasks.arithmeticexpressions;

import java.util.StringJoiner;

public class Expressions {
    public static Variable var(String name, int value) {

        return new Variable(name,value);
    }

    public static Expression val(int value) {
        Expression val = new Expression() {
            private int val=value;

            @Override
            public int evaluate() {
                return val;
            }

            @Override
            public String toExpressionString() {
                if(val<0){
                    return "("+val+")";
                }
                return ""+val;
            }
        };
        return val;
    }

    public static Expression sum(Expression... members){
        Expression sum = new Expression() {

            @Override
            public int evaluate() {
                int sum=0;
                for(Expression variable : members){
                    sum+=variable.evaluate();
                }
                return sum;
            }

            @Override
            public String toExpressionString() {
                String expression = "(";
                for(int i= 0; i<members.length;i++){
                    expression += members[i].toExpressionString();
                    if(i!= members.length-1) {
                         expression+= " + ";
                    }
                }
                expression+=")";

                return expression;
            }
        };
        return sum;
    }

    public static Expression product(Expression... members){
        Expression product = new Expression() {
            @Override
            public int evaluate() {
                int product=1;
                for(Expression variable : members){
                    product*=variable.evaluate();
                }
                return product;
            }

            @Override
            public String toExpressionString() {
                String expression = "(";
                for(int i= 0; i<members.length;i++){
                    expression += members[i].toExpressionString();
                    if(i!= members.length-1) {
                        expression+= " * ";
                    }
                }
                expression+=")";

                return expression;
            }
        };
        return product;
    }

    public static Expression difference(Expression minuend, Expression subtrahend){
        Expression diff = new Expression() {
            @Override
            public int evaluate() {
                return minuend.evaluate() -subtrahend.evaluate();
            }

            @Override
            public String toExpressionString() {
                return "("+minuend.toExpressionString()+" - "+subtrahend.toExpressionString()+")";
            }
        };
        return diff;
    }

    public static Expression fraction(Expression dividend, Expression divisor){
        Expression frac = new Expression() {
            @Override
            public int evaluate() {
                return dividend.evaluate() / divisor.evaluate();
            }

            @Override
            public String toExpressionString() {
                return "("+dividend.toExpressionString()+" / "+divisor.toExpressionString()+")";
            }
        };
        return frac;
    }

}
