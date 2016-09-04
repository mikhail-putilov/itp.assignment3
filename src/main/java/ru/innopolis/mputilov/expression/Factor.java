package ru.innopolis.mputilov.expression;

/**
 * Created by mputilov on 04.09.16.
 */
public class Factor extends Expression {
    OpCode opCode;
    Expression left;
    Expression right;
    public Factor(OpCode op, Expression a, Expression b) {
        opCode = op;
        left = a;
        right = b;
    }
    public enum OpCode {MUL, DIV, NONE}
}
