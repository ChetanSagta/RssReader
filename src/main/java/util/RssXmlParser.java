package util;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RssXmlParser {

    public List<Map<String,String>> parseString(String rssInformation) throws ParserConfigurationException, IOException,
            SAXException {

        String title = "",description="",link="",pubDate="";
        List<Map<String, String>> data = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        InputStream inputStream = IOUtils.toInputStream(rssInformation);

       Document doc = builder.parse(inputStream);

       doc.getDocumentElement().normalize();

        NodeList items =  doc.getElementsByTagName("item");

        for(int i=0;i<items.getLength();i++) {
            Node tmpElement = items.item(i);
            NodeList childNodes = tmpElement.getChildNodes();
            title = childNodes.item(0).getTextContent();
            description = childNodes.item(1).getTextContent();
            link = childNodes.item(2).getTextContent();
            pubDate = childNodes.item(4).getTextContent();

            Map<String,String> temp = new HashMap<>();

            temp.put("title", title);
            temp.put("description", description);
            temp.put("link", link);
            temp.put("pubDate", pubDate);

            data.add(temp);
        }

       return data;

    }
}