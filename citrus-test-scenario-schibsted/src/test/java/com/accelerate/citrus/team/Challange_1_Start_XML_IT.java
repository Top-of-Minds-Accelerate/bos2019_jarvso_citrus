package com.accelerate.citrus.team;

import org.testng.annotations.Test;

import com.consol.citrus.annotations.CitrusXmlTest;
import com.consol.citrus.testng.AbstractTestNGCitrusTest;

/**
 * This is a sample Citrus integration test for loading XML syntax test case.
 *
 * @author Citrus
 */
@Test
public class Challange_1_Start_XML_IT extends AbstractTestNGCitrusTest {

    @CitrusXmlTest(name = "Challange_1_Start_XML_IT")
    public void sampleXml() {}
}
