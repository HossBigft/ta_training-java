package com.epam.rd.autotasks.arithmeticexpressions;

public class Variable implements Expression {
    private int value;
    private String name;

    public Variable(String name, int value) {
        this.name=name;
        this.value=value;
    }

    public void setValue(int value) {
        this.value=value;
    }

    @Override
    public int evaluate() {
        //implement this method
        return value;
    }

    @Override
    public String toExpressionString() {
        //implement this method
        return name;
    }
}
