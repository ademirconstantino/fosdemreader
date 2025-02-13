package com.constantinoit.fosdemreader.service;

import com.constantinoit.fosdemreader.model.FosdemResponse;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

@Component
public class XMLReader {

    public List<FosdemResponse> read(List<String> keywords) {

        List<FosdemResponse> result = new ArrayList<>();
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        inputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        try {
            // Load and parse the XML file
            File xmlFile = new File("fosdem.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();

            // Extract schedule information
            NodeList dayNodes = document.getElementsByTagName("day");
            for (int i = 0; i < dayNodes.getLength(); i++) {
                Element dayElement = (Element) dayNodes.item(i);
                NodeList roomNodes = dayElement.getElementsByTagName("room");
                for (int j = 0; j < roomNodes.getLength(); j++) {
                    Element roomElement = (Element) roomNodes.item(j);
                    NodeList eventNodes = roomElement.getElementsByTagName("event");
                    for (int k = 0; k < eventNodes.getLength(); k++) {
                        Element eventElement = (Element) eventNodes.item(k);

                        // Extract the <abstract> tag content
                        String abstractContent = getTextContent(eventElement, "abstract");
                        loop:
                        for (String keyword : keywords) {
                            if (abstractContent.toLowerCase().contains(keyword.toLowerCase())) {
                                String eventTitle = getTextContent(eventElement, "title");
                                String eventDate = getTextContent(eventElement, "date");
                                result.add(FosdemResponse.builder().event(eventTitle).dateObj(inputFormat.parse(eventDate)).date(inputFormat.parse(eventDate).toString()).description(abstractContent).build());
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Collections.sort(result);
        return result;
    }

    private static String getTextContent(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return "";
    }
}