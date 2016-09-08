package ru.innopolis.mputilov.expression;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Created by mputilov on 04.09.16.
 */
public class Term extends Expression {
    private OpCode opCode;
    private Expression left;
    private Expression right;

    public Term(OpCode op, Expression left, Expression right) {
        opCode = op;
        this.left = left;
        this.right = right;
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
            case PLUS:
                return String.valueOf(leftEvaluated + rightEvaluated);
            case MINUS:
                return String.valueOf(leftEvaluated - rightEvaluated);
            default:
                return null;
        }
    }

    public enum OpCode {PLUS, MINUS, NONE}
}
