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

    public enum OpCode {PLUS, MINUS, NONE}
}
