package com.parser.test;


import com.xml.parser.Stack;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.parsers.FactoryConfigurationError;
import java.io.File;
import java.util.Map;

import com.xml.parser.UnknownStructureParser;

/**
 * Created by Oleg on 19 Aug 2015.
 *
 */
public class ParserTest {

    private final static Logger logger = Logger.getLogger(ParserTest.class);

    private UnknownStructureParser parser = new UnknownStructureParser();

    @Test
    public void testCurr() throws FactoryConfigurationError, Exception {

        File file = new File("C:\\Users\\Oleg\\IdeaProjects\\XmlParser\\resources\\request.xml");

        Stack<Map<String, String>> rowStack = parser.parseXml(file);
        while (!rowStack.isEmpty()) {
            Map<String, String> map = rowStack.pop();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                logger.debug("Key: " + key);
                logger.debug("Value: " + value);
            }
        }
//        Assert.assertEquals(map.get("MonthlyPlanPremiumAmtPP"), "136");
//        Assert.assertEquals(map.get("MonthlyAdvancedPTCAmtPP"), "125");
//        Assert.assertEquals(map.get("AdjustedGrossIncomeAmt"), "22000");
//        Assert.assertEquals(map.get("TotalExemptionsCnt"), "1");
    }

    @Test
    public void testPrev() throws FactoryConfigurationError, Exception {
        File file = new File("C:\\Users\\Oleg\\IdeaProjects\\XmlParser\\resources\\request.xml");
        Map<String, String> map = parser.parse(file);

        // Print all Key/Value pairs
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            logger.debug("Key: " + key);
            logger.debug("Value: " + value);
        }

        Assert.assertEquals(map.get("TotalExemptionsCnt"), "1");
        Assert.assertEquals(map.get("PrimaryBirthDt"), "1970-01-01");
        Assert.assertEquals(map.get("MonthCdPP"), "NOVEMBER");
        Assert.assertEquals(map.get("AdjustedGrossIncomeAmt"), "22000");
    }
}
