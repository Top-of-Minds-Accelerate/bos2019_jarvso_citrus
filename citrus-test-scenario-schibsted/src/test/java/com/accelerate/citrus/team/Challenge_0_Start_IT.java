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
import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;

/**
 * @author Lenny
 */
@Test
public class Challenge_0_Start_IT extends TestNGCitrusTestDesigner {

    /** Test Http REST client */
    @Autowired
    private HttpClient welcomeClient;

    @Autowired
    private HttpClient challengeClient;
   
    @CitrusTest
    public void getWelcomeDataRESTReq() throws JsonProcessingException {
    	
    	String myTeamName = "REPLACE_ME";
    	String myChallengeID = "8fb21cc5-6ea7-465a-8fc3-c03618c86e1f";
    	
    	// ******************************************
    	// 1) Execute GET request to Welcome service
    	// ******************************************
    	
    	http().client(welcomeClient)
            .send()
            .get();
                
        // Wait for response... Make sure to validate the status code (http -2??) 
    	http().client(welcomeClient)
        .receive()
        .response(HttpStatus.OK)
        .payload("{\"message\": \"Välkommen till BOS 2019, Citrus Integration Testing\"," +
        			"\"task\": \"Leta upp koden och returnera den till BOSse\"," +
        		 "\"code\": \"123456789\"}")
        .extractFromPayload("$['code']","myCode");

        echo("== KOD I UPPDRAG 1: Hit Key is: ${myCode} ==");  

        	
        // ******************************************
        // Send response to BOSse 
        // ******************************************
        
        Answer answer = new Answer(myChallengeID, myTeamName, "${myCode}");
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
