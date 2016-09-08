package ru.innopolis.mputilov.expression;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Created by mputilov on 04.09.16.
 */
public class Relation extends Expression {

    private OpCode opCode;
    private Expression left, right;

    public Relation(OpCode op, Expression a, Expression b) {
        opCode = op;
        left = a;
        right = b;
    }

    @Override
    protected void recursiveToXml(Document doc, Node parent) {
        Element element = doc.createElement(opCode.name());
        parent.appendChild(element);
        left.recursiveToXml(doc, element);
        right.recursiveToXml(doc, element);
    }

    @Override
    public String evaluate() {
        java.lang.Integer leftEvaluated = java.lang.Integer.valueOf(left.evaluate());
        java.lang.Integer rightEvaluated = java.lang.Integer.valueOf(right.evaluate());
        switch (opCode) {
            case LESS:
                return String.valueOf(leftEvaluated < rightEvaluated);
            case GREATER:
                return String.valueOf(leftEvaluated > rightEvaluated);
            default:
                return null;
        }
    }

    public enum OpCode {LESS, LESS_EQ, GREATER, GREATER_EQ, EQUAL, NOT_EQ, NONE}
}
