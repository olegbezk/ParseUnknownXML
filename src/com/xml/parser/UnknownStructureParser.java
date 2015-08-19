package com.xml.parser;

import org.apache.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Oleg on 19 Aug 2015.
 *
 */

public class UnknownStructureParser {

    final static Logger logger = Logger.getLogger(UnknownStructureParser.class);

    public Map<String, String> parseXml(File file) throws Exception {
        String name = "", value = "", attrName;
        Map<String, String> map = new HashMap<>();
        XMLStreamReader xr = XMLInputFactory.newInstance().createXMLStreamReader(new FileInputStream(file));

        while (xr.hasNext()) {
            int e = xr.next();
            switch (e) {
                case XMLStreamReader.START_ELEMENT: {
                    name = xr.getLocalName();
                    final int attributeCount = xr.getAttributeCount();
                    if (attributeCount > 0) {
                        attrName = xr.getAttributeName(0).getLocalPart();
                        final String attributeValue = xr.getAttributeValue(0);
                        System.out.println(name + " " + attrName + " " + attributeValue);
                    }
                    break;
                }
                case XMLStreamReader.CHARACTERS: {
                    value = xr.getText();
                    break;
                }
            }
            map.put(name, value);
        }
        return map;
    }

    public Map<String, String> parse(File file) throws Exception {

        Map<String, String> map = new HashMap<>();
        XMLStreamReader xr = XMLInputFactory.newInstance().createXMLStreamReader(new FileInputStream(file));

        while(xr.hasNext()) {
            int e = xr.next();
            if (e == XMLStreamReader.START_ELEMENT) {
                String name = xr.getLocalName();
                xr.next();
                String value = null;
                try {
                    value = xr.getText();
                } catch (IllegalStateException ex) {
                    logger.warn(ex);
                }
                map.put(name, value);
            }
        }
        return map;
    }
}
