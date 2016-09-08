package ru.innopolis.mputilov.expression;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Created by mputilov on 04.09.16.
 */
public class Parenthesized extends Primary {
    public static final String PARENTHESIS = "PARENTHESIS";

    private Expression expression;

    public Parenthesized(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    protected void recursiveToXml(Document doc, Node parent) {
        Element element = doc.createElement(PARENTHESIS);
        parent.appendChild(element);
        expression.recursiveToXml(doc, element);
    }
}
