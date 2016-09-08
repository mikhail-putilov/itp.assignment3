package ru.innopolis.mputilov.expression;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Created by mputilov on 04.09.16.
 */
public class Integer extends Primary {
    public static final String INT = "INT";
    private long value;

    public Integer(String number) {
        value = Long.valueOf(number);
    }

    public String getValue() {
        return String.valueOf(value);
    }

    @Override
    protected void recursiveToXml(Document doc, Node parent) {
        Element element = doc.createElement(INT);
        element.setTextContent(getValue());
        parent.appendChild(element);
    }
}
