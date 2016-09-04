package ru.innopolis.mputilov.expression;

/**
 * Created by mputilov on 04.09.16.
 */
public class Factor extends Expression {
    public enum OpCode {MUL, DIV}
    Primary left;
    Primary right;
}
