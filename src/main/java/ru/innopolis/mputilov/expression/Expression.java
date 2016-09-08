package ru.innopolis.mputilov.expression;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by mputilov on 04.09.16.
 */
public abstract class Expression {

    private static Document createDocument() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            return docBuilder.newDocument();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public Document toXml() {
        Document doc = createDocument();
        recursiveToXml(doc, doc);
        return doc;
    }

    protected abstract void recursiveToXml(Document doc, Node parent);
}
