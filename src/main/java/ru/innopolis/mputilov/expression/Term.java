package ru.innopolis.mputilov.expression;

/**
 * Created by mputilov on 04.09.16.
 */
public class Term extends Expression {
    public Term(OpCode op, Factor left, Factor right) {
        opCode = op;
        this.left = left;
        this.right = right;
    }

    public enum OpCode {PLUS, MINUS, NONE}
    OpCode opCode;
    Factor left;
    Factor right;
}
