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
        Element element;
        if (expression instanceof Operator) {
            element = doc.createElement(encodeOperator(expression));
        } else {
            element = doc.createElement("operand");
            element.setAttribute("value", expression.getRepresentative());
        }
        parent.appendChild(element);
        for (Expression child : expression.getChildren()) {
            add(doc, element, child);
        }
    }

    private String encodeOperator(Expression expression) {
        String representative = expression.getRepresentative();
        switch (representative) {
            case "+":
                return "plus";
            case "-":
                return "minus";
            case "/":
                return "divide";
            case "*":
                return "multiply";
            default:
                throw new IllegalStateException("");
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
