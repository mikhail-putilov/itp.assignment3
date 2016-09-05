package ru.innopolis.mputilov.expression;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

/**
 * Created by mputilov on 04.09.16.
 */
public class PrettyPrinter {

    public static final String INT = "INT";
    public static final String PARENTHESIS = "PARENTHESIS";

    private static Document createDocument() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            return docBuilder.newDocument();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    private void add(Document doc, Node parent, Expression expression) {
        if (expression == null) return;
        Element element;
        if (expression instanceof Logical) {
            Logical logicalExpression = (Logical) expression;
            element = doc.createElement(logicalExpression.opCode.name());
            parent.appendChild(element);
            add(doc, element, logicalExpression.left);
            add(doc, element, logicalExpression.right);
        } else if (expression instanceof Relation) {
            Relation relationExpression = (Relation) expression;
            element = doc.createElement(relationExpression.opCode.name());
            parent.appendChild(element);
            add(doc, element, relationExpression.left);
            add(doc, element, relationExpression.right);
        } else if (expression instanceof Term) {
            Term termExpression = (Term) expression;
            element = doc.createElement(termExpression.opCode.name());
            parent.appendChild(element);
            add(doc, element, termExpression.left);
            add(doc, element, termExpression.right);
        } else if (expression instanceof Integer) {
            Integer integerExpression = (Integer) expression;
            element = doc.createElement(INT);
            element.setTextContent(integerExpression.getValue());
            parent.appendChild(element);
        } else if (expression instanceof Factor) {
            Factor factorExpression = (Factor) expression;
            element = doc.createElement(factorExpression.opCode.name());
            add(doc, element, factorExpression.left);
            add(doc, element, factorExpression.right);
            parent.appendChild(element);
        } else if (expression instanceof Parenthesized) {
            Parenthesized parenthesized = (Parenthesized) expression;
            element = doc.createElement(PARENTHESIS);
            add(doc, element, parenthesized.getExpression());
            parent.appendChild(element);
        }
    }

    public void print(Expression expression) {
        Document doc = createDocument();
        add(doc, doc, expression);

        try {
            Transformer transformer = null;
            transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            StreamResult result = new StreamResult(new StringWriter());
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, result);
            String xmlString = result.getWriter().toString();
            System.out.println(xmlString);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }


}
