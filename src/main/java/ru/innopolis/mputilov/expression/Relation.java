package ru.innopolis.mputilov.expression;

/**
 * Created by mputilov on 04.09.16.
 */
public class Relation extends Expression {

    OpCode opCode;
    Expression left, right;

    public Relation(OpCode op, Expression a, Expression b) {
        opCode = op;
        left = a;
        right = b;
    }

    public enum OpCode {LESS, LESS_EQ, GREATER, GREATER_EQ, EQUAL, NOT_EQ, NONE}
}
