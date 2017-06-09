package com.javarush.test.level33.lesson10.bonus01;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringWriter;


/* Комментарий внутри xml
Реализовать метод toXmlWithComment, который должен возвращать строку - xml представление объекта obj.
В строке перед каждым тэгом tagName должен быть вставлен комментарий comment.
Сериализация obj в xml может содержать CDATA с искомым тегом. Перед ним вставлять комментарий не нужно.

Пример вызова:  toXmlWithComment(firstSecondObject, "second", "it's a comment")
Пример результата:
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<first>
    <!--it's a comment-->
    <second>some string</second>
    <!--it's a comment-->
    <second>some string</second>
    <!--it's a comment-->
    <second><![CDATA[need CDATA because of < and >]]></second>
    <!--it's a comment-->
    <second/>
</first>
*/
public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) {
        StringWriter sw = null;
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            marshaller.marshal(obj, doc);

            NodeList node = doc.getElementsByTagName(tagName);
            for (int i = 0; i < node.getLength(); i++) {
                Node n = node.item(i);
                Node parNode = n.getParentNode();
                Comment comm = doc.createComment(comment);
                parNode.insertBefore(comm, n);
            }

            NodeList childNodes = doc.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                addCDATA(doc, childNodes.item(i));
            }

            sw = new StringWriter();
            TransformerFactory tf = TransformerFactory.newInstance();
            tf.setAttribute("indent-number", 4);
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.transform(new DOMSource(doc), new StreamResult(sw));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sw.toString();
    }

    private static void addCDATA(Document d, Node n) {
        if (!n.hasChildNodes()) {
            if (n.getNodeType() == Node.TEXT_NODE) {
                String s = n.getTextContent();
                if (s.contains("&") || s.contains("\"") || s.contains("<") || s.contains(">") || s.contains("‘")) {
                    Node newNode = d.createCDATASection(s);
                    n.getParentNode().replaceChild(newNode, n);
                }
            }
        } else {
            NodeList childNodes = n.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                addCDATA(d, childNodes.item(i));
            }
        }
    }

    public static void main(String[] args) throws IOException, TransformerException, ParserConfigurationException, SAXException, JAXBException {
        String result = Solution.toXmlWithComment(new AnExample(), "needCDATA", "it's a comment - <needCDATA>");
        System.out.println(result);
    }

    @XmlType(name = "anExample")
    @XmlRootElement
    public static class AnExample {
        public String[] needCDATA = new String[]{"need CDATA because of < and >", ""};
    }
}
