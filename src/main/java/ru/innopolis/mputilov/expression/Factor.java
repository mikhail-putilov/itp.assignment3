package ru.innopolis.mputilov.expression;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.lang.Integer;

/**
 * Created by mputilov on 04.09.16.
 */
public class Factor extends Expression {
    private OpCode opCode;
    private Expression left;
    private Expression right;

    public Factor(OpCode op, Expression a, Expression b) {
        opCode = op;
        left = a;
        right = b;
    }

    @Override
    protected void recursiveToXml(Document doc, Node parent) {
        Element element = doc.createElement(opCode.name());
        left.recursiveToXml(doc, element);
        right.recursiveToXml(doc, element);
    }

    @Override
    public String evaluate() {
        java.lang.Integer leftEvaulated = Integer.valueOf(left.evaluate());
        java.lang.Integer rightEvaulated = Integer.valueOf(right.evaluate());
        switch (opCode) {
            case DIV:
                return String.valueOf(leftEvaulated / rightEvaulated);
            case MUL:
                return String.valueOf(leftEvaulated * rightEvaulated);
            default:
                return null;
        }
    }

    public enum OpCode {MUL, DIV, NONE}
}
