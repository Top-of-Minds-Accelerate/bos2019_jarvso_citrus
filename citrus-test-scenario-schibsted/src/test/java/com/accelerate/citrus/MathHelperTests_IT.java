/*
 * Copyright 2006-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.accelerate.citrus;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.testng.TestNGCitrusTestDesigner;
import com.consol.citrus.ws.client.WebServiceClient;
import org.springframework.core.io.ClassPathResource;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

/**
 * @author Lenny
 */
@Test
public class MathHelperTests_IT extends TestNGCitrusTestDesigner {

    /** Test SoHttp REST client */
    @Autowired
    private WebServiceClient mathSoapClient;   
   
    @CitrusTest
    public void execSOAPRequestSuccess() {

    	soap()
        	.client(mathSoapClient)
        	.send()
        	.soapAction("http://tempuri.org/Add")
            .payload(new ClassPathResource("templates/addRequest.xml"));
    	
    	soap().client("mathSoapClient")
         	.receive()
         	.schemaValidation(false)
            .payload(new ClassPathResource("templates/addResponse.xml"))
            .extractFromPayload("//:AddResponse/:AddResult", "addResult")
            ;
   
    	echo("Svaret är: ${addResult}");
    	
    	echo("SVARET IGEN:" + addNumbers());
    	
    }

    public String addNumbers() {

    	soap()
    	.client(mathSoapClient)
    	.send()
    	.soapAction("http://tempuri.org/Add")
        .payload(new ClassPathResource("templates/addRequest.xml"));

	soap().client("mathSoapClient")
     	.receive()
     	.schemaValidation(false)
        .payload(new ClassPathResource("templates/addResponse.xml"))
        .extractFromPayload("//:AddResponse/:AddResult", "addResult")
        ;
	
		return "${addResult}"; 
    	
    }

    
}
