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
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.ws.client.WebServiceClient;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

/**
 * @author Lenny
 */
@Test
public class MathHelperTests_IT extends TestNGCitrusTestDesigner {

    /** Test SoHttp REST client */
    @Autowired
    private HttpClient mathSoapClient;

    @Autowired
    private HttpClient authClient;
   
    @CitrusTest
    public void execSOAPRequestSuccess() {


    	// Get access token
    	http().client(authClient)
        .send()
        .post()
    	.header("Authorization","Basic dGVzdGp3dGNsaWVudGlkOlhZN2ttem9OemwxMDA=")
    	.header("content-type","application/x-www-form-urlencoded")
        .payload("grant_type=password&username=Olle&password=123")	
        ;
    	
        http().client(authClient)
        .receive()
        .response(HttpStatus.OK)
     	.extractFromPayload("$['access_token']","myKey");

        echo("Hit Key is: ${myKey}");


        
    	http().client(mathSoapClient)
        .send()
        .post()
    	.header("Authorization","Bearer ${wso2_token}")
    	.header("SOAPAction","http://tempuri.org/Add")
    	.header("Content-Type", "text/xml")
    	.header("accept","text/xml")
        .payload(new ClassPathResource("templates/addRequest.xml")	
        );
    	
        // Wait for response... Make sure to validate the status code (http -2??) 
    	http().client(mathSoapClient)
            .receive()
            .response(HttpStatus.OK)
            .schemaValidation(false)
            ;
   
    	
    }

    
}
