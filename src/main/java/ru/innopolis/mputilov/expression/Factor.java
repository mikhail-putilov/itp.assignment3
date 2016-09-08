package ru.innopolis.mputilov.expression;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

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

    public enum OpCode {MUL, DIV, NONE}
}
