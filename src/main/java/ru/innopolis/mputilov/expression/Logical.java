package ru.innopolis.mputilov.expression;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Created by mputilov on 04.09.16.
 */
public class Logical extends Expression {

    private OpCode opCode;
    private Expression left;
    private Expression right;

    public Logical(OpCode op, Expression left, Expression right) {
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
        Boolean leftEvaluated = Boolean.valueOf(left.evaluate());
        Boolean rightEvaluated = Boolean.valueOf(right.evaluate());
        switch (opCode) {
            case AND:
                return String.valueOf(leftEvaluated && rightEvaluated);
            case OR:
                return String.valueOf(leftEvaluated || rightEvaluated);
            default:
                return null;
        }
    }

    public enum OpCode {AND, OR, XOR, NONE}
}
