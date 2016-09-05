package ru.innopolis.mputilov.expression;


/**
 * Created by mputilov on 04.09.16.
 */
public class Logical extends Expression {

    OpCode opCode;
    Expression left;
    Expression right;

    public Logical(OpCode op, Expression left, Expression right) {
        opCode = op;
        this.left = left;
        this.right = right;
    }

    public enum OpCode {AND, OR, XOR, NONE}
}
