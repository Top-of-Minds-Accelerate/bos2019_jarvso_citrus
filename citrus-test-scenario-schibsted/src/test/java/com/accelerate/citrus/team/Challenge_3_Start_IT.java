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

package com.accelerate.citrus.team;

import com.accelerate.citrus.team.models.Answer;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.testng.TestNGCitrusTestDesigner;
import com.consol.citrus.http.client.HttpClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;

/**
 * @author Lenny
 */
@Test
public class Challenge_3_Start_IT extends TestNGCitrusTestDesigner {

    /** Test Http REST client */
    @Autowired
    private HttpClient ignoreClient;

    @Autowired
    private HttpClient challengeClient;
   
    @CitrusTest
    public void getIgnoreRESTReq() throws JsonProcessingException {
    	
    	String myTeamName = "MIKE_IC";
    	String myChallengeID = "0f71f5c0-d089-4ddd-ade7-e213e55c6382";
    	
    	// ******************************************
    	// 1) Execute GET request to Welcome service
    	// ******************************************
    	
    	http().client(ignoreClient)
            .send()
            .get();
                
        // Wait for response... Make sure to validate the status code (http -2??) 
    	http().client(ignoreClient)
        .receive()
        .response(HttpStatus.OK)
        .payload(new ClassPathResource("templates/invoice.xml"));

        ;	
        
        // ******************************************
        // Send response to BOSse 
        // ******************************************
        
        Answer answer = new Answer(myChallengeID, myTeamName, "@ignore@");
    
        // Post answer and wait
        http().client(challengeClient)
        	.send()
            .post()
            .contentType("application/json")
            .payload(new ObjectMapper().writeValueAsString(answer));
 
    	// Wait for response... Make sure to validate the status code (http - 2??) 
		http().client(challengeClient)
        	.receive()
        	.response(HttpStatus.OK)
        	.extractFromPayload("$['result']","myResult");   
		
		echo("== SVAR FRÅN BOSse: ${myResult} ==");
		
    }
}
